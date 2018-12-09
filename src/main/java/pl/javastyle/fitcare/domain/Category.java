package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.commons.domain.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Table(name = "categories")
@Entity
@Getter
@Setter
@ToString(exclude = {"user", "products"})
@NoArgsConstructor
public class Category implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    @ManyToOne
    private User user;

    @Override
    public boolean isPersisted() {
        return this.getId() != null;
    }
}
