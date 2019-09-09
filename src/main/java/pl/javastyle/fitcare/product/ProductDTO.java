package pl.javastyle.fitcare.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.core.BaseDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends BaseDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotNull
    private Double calories;

    @Valid
    private Macronutrients macronutrients;

    ProductDTO(Product product) {
        setId(product.getId());
        this.name = product.getName();
        this.calories = product.getCalories();
        this.macronutrients = product.getMacronutrients();
        this.category = product.getCategory().getName();
    }
}
