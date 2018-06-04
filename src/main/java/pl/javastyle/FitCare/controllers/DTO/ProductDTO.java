package pl.javastyle.FitCare.controllers.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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
