package ma.formations.rest.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import ma.formations.rest.domaine.ArticleDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void testGetById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<ArticleDTO> result = restTemplate.exchange(
                "http://localhost:%d/api/articles/id/1".formatted(port),
                HttpMethod.GET,
                entity,
                ArticleDTO.class);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getBody());
        ArticleDTO body = result.getBody();
        Assertions.assertEquals(1L, body.getId());
        Assertions.assertEquals("PC PORTABLE HP I7", body.getDescription());
        Assertions.assertEquals(15000d, body.getPrice());
        Assertions.assertEquals(10d, body.getQuantity());
    }
}
