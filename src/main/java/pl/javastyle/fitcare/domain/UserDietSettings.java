package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "diet_setttings")
@Entity
@Getter
@Setter
@ToString
public class UserDietSettings implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numberOfMeals;
    private Double caloriesLimit;
    private Double proteinLimit;
    private Double fatLimit;
    private Double carbsLimit;

    @Override
    public boolean isPersisted() {
        return this.getId() != null;
    }
}
