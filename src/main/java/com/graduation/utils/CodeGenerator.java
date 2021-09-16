package com.graduation.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;

/**
 * Description MybatisPlus代码生成器
 *
 * @author shaoming
 * @date 2021/7/22 10:37
 * @since 1.0
 **/

public class CodeGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator ag = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        // 1.配置基本信息

        // 代码生成路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("shaoming");
        // 完成后是否打开资源管理器
        gc.setOpen(false);
        // 是否覆盖原来的
        gc.setFileOverride(false);
        // 正则表达式去除service接口名 I 前缀
        gc.setServiceName("%sService");
        gc.setIdType(IdType.ASSIGN_UUID);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        ag.setGlobalConfig(gc);

        // 2.配置数据源

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(com.graduation.utils.YmlUtils.getValue("spring.datasource.driver-class-name"));
        dsc.setUrl(com.graduation.utils.YmlUtils.getValue("spring.datasource.url"));
        dsc.setUsername(com.graduation.utils.YmlUtils.getValue("spring.datasource.username"));
        dsc.setPassword(com.graduation.utils.YmlUtils.getValue("spring.datasource.password"));
        dsc.setDbType(DbType.MYSQL);
        ag.setDataSource(dsc);

        // 3.配置包信息

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.graduation");
        pc.setEntity("model");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        ag.setPackageInfo(pc);

        // 4.策略配置

        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 设置映射的表名  生成其他表只需修改这里即可
        strategy.setInclude("share");

        // lombok 支持
        strategy.setEntityLombokModel(true);
        // 逻辑删除
        strategy.setLogicDeleteFieldName("deleted");
        // 自动填充配置
        TableFill create = new TableFill("createTime", FieldFill.INSERT);
        TableFill update = new TableFill("updateTime", FieldFill.INSERT_UPDATE);
        strategy.setTableFillList(Arrays.asList(create,update));
        // 乐观锁
        strategy.setVersionFieldName("version");
        // 驼峰命名
        strategy.setRestControllerStyle(true);
        // 下划线命名
        strategy.setControllerMappingHyphenStyle(true);
        ag.setStrategy(strategy);

        // 执行
        ag.execute();

    }
}
