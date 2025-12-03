package ma.formations.rest.dao;

import java.util.List;

import ma.formations.rest.service.service.model.Article;

public interface IDao {

    Article findById(Long id);

    List<Article> findAll();

    void save(Article article);

    void deleteById(Long id);
}