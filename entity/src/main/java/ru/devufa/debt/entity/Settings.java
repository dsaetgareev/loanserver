package ru.devufa.debt.entity;

import ru.devufa.debt.entity.common.EntityWithId;

import javax.persistence.*;

@Entity
@Table(name = "person_settings")
public class Settings extends EntityWithId {
    @Column
    private String key;
    @Column
    private String value;

    public Settings() {
    }

    public Settings(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
