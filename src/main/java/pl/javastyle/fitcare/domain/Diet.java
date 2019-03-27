package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.core.BaseEntity;
import pl.javastyle.fitcare.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Table(name = "diets")
@Entity
@Getter
@Setter
@ToString(exclude = {"user", "productList"})
public class Diet extends BaseEntity {

    private String name;
    private LocalDate creationDate;
    private LocalDate executionDate;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "diet")
    private List<MeasuredProduct> productList;

}
