package ma.formations.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ma.formations.ioc.service.ServiceImpl;
import ma.formations.ioc.service.model.Article;

class TestService {

    @Test
    void shouldReturnTheFirstArticle() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainApplication.class)) {
            ServiceImpl service = context.getBean(ServiceImpl.class);
            Article article = service.findById(1L);
            Assertions.assertNotNull(article, "Article 1 should be present");
            Assertions.assertAll(
                    () -> Assertions.assertEquals(1L, article.getId()),
                    () -> Assertions.assertEquals("PC HP I7", article.getDescription()),
                    () -> Assertions.assertEquals(25000d, article.getPrice()),
                    () -> Assertions.assertEquals(5, article.getQuantity()));
        }
    }
}
