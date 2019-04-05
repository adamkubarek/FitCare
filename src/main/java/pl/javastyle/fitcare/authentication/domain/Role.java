package pl.javastyle.fitcare.authentication.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Table(name = "roles")
@Entity
@Getter
@Setter
@ToString
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleName name;
}
