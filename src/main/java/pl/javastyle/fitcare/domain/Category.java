package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.core.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Table(name = "categories")
@Entity
@Getter
@Setter
@ToString(exclude = {"user", "products"})
@NoArgsConstructor
public class Category extends BaseEntity {

    @Column(unique = true)
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    @ManyToOne
    private User user;

}
