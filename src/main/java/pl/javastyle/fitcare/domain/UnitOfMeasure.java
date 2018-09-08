package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "units_of_measure")
@Entity
@Getter
@Setter
@ToString
public class UnitOfMeasure implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double value;

    @Override
    public boolean isPersisted() {
        return this.getId() != null;
    }
}
