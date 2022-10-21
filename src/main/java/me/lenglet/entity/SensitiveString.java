package me.lenglet.entity;

public class SensitiveString extends AbstractSensitiveData<String> {

    SensitiveString() {
        super();
    }

    public SensitiveString(
            String value,
            SensitiveContainer parent
    ) {
        super(value, parent);
    }

    @Override
    public String value() {
        return this.getStringValue();
    }
}
