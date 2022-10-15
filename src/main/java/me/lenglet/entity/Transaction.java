package me.lenglet.entity;

import me.lenglet.EncryptionService;

import javax.persistence.*;

@Entity
@EntityListeners(SensitiveDataListener.class)
public class Transaction implements SensitiveContainer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "client_name"))
    private SensitiveString clientName;

    @Transient
    private EncryptionService encryptionService;

    public void setClientName(String clientName) {
        this.clientName = new SensitiveString(clientName, this);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isMnpi() {
        return true;
    }

    @Override
    public EncryptionService getEncryptionService() {
        return encryptionService;
    }

    @Override
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
}
