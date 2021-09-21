package com.graduation.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.graduation.service.IndexService;
import com.graduation.utils.DateConverter;
import com.graduation.utils.FileSizeConverter;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Description 首页业务实现类
 *
 * @author shaoming
 * @date 2021/8/17 14:32
 * @since 1.0
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Override
    public Map<String, Object> getStatus(Object data) {
        // 声明结果集
        Map<String, Object> result = new HashMap<>();
        // 声明30天文件大小数据容器
        List<String> dayFileSizeList = new ArrayList<>();
        // 声明30天文件数量数据容器
        List<String> dayFileCountList = new ArrayList<>();
        // 声明30天内日期容器
        List<String> dayNumList = new ArrayList<>();
        JSONObject parseObj = JSONUtil.parseObj(data);
        JSONArray parseArray = JSONUtil.parseArray(parseObj.get("Fs.FileStats"));
        // 剩余空间
        result.put("diskFreeSize", FileSizeConverter.getLength(Long.parseLong(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("free"))));
        // 总空间
        result.put("diskTotalSize", FileSizeConverter.getLength(Long.parseLong(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("total"))));
        // 已使用空间
        result.put("diskUsedSize", FileSizeConverter.getLength(Long.parseLong(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("used"))));
        // 节点
        result.put("inodesTotal", JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("inodesTotal"));
        // 节点
        result.put("inodesUsed", JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("inodesUsed"));
        // 节点
        result.put("inodesFree", JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("inodesFree"));
        long dayFileSize = 0;
        long dayFileCount = 0;
        for (int i = 0; i < parseArray.size(); i++) {
            JSONObject fileStats = JSONUtil.parseObj(parseArray.getStr(i));
            if ("all".equals(fileStats.get("date"))) {
                // 获取总文件大小,数量
                result.put("totalFileSize", FileSizeConverter.getLength(Long.parseLong(fileStats.getStr("totalSize"))));
                result.put("totalFileCount", fileStats.getStr("fileCount"));
            } else {
                long subDay = DateConverter.differentBetweenDays(DateConverter.StringToDate(fileStats.getStr("date"), "yyyyMMdd"), new Date(), false);
                if (subDay <= 30) {
                    // 获取30天内文件大小,数量
                    dayFileSize += Long.parseLong(fileStats.getStr("totalSize"));
                    dayFileCount += Long.parseLong(fileStats.getStr("fileCount"));
                    dayFileSizeList.add(FileSizeConverter.getLengthOfMb(Long.parseLong(fileStats.getStr("totalSize"))));
                    dayFileCountList.add(fileStats.getStr("fileCount"));
                    dayNumList.add(fileStats.getStr("date").substring(6) + "日");
                }
            }
        }
        result.put("dayFileSize", FileSizeConverter.getLength(dayFileSize));
        result.put("dayFileCount", dayFileCount);
        result.put("dayFileSizeList", dayFileSizeList);
        result.put("dayFileCountList", dayFileCountList);
        result.put("dayNumList", dayNumList);
        return result;
    }
}
