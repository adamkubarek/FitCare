package pl.javastyle.fitcare.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.domain.Macronutrients;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
public class ProductDTO {

    @Getter @Setter
    @JsonIgnore
    private Long id;
    @Getter @Setter
    @NotBlank
    private String name;
    @Getter @Setter
    @NotBlank
    private String category;
    @Getter @Setter
    @NotNull
    private Double calories;
    @Getter @Setter
    @Valid
    private Macronutrients macronutrients;
}
