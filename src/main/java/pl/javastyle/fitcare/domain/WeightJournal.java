package pl.javastyle.fitcare.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.core.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class WeightJournal extends BaseEntity {

    private LocalDateTime dateOfMeasure;
    private Double weight;

}
