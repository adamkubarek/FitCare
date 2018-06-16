package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categories")
@ToString(exclude = "products")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;
    @OneToMany(mappedBy = "category")
    @Getter @Setter
    private List<Product> products;

    public boolean isPersisted() {
        return this.getId() != null;
    }
}
