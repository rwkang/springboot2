package shop.onekorea.springboot2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

@Configuration
@EnableJpaRepositories(
        basePackages = "shop.onekorea.springboot2.repository.secondary",
        entityManagerFactoryRef = "secondaryEntityManager",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDatabaseConfig {

    /*
    [application.yml] 파일에서 세팅한 값 가져오기 실험...
     */
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String jpaHibernateDdlAuto;

    @Value("${spring.datasource.hikari.primary.driver-class-name}")
    private String driverClassName1;
    @Value("${spring.datasource.hikari.primary.jdbc-url}")
    private String url1;
    @Value("${spring.datasource.hikari.primary.username}")
    private String username1;
    @Value("${spring.datasource.hikari.primary.password}")
    private String password1;
    @Value("${spring.datasource.hikari.secondary.driver-class-name}")
    private String driverClassName2;
    @Value("${spring.datasource.hikari.secondary.jdbc-url}")
    private String url2;
    @Value("${spring.datasource.hikari.secondary.username}")
    private String username2;
    @Value("${spring.datasource.hikari.secondary.password}")
    private String password2;

    @Bean
    public PlatformTransactionManager secondaryTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(secondaryEntityManager().getObject());

        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManager() {

        System.err.println(getInfo() + " jpaHibernateDdlAuto: " + jpaHibernateDdlAuto);
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(secondaryDataSource());
        em.setPackagesToScan(new String[]{"shop.onekorea.springboot2.entity.secondary"});
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create");
//        properties.put("hibernate.hbm2ddl.auto", "update");

        em.setJpaPropertyMap(properties);

        System.err.println(getInfo() + " jpaHibernateDdlAuto: " + jpaHibernateDdlAuto);

        return em;

    }

    // DB 등록
    @Bean
    public DataSource secondaryDataSource() {

        System.err.println(getInfo() + " driverClassName1: " + driverClassName1);
        System.err.println(getInfo() + " driverClassName2: " + driverClassName2);
        System.err.println(getInfo() + " url1: " + url1);
        System.err.println(getInfo() + " url2: " + url2);

        return DataSourceBuilder.create()
                .driverClassName(driverClassName2)
                .url(url2)
                .username(username2)
                .password(password2)
                .build();
//        return DataSourceBuilder.create()
//                .driverClassName("oracle.jdbc.OracleDriver")
//                .url("jdbc:oracle:thin:@localhost:1521/xe")
//                .username("scott")
//                .password("tiger")
//                .build();
    }

}
