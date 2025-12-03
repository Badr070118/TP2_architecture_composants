package ma.formations.rest.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import ma.formations.rest.service.service.model.Article;

@Repository
public class DaoImpl implements IDao {

    private final List<Article> database = new ArrayList<>(Arrays.asList(
            new Article(1L, "PC PORTABLE HP I7", 15000d, 10d),
            new Article(2L, "ECRAN", 1500d, 10d),
            new Article(3L, "CAMERA LG", 3000d, 10d),
            new Article(4L, "SOURIS", 200d, 10d)));
    private final AtomicLong sequence = new AtomicLong(database.stream()
            .mapToLong(Article::getId)
            .max()
            .orElse(0L));

    @Override
    public Article findById(Long id) {
        if (id == null) {
            return null;
        }
        return database.stream()
                .filter(article -> id.equals(article.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Article> findAll() {
        return database;
    }

    @Override
    public void save(Article article) {
        if (article == null) {
            return;
        }
        if (article.getId() == null) {
            article.setId(sequence.incrementAndGet());
            database.add(article);
            return;
        }
        Article existing = findById(article.getId());
        if (existing == null) {
            database.add(article);
            return;
        }
        int index = database.indexOf(existing);
        database.set(index, article);
    }

    @Override
    public void deleteById(Long id) {
        Article existing = findById(id);
        if (existing != null) {
            database.remove(existing);
        }
    }
}
