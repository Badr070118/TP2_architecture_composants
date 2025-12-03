package ma.formations.ioc.dao;

import java.util.ArrayList;
import java.util.List;

import ma.formations.ioc.service.model.Article;

public class DaoImpl2 implements IDao {

    private final List<Article> articles = new ArrayList<>();

    public DaoImpl2() {
        articles.add(new Article(1L, "DESCRIPTION_1", 100, 3));
        articles.add(new Article(2L, "DESCRIPTION_2", 300, 11));
        articles.add(new Article(3L, "DESCRIPTION_3", 15000, 33));
        articles.add(new Article(4L, "DESCRIPTION_4", 11000, 4));
    }

    @Override
    public Article findById(Long id) {
        return articles.stream()
                .filter(article -> article.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Article> getAll() {
        return new ArrayList<>(articles);
    }

    @Override
    public Article save(Article article) {
        if (article.getId() == null) {
            long nextId = articles.stream().mapToLong(Article::getId).max().orElse(0L) + 1L;
            article.setId(nextId);
        }
        deleteById(article.getId());
        articles.add(article);
        return article;
    }

    @Override
    public void deleteById(Long id) {
        articles.removeIf(article -> article.getId().equals(id));
    }
}
