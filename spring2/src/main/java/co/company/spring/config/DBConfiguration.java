package co.company.spring.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "co.company") // DAO가 스캔안돼서 적어준거
public class DBConfiguration {
	  
	//데이터소스 등록
	@Bean(destroyMethod="close")
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("hr");
		dataSource.setPassword("hr");
		dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}

	// 트랜잭션 매니저 등록	 
    @Bean
    public DataSourceTransactionManager transactionManager() { // 데이터 소스 생성할때
        return new DataSourceTransactionManager(dataSource()); // 생성자에 필요한 __를 넣어준다
    }
    @Bean
    public JdbcTemplate jdbcManager() {
    	return new JdbcTemplate(dataSource());
    }
}
