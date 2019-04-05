package pl.javastyle.fitcare.user.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ActivityRate {
    LOW, MEDIUM, HIGH, EXTREME_HIGH;
}
