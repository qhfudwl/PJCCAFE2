package cafe.pj.jvx330.web.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cafe.pj.jvx330.menu.dao.MenuDao;
import cafe.pj.jvx330.menu.dao.MenuDaoImpl;
import cafe.pj.jvx330.menu.service.MenuService;
import cafe.pj.jvx330.menu.service.MenuServiceImpl;

/**
 * 
 * @author 김보령
 *
 */
@Configuration
@ComponentScan(basePackages = "cafe.pj.jvx330")
@EnableTransactionManagement
public class DataSourceConfig {
	
	@Bean
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
		ds.setUrl("jdbc:derby://localhost:1527/pjccafe");
		ds.setUsername("pjccafe");
		ds.setPassword("pjccafe");
		
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setMaxIdle(10);
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
