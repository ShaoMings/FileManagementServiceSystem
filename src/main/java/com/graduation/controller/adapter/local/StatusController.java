package com.graduation.controller.adapter.local;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.model.pojo.Peers;
import com.graduation.model.pojo.User;
import com.graduation.model.vo.FileResponseVo;
import com.graduation.model.vo.UserDirStatusVo;
import com.graduation.service.IndexService;
import com.graduation.service.PeersService;
import com.graduation.service.UserService;
import com.graduation.utils.Constant;
import com.graduation.utils.DateConverter;
import com.graduation.utils.FileSizeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description 首页视图控制器
 *
 * @author shaoming
 * @date 2021/8/17 16:08
 * @since 1.0
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/status")
public class StatusController extends BaseController {

    private final IndexService indexService;
    private final PeersService peersService;
    private final UserService userService;


    /**
     * 首页视图
     * @return 首页
     */
    @RequestMapping("")
    public String home() {
        return "status";
    }


    /**
     * 获取状态信息
     *
     * @return 响应对象
     */
    @RequestMapping("/getStatus")
    @ResponseBody
    public FileResponseVo getStatus() {
        try {
            String json = HttpUtil.get(getPeersUrl() + Constant.API_STATUS);
            JSONObject jsonObject = JSONUtil.parseObj(json);
            if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
                Map<String, Object> res = indexService.getStatus(jsonObject.get("data"));
                Double peerUsed = FileSizeConverter.getLengthAutoCalToByte((String) res.get("peerUsed"));
                peersService.updatePeersUsedSpace(getPeers().getId(),peerUsed);
                res.put("peerFree",FileSizeConverter.getLength(peersService.getPeersLeftSpace(getPeers().getId()).longValue()));
                return FileResponseVo.success(res);
            } else {
                return FileResponseVo.fail("调用接口失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FileResponseVo.fail("系统异常");
    }


    @RequestMapping("/getUserStatus")
    @ResponseBody
    public FileResponseVo getUserStatus(){
        try {
            String json = HttpUtil.get(getPeersUrl() + Constant.API_USER_STATUS+"?userPath="+getUser().getUsername());
            JSONObject jsonObject = JSONUtil.parseObj(json);
            if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
                JSONObject data = JSONUtil.parseObj(jsonObject.get("data"));
                // 用户文件总数
                Integer fileCount = (Integer) data.get("count");
                // 用户存储空间使用量
                Object tmpSize = data.get("size");
                Double userLeftDiskSpace;
                String size;
                // 用户初始总存储空间大小
                Double userTotalDiskSpace = userService.getUserTotalDiskSpace(getUser().getId());
                if (tmpSize instanceof Integer){
                    Integer sumOfUsedSize = (Integer) data.get("size");
                    // 用户真实剩余存储空间
                    userLeftDiskSpace = userTotalDiskSpace - sumOfUsedSize.doubleValue();
                    userService.updateUserLeftDiskSpace(getUser().getId(),userLeftDiskSpace);
                    size = FileSizeConverter.getLength(sumOfUsedSize);
                }else {
                    Long sumOfUsedSize = (Long) data.get("size");
                    // 用户真实剩余存储空间
                    userLeftDiskSpace = userTotalDiskSpace - sumOfUsedSize.doubleValue();
                    userService.updateUserLeftDiskSpace(getUser().getId(),userLeftDiskSpace);
                    size = FileSizeConverter.getLength(sumOfUsedSize);
                }
                String total = FileSizeConverter.getLength(userTotalDiskSpace.longValue());
                String left = FileSizeConverter.getLength(userLeftDiskSpace.longValue());
                UserDirStatusVo statusVo = new UserDirStatusVo();
                statusVo.setCount(fileCount);
                statusVo.setSize(size);
                statusVo.setTotal(total);
                statusVo.setLeft(left);
                return FileResponseVo.success(statusVo);
            } else {
                return FileResponseVo.fail("调用接口失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FileResponseVo.fail("系统异常");
    }


    /**
     * 修正数据统计信息
     *
     * @return 响应对象
     */
    @RequestMapping("/repair_stat")
    @ResponseBody
    public FileResponseVo repairStatus() {
        int count = 0;
        for (int i = 0; i > -Constant.MONTHS_DAY; i--) {
            String dateString = DateConverter.getFormatDate(DateConverter.StringToDate(
                    DateConverter.dayCalculate(DateConverter.getFormatDate("yyyy-MM-dd"), i),
                    "yyyy-MM-dd"), "yyyyMMdd");
            HashMap<String, Object> hashMap = new HashMap<>(20);
            hashMap.put("date", dateString);
            String res = HttpUtil.post(getPeersUrl() + Constant.API_REPAIR_STAT, hashMap);
            JSONObject jsonObject = JSONUtil.parseObj(res);
            if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
                count++;
            }
        }
        return FileResponseVo.success("成功修正" + count + "天数据信息", null);
    }

    /**
     * 同步失败修复
     * @return 响应对象
     */
    @RequestMapping("/repair")
    @ResponseBody
    public FileResponseVo repair() {
        String res = HttpUtil.post(getPeersUrl() + Constant.API_REPAIR + "?force=1", new HashMap<>(8));
        JSONObject jsonObject = JSONUtil.parseObj(res);
        if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
            return FileResponseVo.success("请求修复操作成功,正在后台操作,请勿重复使用此功能", null);
        } else {
            return FileResponseVo.fail("操作失败,请稍后重新尝试");
        }
    }

    /**
     *  备份数据
     * @return 响应对象
     */
    @RequestMapping("/backup")
    @ResponseBody
    public FileResponseVo backup(){
        int count = 0;
        for (int i = 0; i > -Constant.MONTHS_DAY; i--) {
            String sub = DateConverter.dayCalculate(DateConverter.getFormatDate("yyyy-MM-dd"),i);
            String date = DateConverter.getFormatDate(DateConverter.StringToDate(sub,"yyyy-MM-dd"),"yyyyMMdd");
            HashMap<String, Object> hashMap = new HashMap<>(20);
            hashMap.put("date", date);
            String res = HttpUtil.post(getPeersUrl() + Constant.API_BACKUP, hashMap);
            JSONObject jsonObject = JSONUtil.parseObj(res);
            if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
                count++;
            }
        }
        return FileResponseVo.success("成功备份" + count + "天数据信息", null);
    }

    /**
     *  移除空文件夹
     * @return 响应对象
     */
    @RequestMapping("/remove_empty_dir")
    @ResponseBody
    public FileResponseVo removeEmptyDir(){
        String res = HttpUtil.post(getPeersUrl()+Constant.API_REMOVE_EMPTY_DIR,new HashMap<>(3));
        JSONObject jsonObject = JSONUtil.parseObj(res);
        if (Constant.API_STATUS_SUCCESS.equals(jsonObject.getStr(Constant.STATUS_CONSTANT))) {
            return FileResponseVo.success("请求删除操作成功,正在后台操作,请勿重复使用此功能", null);
        } else {
            return FileResponseVo.fail("操作失败,请稍后重新尝试");
        }
    }

    /**
     * 获取所有集群对象
     * @return 响应对象
     */
    @RequestMapping("/getAllPeers")
    @ResponseBody
    public FileResponseVo getAllPeers(){
        List<Peers> list = peersService.list();
        return FileResponseVo.success(list);
    }


    /**
     * 切换集群
     * @param id 集群id
     * @param session session
     * @return 响应对象
     */
    @RequestMapping("/switchPeers")
    @ResponseBody
    public FileResponseVo switchPeers(int id, HttpSession session){
        if (id == getPeers().getId()){
            return FileResponseVo.fail("当前集群正在使用中");
        }
        User user = getUser();
        user.setPeersid(id);
        user.setId(getUser().getId());
        // 用户更新时间
        user.setUpdateTime(new Date());
        if (userService.updateById(user)){
            Peers peers = peersService.getById(id);
            session.setAttribute("peers",peers);
            return FileResponseVo.success();
        }
        return FileResponseVo.fail("切换失败");
    }

}
