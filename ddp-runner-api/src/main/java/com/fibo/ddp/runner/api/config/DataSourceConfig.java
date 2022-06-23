package com.fibo.ddp.runner.api.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.fibo.ddp.common.service.datax.runner.mysql.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    // 将所有前缀为spring.datasource.druid下的配置项都加载DataSource中
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("druidDataSource") DruidDataSource defaultDataSource) {
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        Map<Object,Object> map = new HashMap<>();
        map.put("default", defaultDataSource);
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        return dynamicDataSource;
    }

    @Bean(name = "dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("druidDataSource") DruidDataSource defaultDataSource){
        DataSourceTransactionManager dm = new DataSourceTransactionManager();
        dm.setDataSource(defaultDataSource);
        return dm;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperXmlResource = resolver.getResources("classpath*:**/*Mapper.xml");
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(mapperXmlResource);
        bean.setTypeAliasesPackage("com.fibo.ddp.common.model");
        bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        bean.getObject().getConfiguration().setCacheEnabled(false);
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
