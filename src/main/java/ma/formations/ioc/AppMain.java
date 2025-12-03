package ma.formations.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ma.formations.ioc.service.ServiceImpl;

public class AppMain {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainApplication.class)) {
            ServiceImpl service = context.getBean(ServiceImpl.class);
            service.getAll().forEach(article ->
                    System.out.printf("%-3s %-20s %10.2f %5d%n",
                            article.getId(),
                            article.getDescription(),
                            article.getPrice(),
                            article.getQuantity()));
        }
    }
}
