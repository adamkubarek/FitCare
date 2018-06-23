package pl.javastyle.fitcare.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ProductDTO {

    @Getter @Setter
    @JsonIgnore
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String category;
    @Getter @Setter
    private Double calories;
    @Getter @Setter
    private Double protein;
    @Getter @Setter
    private Double carbs;
    @Getter @Setter
    private Double fat;
}
