package me.lenglet.entity;

public interface SensitiveData<T> {

    T value();

    SensitiveContainer getParent();
}
