package ru.devufa.debt.entity;

import ru.devufa.debt.entity.common.EntityWithId;

import javax.persistence.*;

@Entity
@Table(
        name = "person_settings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"key", "person_id"})
)
public class Settings extends EntityWithId {
    @Column
    @Enumerated(EnumType.STRING)
    private SettingParam key;
    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Settings(SettingParam key, String value, Person person) {
        this(key, value);
        this.person = person;
    }

    public Settings() {
    }

    public Settings(SettingParam key, String value) {
        this.key = key;
        this.value = value;
    }

    public SettingParam getKey() {
        return key;
    }

    public void setKey(SettingParam key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
