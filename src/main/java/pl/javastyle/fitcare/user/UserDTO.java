package pl.javastyle.fitcare.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.user.enums.ActivityRate;
import pl.javastyle.fitcare.user.enums.DietGoal;
import pl.javastyle.fitcare.user.enums.Gender;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @JsonIgnore
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private Integer height;

    @NotNull
    private Double weight;

    @NotNull
    private Gender gender;

    @NotNull
    private DietGoal dietGoal;

    @NotNull
    private ActivityRate activityRate;
}
