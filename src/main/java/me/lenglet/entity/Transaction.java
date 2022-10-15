package me.lenglet.entity;

import jakarta.persistence.*;

@Entity
public class Transaction implements SensitiveContainer {

    @Id
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "client_name"))
    private SensitiveString clientName;

    public void setClientName(String clientName) {
        this.clientName = new SensitiveString(clientName, this);
    }

    @Override
    public boolean isMnpi() {
        return true;
    }
}
