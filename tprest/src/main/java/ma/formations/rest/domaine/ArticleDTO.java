package ma.formations.rest.domaine;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDTO {

    private Long id;

    @Size(min = 1, max = 30, message = "description size must be between 1 and 30")
    private String description;

    private Double price;

    @Min(value = 1, message = "The quantity value must be greeter than 1")
    private Double quantity;
}