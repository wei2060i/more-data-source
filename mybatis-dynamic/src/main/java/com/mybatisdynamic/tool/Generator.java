package com.mybatisdynamic.tool;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Wei
 */
public class Generator {

    public static void main(String[] args) throws FileNotFoundException {
        Map map = new Yaml().load(new FileInputStream("mybatis-dynamic/src/main/resources/config/reverse.yml"));
        String author = LnUtils.ymlToObject(map, "author");
        String url = LnUtils.ymlToObject(map, "url");
        String username = LnUtils.ymlToObject(map, "username");
        String password = LnUtils.ymlToObject(map, "password") + "";
        String driverName = LnUtils.ymlToObject(map, "driverName");

        String outputDir = LnUtils.ymlToObject(map, "src");

        String packParent = LnUtils.ymlToObject(map, "pack.parent");
        String packEntity = LnUtils.ymlToObject(map, "pack.entity");
        String packDao = LnUtils.ymlToObject(map, "pack.dao");
        String packServiceImpl = LnUtils.ymlToObject(map, "pack.serviceImpl");
        String packService = LnUtils.ymlToObject(map, "pack.service");
        String packController = LnUtils.ymlToObject(map, "pack.controller");


        String tablePrefix = LnUtils.ymlToObject(map, "table.prefix");

        List<String> list = null;
        list = LnUtils.ymlToObject(map, "table.exclude");
        String[] tableExclude = list != null ? list.toArray(new String[0]) : null;
        list = LnUtils.ymlToObject(map, "table.include");
        String[] tableInclude = list != null ? list.toArray(new String[0]) : null;

        List<String> overWriter = LnUtils.ymlToObject(map, "pack.overwriter");

        AutoGenerator autoGenerator = new AutoGenerator();

        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)  //??????Ar??????
                .setAuthor(author)
                .setOpen(false)
                .setFileOverride(true)
                .setOutputDir(outputDir + "/src/main/java")
                .setEnableCache(false)// XML ????????????
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setServiceName("I%sService")
                .setMapperName("%sDao");
        autoGenerator.setGlobalConfig(config);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName(driverName)
                .setUsername(username)
                .setPassword(password)
                .setUrl(url);
        autoGenerator.setDataSource(dataSourceConfig);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setTablePrefix(tablePrefix)// ????????????????????????????????????
                .setNaming(NamingStrategy.underline_to_camel)// ??????????????????
                .setExclude(tableExclude)
                .setInclude(tableInclude) //??????????????????
                .setEntityLombokModel(true) //Lombok
                .setEntityTableFieldAnnotationEnable(true)
                .setRestControllerStyle(true);
        autoGenerator.setStrategy(strategyConfig);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packParent)
                .setEntity(packEntity)
                .setMapper(packDao)
                .setService(packService)
                .setController(packController) //????????????????????????,?????? web
                .setServiceImpl(packServiceImpl)
        //.setXml(xml)
        ;
        autoGenerator.setPackageInfo(packageConfig);

        TemplateConfig templateConfig = new TemplateConfig();
        if (!overWriter.contains("po")) {
            templateConfig.setEntity(null);
        }
        if (!overWriter.contains("dao")) {
            templateConfig.setMapper(null);
        }
        if (!overWriter.contains("service")) {
            templateConfig.setService(null);
        }
        if (!overWriter.contains("serviceImpl")) {
            templateConfig.setServiceImpl(null);
        }
        if (!overWriter.contains("controller")) {
            templateConfig.setController(null);
        }
        //???????????????xml,??????????????????
        templateConfig.setXml(null);
        if (overWriter.contains("mapperXml")) {
            // ????????????????????? freemarker
            String templatePath = "/templates/mapper.xml.ftl";
            //????????????????????? velocity
            //String templatePath = "/templates/mapper.xml.vm";
            //?????????????????????
            List<FileOutConfig> focList = new ArrayList<>();
            // ?????????????????????????????????
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // ???????????????????????? ,????????? Entity ????????????????????????????????? xml ????????????????????????????????????
                    return outputDir + "/src/main/resources/mapper/"
                            + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            autoGenerator.setCfg(new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            }.setFileOutConfigList(focList));
        }
        //??????freemarker??????,??????velocity
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.execute();
    }

}
