package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.commons.domain.BaseEntity;

import javax.persistence.*;

@Table(name = "measured_products")
@Entity
@Getter
@Setter
@ToString(exclude = {"diet", "product"})
public class MeasuredProduct implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double unitValue;

    @ManyToOne
    private UnitOfMeasure unitOfMeasure;

    @OneToOne
    private Product product;

    @ManyToOne
    private Diet diet;

    @Override
    public boolean isPersisted() {
        return this.getId() != null;
    }
}
