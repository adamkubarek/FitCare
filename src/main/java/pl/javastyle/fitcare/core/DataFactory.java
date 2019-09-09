package pl.javastyle.fitcare.core;

public interface DataFactory {

    BaseEntity createEntity(BaseDTO baseDTO);

    BaseDTO createDTO(BaseEntity baseEntity);
}
