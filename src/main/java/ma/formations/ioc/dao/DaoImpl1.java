package ma.formations.ioc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ma.formations.ioc.service.model.Article;

@Repository
public class DaoImpl1 implements IDao {

    private final List<Article> articles = new ArrayList<>();

    public DaoImpl1() {
        articles.add(new Article(1L, "PC HP I7", 25000, 5));
        articles.add(new Article(2L, "PC HP I5", 15000, 10));
        articles.add(new Article(3L, "TV LG 32p", 3500, 8));
        articles.add(new Article(4L, "TV SAMSUNG 60p", 9000, 15));
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
