package ma.formations.rest.domaine;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ma.formations.rest.service.service.model.Article;

public final class ArticleConverter {

    private ArticleConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Article toBO(ArticleDTO dto) {
        if (dto == null) {
            return null;
        }
        return Article.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }

    public static ArticleDTO toDTO(Article article) {
        if (article == null) {
            return null;
        }
        return ArticleDTO.builder()
                .id(article.getId())
                .description(article.getDescription())
                .price(article.getPrice())
                .quantity(article.getQuantity())
                .build();
    }

    public static List<Article> toBOs(List<ArticleDTO> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(ArticleConverter::toBO)
                .collect(Collectors.toList());
    }

    public static List<ArticleDTO> toDTOs(List<Article> articles) {
        if (articles == null) {
            return Collections.emptyList();
        }
        return articles.stream()
                .filter(Objects::nonNull)
                .map(ArticleConverter::toDTO)
                .collect(Collectors.toList());
    }
}