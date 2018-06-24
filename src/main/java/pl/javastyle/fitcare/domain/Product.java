package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.rest.dto.ProductDTO;

import javax.persistence.*;

@Entity
@Table(name = "Products")
@ToString
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Column(unique = true)
    @Getter @Setter
    private String name;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter @Setter
    private Category category;
    @Getter @Setter
    private Double calories;
    @Embedded
    @Getter @Setter
    private Macronutrients macronutrients;


    public boolean isPersisted() {
        return this.getId() != null;
    }

    public void fillWithPatcherProperties(ProductDTO patcher) {
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
