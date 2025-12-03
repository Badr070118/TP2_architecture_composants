package ma.formations.ioc.service;

import java.util.List;

import ma.formations.ioc.service.model.Article;

public interface IService {
    List<Article> getAll();

    Article save(Article article);

    void deleteById(Long id);

    Article findById(Long id);
}
