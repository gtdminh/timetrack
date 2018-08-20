package com.fxrialab.timetrack.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({"com.fxrialab.timetrack.model","com.fxrialab.timetrack.model.security"})
@PropertySource({"classpath:jpa.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.fxrialab.timetrack.dao","com.fxrialab.timetrack.dao.security"})
public class PersistenceConfig {
	@Autowired
	public Environment env;
	
	@Bean
	public DataSource dataSource() {
		HikariConfig cf = new HikariConfig();
		cf.setUsername(env.getProperty("db.username"));
		cf.setPassword(env.getProperty("db.password"));
		cf.setJdbcUrl(env.getProperty("db.url"));
		cf.setDriverClassName(env.getProperty("jpa.driver.classname"));
		HikariDataSource ds = new HikariDataSource(cf);

		return ds;
	}

	@Bean
	public JpaVendorAdapter jpaAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setDatabasePlatform(env.getProperty("jpa.dialect"));
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);

		return adapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean manager = new LocalContainerEntityManagerFactoryBean();
		manager.setPackagesToScan(new String[]{"com.fxrialab.timetrack.model", "com.fxrialab.timetrack.security.model"});
		manager.setDataSource(dataSource());
		manager.setJpaVendorAdapter(jpaAdapter());
		manager.setJpaProperties(jpaProps());

		return manager;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager txm = new JpaTransactionManager();
		txm.setEntityManagerFactory(emf);

		return txm;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor execeptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties jpaProps(){
		Properties props = new Properties();
		props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		props.setProperty("hibernate.dialect",env.getProperty("jpa.dialect"));
		return props;
	}
}
