package ma.formations.rest.unitaire;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ma.formations.rest.domaine.ArticleDTO;
import ma.formations.rest.service.IService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IService service;

    @Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(Arrays.asList(
                new ArticleDTO(1L, "PC PORTABLE HP I7", 15000d, 10d),
                new ArticleDTO(2L, "ECRAN", 1500d, 10d),
                new ArticleDTO(3L, "CAMERA LG", 3000d, 10d),
                new ArticleDTO(4L, "SOURIS", 200d, 10d)
        ));

        mvc.perform(get("/api/articles/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].description").value("ECRAN"))
                .andExpect(jsonPath("$[2].price").value(3000d))
                .andExpect(jsonPath("$[3].quantity").value(10d));
    }
}
