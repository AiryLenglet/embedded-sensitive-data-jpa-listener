package me.lenglet.entity;

import me.lenglet.EncryptionService;

public interface SensitiveContainer {
    boolean isMnpi();

    EncryptionService getEncryptionService();

    void setEncryptionService(EncryptionService encryptionService);
}
