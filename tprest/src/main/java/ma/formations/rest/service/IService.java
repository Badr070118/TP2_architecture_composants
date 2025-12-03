package ma.formations.rest.service;

import java.util.List;

import ma.formations.rest.domaine.ArticleDTO;

public interface IService {

    ArticleDTO getById(Long id);

    List<ArticleDTO> getAll();

    void create(ArticleDTO dto);

    void update(Long id, ArticleDTO dto);

    void deleteById(Long id);
}