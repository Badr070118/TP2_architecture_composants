package ma.formations.ioc.dao;

import java.util.List;

import ma.formations.ioc.service.model.Article;

public interface IDao {
    Article findById(Long id);

    List<Article> getAll();

    Article save(Article article);

    void deleteById(Long id);
}
