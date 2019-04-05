package pl.javastyle.fitcare.user.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DietGoal {
    LOOSE_WEIGHT, KEEP_WEIGHT, GAIN_WEIGHT;
}
