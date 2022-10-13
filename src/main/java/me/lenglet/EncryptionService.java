package me.lenglet;

public interface EncryptionService {
    String decrypt(String cypher);

    String encrypt(String value);
}
