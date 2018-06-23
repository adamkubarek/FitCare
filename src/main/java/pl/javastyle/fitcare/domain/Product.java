package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Products")
@ToString
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Column(unique = true)
    @Getter @Setter
    private String name;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter @Setter
    private Category category;
    @Getter @Setter
    private Double calories;
    @Getter @Setter
    private Double protein;
    @Getter @Setter
    private Double carbs;
    @Getter @Setter
    private Double fat;

    public boolean isPersisted() {
        return this.getId() != null;
    }
}
