package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "diet_setttings")
@Entity
@Getter
@Setter
@ToString
public class UserDietSettings extends BaseEntity {

    private Integer numberOfMeals;
    private Double caloriesLimit;
    private Double proteinLimit;
    private Double fatLimit;
    private Double carbsLimit;

}
