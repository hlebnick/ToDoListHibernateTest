package com.hlebnick.todolist.config;

import com.hlebnick.todolist.entities.ToDoListEntity;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static com.hlebnick.todolist.config.DataSourceConfig.DataSourceProperty.*;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:persistence-h2.properties"})
@ComponentScan("com.hlebnick.todolist")
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(env.getRequiredProperty(DRIVER_CLASS_NAME.getValue()));
        basicDataSource.setUrl(env.getRequiredProperty(DATABASE_URL.getValue()));
        basicDataSource.setUsername(env.getRequiredProperty(DATABASE_USERNAME.getValue()));
        basicDataSource.setPassword(env.getRequiredProperty(DATABASE_PASSWORD.getValue()));
        return basicDataSource;
    }

    @Bean
    @Autowired
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        localSessionFactoryBean.setNamingStrategy(new ImprovedNamingStrategy());
        localSessionFactoryBean.setPackagesToScan("com.hlebnick.todolist.entities");
        return localSessionFactoryBean;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        return new HibernateTransactionManager(sessionFactory);
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"));
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.auto"));
                setProperty("hibernate.default_schema", env.getProperty("hibernate.schema"));
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.format_sql", "true");
//                setProperty("hibernate.hbm2ddl.auto", "validate");
            }
        };
    }

    enum DataSourceProperty {
        DRIVER_CLASS_NAME("jdbc.driverClassName"),
        DATABASE_PASSWORD("jdbc.password"),
        DATABASE_URL("jdbc.url"),
        DATABASE_USERNAME("jdbc.username");

        DataSourceProperty(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }
    }
}