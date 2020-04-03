package com.yueqian.xk;

import com.github.pagehelper.PageInterceptor;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 之前spring-bean.xml+mybatis.xml配置
 */
@Configuration
@MapperScan(basePackages = "com.yueqian.xk.mapper")
public class MyBatisConfig {
    /**
     * 配置数据源
     * @return
     */
     @Bean(name="dataSource",destroyMethod = "close")
     public BasicDataSource basicDataSource(){
         BasicDataSource dataSource = new BasicDataSource();
         dataSource.setDriverClassName("com.mysql.jdbc.Driver");//数据库驱动
         dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/xk");//数据库路径
         dataSource.setUsername("root");//数据库用户名
         dataSource.setPassword("123456");//数据库密码

         dataSource.setInitialSize(3);//连接池大小
         dataSource.setMaxActive(50);//最大活跃数
         dataSource.setMaxIdle(1);//最大空闲数
         dataSource.setMaxWait(4000);//等待连接时间
         dataSource.setDefaultAutoCommit(false);//关闭自动提交事务
         return dataSource;
     }

     @Bean
     public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
         SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
         //设置要使用的数据源
         factoryBean.setDataSource(dataSource);

         SqlSessionFactory factory = null;
         //设置xml文件中的类所在的包(给实体类设置别名)
         factoryBean.setTypeAliasesPackage("com.yueqian.xk.beans");
         //为了让mybatis自动将下划线分割的列名转换为驼峰表示的属性名，举例：数据库：user_id   java类：userId
         org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
         configuration.setMapUnderscoreToCamelCase(true);
         factoryBean.setConfiguration(configuration);

         //设置映射xml文件的路径
         Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:com/yueqian/xk/mapper/*Mapper.xml");
         factoryBean.setMapperLocations(resources);



         //pageHelper分页配置(要导入对应jar包)
         PageInterceptor  pageInterceptor = new PageInterceptor();
         Properties properties = new Properties();
         pageInterceptor.setProperties(properties);
         factoryBean.setPlugins(new Interceptor[]{pageInterceptor});

         factory =factoryBean.getObject();//转换类型
         return factory;
     }
}
