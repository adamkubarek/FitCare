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
    private double calories;
    @Getter @Setter
    private double protein;
    @Getter @Setter
    private double carbs;
    @Getter @Setter
    private double fat;
}
