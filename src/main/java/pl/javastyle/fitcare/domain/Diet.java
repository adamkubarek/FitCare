package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.commons.domain.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "diets")
@Entity
@Getter
@Setter
@ToString(exclude = {"user", "productList"})
public class Diet implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate creationDate;
    private LocalDate executionDate;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "diet")
    private List<MeasuredProduct> productList;

    @Override
    public boolean isPersisted() {
        return this.getId() != null;
    }
}
