package pl.javastyle.fitcare.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

public abstract class BaseDTO {

    @Getter
    @Setter
    @JsonIgnore
    private Long id;
}
