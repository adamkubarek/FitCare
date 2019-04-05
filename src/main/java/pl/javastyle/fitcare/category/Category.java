package pl.javastyle.fitcare.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.core.BaseEntity;
import pl.javastyle.fitcare.product.Product;
import pl.javastyle.fitcare.user.User;

import javax.persistence.*;
import java.util.List;

@Table(name = "categories")
@Entity
@Getter
@Setter
@ToString(exclude = {"user", "products"})
@NoArgsConstructor
public class Category extends BaseEntity {

    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    @ManyToOne
    private User user;
}
