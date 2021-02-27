package pl.javastyle.fitcare.product;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Macronutrients {

    @NotNull
    private Double protein;

    @NotNull
    private Double carbohydrates;

    @NotNull
    private Double fat;
}