package ru.devufa.debt.entity.common;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class EntityWithoutId {
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created;

    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modified;

    @PrePersist
    public void prePersist() {
        if(this.created == null) {
            this.created = new Date();
        }
        if(this.modified == null) {
            this.modified = new Date();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.modified = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
