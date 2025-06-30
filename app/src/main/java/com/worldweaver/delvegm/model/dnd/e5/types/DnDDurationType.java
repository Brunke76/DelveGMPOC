package com.worldweaver.delvegm.model.dnd.e5.types;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.time.DurationType;

public enum DnDDurationType implements DurationType {

    INSTANTANEOUS(R.string.duration_instantaneous_label),
    PERMANENT(R.string.duration_permanent_label),
    BEGINNING_OF_TARGETS_NEXT_TURN(R.string.duration_beginning_of_targets_next_turn_label),
    BEGINNING_OF_YOUR_NEXT_TURN(R.string.duration_beginning_of_your_next_turn_label),
    END_OF_TARGETS_NEXT_TURN(R.string.duration_end_of_targets_next_turn_label),
    END_OF_YOUR_NEXT_TURN(R.string.duration_end_of_your_next_turn_label),
    TURNS(R.string.duration_turns_label, R.string.duration_turns_label_singular, R.string.duration_turns_label_plural),
    ROUNDS(R.string.duration_rounds_label, R.string.duration_rounds_label_singular, R.string.duration_rounds_label_plural),
    SECONDS(R.string.duration_seconds_label, R.string.duration_seconds_label_singular, R.string.duration_seconds_label_plural),
    MINUTES(R.string.duration_minutes_label, R.string.duration_minutes_label_singular, R.string.duration_minutes_label_plural),
    HOURS(R.string.duration_hours_label, R.string.duration_hours_label_singular, R.string.duration_hours_label_plural),
    DAYS(R.string.duration_days_label, R.string.duration_days_label_singular, R.string.duration_days_label_plural),
    WEEKS(R.string.duration_weeks_label, R.string.duration_weeks_label_singular, R.string.duration_weeks_label_plural),
    MONTHS(R.string.duration_months_label, R.string.duration_months_label_singular, R.string.duration_months_label_plural),
    YEARS(R.string.duration_years_label, R.string.duration_years_label_singular, R.string.duration_years_label_plural);

    DnDDurationType(int label) {
        this.label = label;
        this.labelSingular = label;
        this.labelPlural = label;
    }

    DnDDurationType(int label, int labelSingular, int labelPlural) {
        this.label = label;
        this.labelSingular = labelSingular;
        this.labelPlural = labelPlural;
    }

    private final int label;

    private final int labelSingular;

    private final int labelPlural;

    @Override
    public int getLabel() {
        return this.label;
    }

    @Override
    public int getLabelPlural() {
        return this.labelPlural;
    }

    @Override
    public int getLabelSingular() {
        return this.labelSingular;
    }
}
