package nao.cycledev.springdb.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("nao.cycledev.springdb.data")
public class DataSourceConfig {

    @Resource
    Environment env;

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spittrdb");
        ds.setUsername("root");
        ds.setPassword("");

        //ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        //ds.setUrl(env.getProperty("jdbc.url"));
        //ds.setUsername(env.getProperty("jdbc.username"));
        //ds.setPassword(env.getProperty("jdbc.password"));

        return ds;

    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource ds) {

        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(ds);
        sfb.setPackagesToScan(new String[]{"nao.cycledev.springdb.model" });
        //sfb.setAnnotatedClasses(
        //        new Class<?>[] { Spitter.class }
        //);
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        sfb.setHibernateProperties(props);
        return sfb;

    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
