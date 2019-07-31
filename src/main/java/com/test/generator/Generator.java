package com.test.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class Generator {

    /** 需要生成的表 */
    public static String[] tables = {"user_test"};
    /** 剔除表前缀 */
    public static String removeTablePrefix = "";
    /** 剔除字段前缀 */
    public static String removeFieldPrefix = "";

    /** 数据库链接，默认Mysql  */
//    public static String jdbcUrl = "jdbc:mysql://192.168.5.130:3306/tax_caishui?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
    public static String jdbcUrl = "jdbc:mysql://192.168.5.131:3306/test?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
    public static String jdbcUser = "root";
    public static String jdbcPwd = "123456";

    /** 生成实体类作者 */
    public static String author = "song";
    /** 生成实体类后缀，如%sEntity 会生成XXXEntity ; %s则没有后缀 */
    public static String entitySuffix = "%s";

    /** 文件生成的磁盘位置 */
    public static String userDir = System.getProperty("user.dir");
    public static String projectPath = userDir+"/src/main/java";
    /** 基础包名，生成的文件会在基础包名下的pkg包下生成 */
    public static String basePackage = "com.test";
    public static String controllerPkg = "tt";
    public static String servicePkg = "tt";
    public static String entityPkg = "tt";
    public static String mapperPkg = "tt";

    /** 生成的类 各自需要继承的类(类的完整包名)*/
    public static String superController = "";
    public static String superService = "";
    public static String superMapper = "";
    public static String superEntity = "";


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 指定生成那种文件 生成的文件所在路径会再上面指定的包名下
        mpg.setCfg(getCfg(true,true,false,false,false,false));
        // 数据源配置，此处数据库为mysql
        mpg.setDataSource(getDscfg(DbType.MYSQL,"com.mysql.jdbc.Driver"));
        // 全局配置
        mpg.setGlobalConfig(getGlcfg());
        // 包配置
        mpg.setPackageInfo(getPck());
        // 策略配置
        mpg.setStrategy(getStcfg());
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

    /**
     * 自定义配置是否生成文件 true:生成,false:不生成
     * @param entityFlag 是否生成实体类
     * @param mapperFlag 是否生成mapper
     * @param xmlFlag 是否生成mapper xml文件
     * @param controllerFlag 是否生成controller文件
     * @param serviceFlag 是否生成service文件
     * @param serviceImplFlag 是否生成serviceImpl文件
     * @return
     */
    public static InjectionConfig getCfg(boolean entityFlag,boolean mapperFlag,boolean xmlFlag,
                                         boolean controllerFlag,boolean serviceFlag,boolean serviceImplFlag){
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        cfg.setFileCreate((configBuilder,fileType,filePath) -> {
            if(FileType.ENTITY==fileType){
                return entityFlag;
            }
            if(FileType.MAPPER==fileType){
                return mapperFlag;
            }
            if(FileType.XML==fileType){
                return xmlFlag;
            }
            if(FileType.CONTROLLER==fileType){
                return controllerFlag;
            }
            if(FileType.SERVICE==fileType){
                return serviceFlag;
            }
            if(FileType.SERVICE_IMPL==fileType){
                return serviceImplFlag;
            }
            return false;
        });
        return cfg;
    }

    /**
     * 全局配置
     * @return
     */
    public static GlobalConfig getGlcfg(){
        GlobalConfig gc = new GlobalConfig();
        //如果AR模式不启用且没有继承某个实体类 则默认实现Serializable接口
        //gc.setActiveRecord(true); // 是否支持AR模式
        gc.setOutputDir(projectPath);
        gc.setFileOverride(true);//文件覆盖
        gc.setIdType(IdType.AUTO);//主键策略
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseColumnList(true);//xml 生成列
        gc.setBaseResultMap(true);//xml 生成ResultMap
        gc.setEntityName(entitySuffix);//实体后缀 Entity
        gc.setMapperName("%sMapper");//Mapper后缀
        gc.setXmlName("%sMapper");//xml后缀
        gc.setControllerName("%sController");//
        gc.setServiceName("%sService");//服务类 后缀service
        gc.setServiceImplName("%sServiceImpl");//服务实现类 后缀serviceImpl
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setSwagger2(true);//实体属性 Swagger2注解
        //实体类Entity中的时间类型指定生成为Date类型
        gc.setDateType(DateType.ONLY_DATE);
        return gc;
    }
    /**
     * 数据源配置
     * @return
     */
    public static DataSourceConfig getDscfg(DbType dbType,String dbDriver){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(dbType);
        dsc.setUrl(jdbcUrl);
        dsc.setDriverName(dbDriver);
        dsc.setUsername(jdbcUser);
        dsc.setPassword(jdbcPwd);
        return dsc;
    }

    /**
     * 包配置
     * @return
     */
    public static PackageConfig getPck(){
        PackageConfig pc = new PackageConfig();
        pc.setParent(basePackage);
        pc.setController(controllerPkg);
        pc.setMapper(mapperPkg);
        pc.setService(servicePkg);
        pc.setServiceImpl(servicePkg);
        pc.setEntity(entityPkg);
        pc.setXml(mapperPkg);
        return pc;
    }

    /**
     * 策略配置
     * @return
     */
    public static StrategyConfig getStcfg(){
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //剔除表前缀
        if(StringUtils.isNotEmpty(removeTablePrefix)){
            strategy.setTablePrefix(removeTablePrefix);
        }
        //剔除字段前缀
        if(StringUtils.isNotEmpty(removeFieldPrefix)){
            strategy.setFieldPrefix(removeFieldPrefix);
        }
        if(StringUtils.isNotEmpty(superController)){
            strategy.setSuperControllerClass(superController);
        }
        if(StringUtils.isNotEmpty(superService)){
            strategy.setSuperServiceClass(superService);
        }
        if(StringUtils.isNotEmpty(superMapper)){
            strategy.setSuperMapperClass(superMapper);
        }
        if(StringUtils.isNotEmpty(superEntity)){
            strategy.setSuperEntityClass(superEntity);
        }
        //排除生成的公共实体属性 如果有继承父类时 可以启用
        //strategy.setSuperEntityColumns(new String[]{"id"});
        //要生成的表 多个英文,号分割
        strategy.setInclude(tables);
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表

        return strategy;
    }
}
