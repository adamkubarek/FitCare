package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.commons.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "measured_products")
@Entity
@Getter
@Setter
@ToString(exclude = {"diet", "product"})
public class MeasuredProduct extends BaseEntity {

    private Double unitValue;

    @ManyToOne
    private UnitOfMeasure unitOfMeasure;

    @OneToOne
    private Product product;

    @ManyToOne
    private Diet diet;

}
