package ma.formations.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ma.formations.ioc.dao.DaoImpl1;
import ma.formations.ioc.dao.IDao;

@Configuration
@ComponentScan("ma.formations.ioc")
public class MainApplication {

    // Alternative bean used to illustrate factory-based injection (see ServiceImpl option c)
    @Bean
    @Qualifier("dao1")
    public IDao dao1Bean() {
        return new DaoImpl1();
    }
}
