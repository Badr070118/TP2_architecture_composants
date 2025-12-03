package ma.formations.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ma.formations.rest.dao.IDao;
import ma.formations.rest.domaine.ArticleConverter;
import ma.formations.rest.domaine.ArticleDTO;
import ma.formations.rest.service.exception.BusinessException;
import ma.formations.rest.service.service.model.Article;

@Service
@AllArgsConstructor
public class ServiceImpl implements IService {

    private final IDao dao;

    @Override
    public ArticleDTO getById(Long id) {
        Article article = dao.findById(id);
        if (article == null) {
            throw new BusinessException("no article with id= %s exist".formatted(id));
        }
        return ArticleConverter.toDTO(article);
    }

    @Override
    public List<ArticleDTO> getAll() {
        return ArticleConverter.toDTOs(dao.findAll());
    }

    @Override
    public void create(ArticleDTO dto) {
        if (dto == null) {
            throw new BusinessException("Article payload should not be null");
        }
        if (dto.getId() == null) {
            dao.save(ArticleConverter.toBO(dto));
            return;
        }
        Article existing = dao.findById(dto.getId());
        if (existing != null) {
            throw new BusinessException("Article with the same Id=%s exist in database".formatted(dto.getId()));
        }
        dao.save(ArticleConverter.toBO(dto));
    }

    @Override
    public void update(Long id, ArticleDTO dto) {
        if (id == null) {
            throw new BusinessException("Article id=%s should not be null".formatted(id));
        }
        Article existing = dao.findById(id);
        if (existing == null) {
            throw new BusinessException("No article with the id=%s exist in database".formatted(id));
        }
        if (dto == null) {
            throw new BusinessException("Article payload should not be null");
        }
        dto.setId(id);
        dao.save(ArticleConverter.toBO(dto));
    }

    @Override
    public void deleteById(Long id) {
        Article existing = dao.findById(id);
        if (existing == null) {
            throw new BusinessException("no article with id= %s exist".formatted(id));
        }
        dao.deleteById(id);
    }
}