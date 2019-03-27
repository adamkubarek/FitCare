package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "units_of_measure")
@Entity
@Getter
@Setter
@ToString
public class UnitOfMeasure extends BaseEntity {

    private String name;
    private Double value;

}
