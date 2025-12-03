package ma.formations.rest.service.service.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private Double price;
    private Double quantity;
}