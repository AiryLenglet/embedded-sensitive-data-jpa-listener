package me.lenglet.entity;

import java.time.LocalDate;

public class SensitiveDate extends AbstractSensitiveData<LocalDate> {

    SensitiveDate() {
        super();
    }

    public SensitiveDate(
            LocalDate value,
            SensitiveContainer parent
    ) {
        super(value.toString(), parent);
    }

    @Override
    public LocalDate value() {
        return LocalDate.parse(this.value);
    }
}
