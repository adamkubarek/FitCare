package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.core.BaseEntity;
import pl.javastyle.fitcare.domain.enums.ActivityRate;
import pl.javastyle.fitcare.domain.enums.DietGoal;
import pl.javastyle.fitcare.domain.enums.Gender;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@ToString
public class User extends BaseEntity {

    private String name;
    private Integer age;
    private Integer height;
    private Double weight;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private DietGoal dietGoal;
    @Enumerated(EnumType.STRING)
    private ActivityRate activityRate;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    private List<Category> categories;

    @OneToMany(mappedBy = "user")
    private List<Diet> diets;

    @OneToOne
    private UserDietSettings userDietSettings;

    @OneToOne
    private WeightJournal weightJournal;

}
