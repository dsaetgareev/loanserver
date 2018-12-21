package ru.devufa.debt.entity;

import ru.devufa.debt.entity.common.EntityWithId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person extends EntityWithId {
    @Column(nullable = false, unique = true)
    private String telephoneNumber;
    @Column
    private String password;//todo encode
    @Column
    private String photo;
    @Column
    private String email;
    @Column
    private String question;
    @Column
    private String answer;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "settings_id")
    private List<Settings> settings;
    /*
        Ожидает ли регистрации участника
     */
    @Column
    private boolean isWaitingForPersonRegistration;

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Settings> getSettings() {
        if (settings == null) {
            return new ArrayList<>();
        }
        return settings;
    }

    public void setSettings(List<Settings> settings) {
        this.settings = settings;
    }

    public boolean isWaitingForPersonRegistration() {
        return isWaitingForPersonRegistration;
    }

    public void setWaitingForPersonRegistration(boolean waitingForPersonRegistration) {
        isWaitingForPersonRegistration = waitingForPersonRegistration;
    }
}
