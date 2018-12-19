package ru.devufa.debt.entity;

import ru.devufa.debt.entity.common.EntityWithId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class Language extends EntityWithId{
    @Column
    private String name;
    @Column
    private String shortName;
    @Column
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
