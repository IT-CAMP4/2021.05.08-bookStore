package pl.camp.it.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.camp.it.dao.impl.HibernateBookDAOStub;
import pl.camp.it.dao.impl.HibernateOrderDAOStub;
import pl.camp.it.dao.impl.HibernateUserDAOStub;

@Configuration
@ComponentScan(basePackages = {
        "pl.camp.it.controllers",
        "pl.camp.it.services",
        "pl.camp.it.session"
})
public class TestConfiguration {

    @Bean
    public HibernateBookDAOStub hibernateBookDAOStub() {
        return new HibernateBookDAOStub();
    }

    @Bean
    public HibernateOrderDAOStub hibernateOrderDAOStub() {
        return new HibernateOrderDAOStub();
    }

    @Bean
    public HibernateUserDAOStub hibernateUserDAOStub() {
        return new HibernateUserDAOStub();
    }
}
