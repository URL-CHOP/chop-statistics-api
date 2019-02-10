package me.nexters.chopstatsapi.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import lombok.extern.slf4j.Slf4j;

/**
 * @author manki.kim
 */
@Configuration
@MapperScan(basePackages = "me.nexters.chopstatsapi.repository.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
@Slf4j
@PropertySource(value = "classpath:application-datasource.yml", ignoreResourceNotFound = true, factory = YamlPropertySourceFactory.class)
public class DatasourceConfig {

	@Bean
	@ConfigurationProperties(prefix = "datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
			.build();
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource());
		return dataSourceTransactionManager;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setFailFast(true);

		SqlSessionFactory sqlSessionFactory = sessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
		// where 문이 없을 때 조회시 최대 row수
		sqlSessionFactory.getConfiguration().setDefaultFetchSize(100);

		return sqlSessionFactory;
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}