package ma.formations.ioc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ma.formations.ioc.dao.IDao;
import ma.formations.ioc.service.model.Article;

@Service
public class ServiceImpl implements IService {

    private IDao dao;

    // Option a) Setter injection (active by default)
    @Autowired
    public void setDao(@Qualifier("dao1") IDao dao) {
        this.dao = dao;
    }

    /*
     * Option b) Constructor injection.
     * Uncomment the constructor below and comment the setter above to rely solely
     * on constructor injection.
     *
     * public ServiceImpl(@Qualifier("dao1") IDao dao) {
     *     this.dao = dao;
     * }
     */

    /*
     * Option c) Factory-based injection.
     * Keep the field and add the following annotations directly on it to consume
     * the bean named "dao1" exposed from MainApplication.
     *
     * @Autowired
     * @Qualifier("dao1")
     * private IDao dao;
     */

    @Override
    public List<Article> getAll() {
        return dao.getAll();
    }

    @Override
    public Article save(Article article) {
        return dao.save(article);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Article findById(Long id) {
        return dao.findById(id);
    }
}
