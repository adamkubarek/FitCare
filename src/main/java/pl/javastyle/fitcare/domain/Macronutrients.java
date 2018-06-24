package pl.javastyle.fitcare.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Macronutrients {

    @Getter @Setter
    @NotNull
    private Double protein;
    @Getter @Setter
    @NotNull
    private Double carbohydrates;
    @Getter @Setter
    @NotNull
    private Double fat;
}