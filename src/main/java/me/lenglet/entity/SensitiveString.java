package me.lenglet.entity;

public class SensitiveString extends AbstractSensitiveData {

    SensitiveString() {
        super();
    }

    public SensitiveString(
            String value,
            SensitiveContainer parent
    ) {
        super(value, parent);
    }
}
