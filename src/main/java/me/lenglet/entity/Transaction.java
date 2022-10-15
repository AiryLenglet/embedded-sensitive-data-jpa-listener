package me.lenglet.entity;

import me.lenglet.EncryptionService;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(SensitiveDataListener.class)
public class Transaction implements SensitiveContainer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "client_name"))
    private SensitiveString clientName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "transaction_date"))
    private SensitiveDate transactionDate;

    @Transient
    private EncryptionService encryptionService;

    public void setClientName(String clientName) {
        this.clientName = new SensitiveString(clientName, this);
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = new SensitiveDate(transactionDate, this);
    }

    public Long getId() {
        return id;
    }

    public SensitiveString getClientName() {
        return clientName;
    }

    public SensitiveDate getTransactionDate() {
        return transactionDate;
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
