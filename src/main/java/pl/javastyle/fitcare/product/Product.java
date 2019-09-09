package pl.javastyle.fitcare.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.category.Category;
import pl.javastyle.fitcare.core.BaseEntity;
import pl.javastyle.fitcare.user.User;

import javax.persistence.*;

@Table(name = "products")
@Entity
@Getter
@Setter
@ToString(exclude = {"user", "category"})
@NoArgsConstructor
public class Product extends BaseEntity {

    private String name;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;
    private Double calories;
    @Embedded
    private Macronutrients macronutrients;

    @ManyToOne
    private User user;

    public Product(ProductDTO productDTO) {
        setId(productDTO.getId());
        this.name = productDTO.getName();
        this.calories = productDTO.getCalories();
        this.macronutrients = productDTO.getMacronutrients();
    }

    void fillWithPatcherProperties(ProductDTO patcher) {
        if (patcher.getName() != null) {
            this.setName(patcher.getName());
        }

        if (patcher.getCategory() != null && !patcher.getCategory().isEmpty()) {
            Category incomingCategory = new Category();
            incomingCategory.setName(patcher.getCategory());
            this.setCategory(incomingCategory);
        }

        if (patcher.getMacronutrients() != null) {
            fillEmbeddedMacronutrientsObject(patcher);
        }

        if (patcher.getCalories() != null) {
            this.setCalories(patcher.getCalories());
        }
    }

    private void fillEmbeddedMacronutrientsObject(ProductDTO patcher) {
        Macronutrients patcherMacronutrients = patcher.getMacronutrients();
        Macronutrients productMacronutrients = this.getMacronutrients();

        if (patcherMacronutrients.getCarbohydrates() != null) {
            productMacronutrients.setCarbohydrates(patcherMacronutrients.getCarbohydrates());
        }

        if (patcherMacronutrients.getProtein() != null) {
            productMacronutrients.setProtein(patcherMacronutrients.getProtein());
        }

        if (patcherMacronutrients.getFat() != null) {
            productMacronutrients.setFat(patcherMacronutrients.getFat());
        }
    }
}
