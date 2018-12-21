package ru.devufa.debt.sms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SMSCConfig {
    @Value("${sms.login}")
    private String SMSC_LOGIN;     // логин клиента
    @Value("${sms.password}")
    private String SMSC_PASSWORD;  // пароль или MD5-хеш пароля в нижнем регистре
    @Value("${sms.https}")
    private boolean SMSC_HTTPS;         // использовать HTTPS протокол
    @Value("${sms.charset}")
    private String SMSC_CHARSET;       // кодировка сообщения: koi8-r, windows-1251 или utf-8 (по умолчанию)
    @Value("${sms.debug}")
    private boolean SMSC_DEBUG;         // флаг отладки
    @Value("${sms.post}")
    private boolean SMSC_POST;         // Использовать метод POST

    public String getSMSC_LOGIN() {
        return SMSC_LOGIN;
    }

    public void setSMSC_LOGIN(String SMSC_LOGIN) {
        this.SMSC_LOGIN = SMSC_LOGIN;
    }

    public String getSMSC_PASSWORD() {
        return SMSC_PASSWORD;
    }

    public void setSMSC_PASSWORD(String SMSC_PASSWORD) {
        this.SMSC_PASSWORD = SMSC_PASSWORD;
    }

    public boolean isSMSC_HTTPS() {
        return SMSC_HTTPS;
    }

    public void setSMSC_HTTPS(boolean SMSC_HTTPS) {
        this.SMSC_HTTPS = SMSC_HTTPS;
    }

    public String getSMSC_CHARSET() {
        return SMSC_CHARSET;
    }

    public void setSMSC_CHARSET(String SMSC_CHARSET) {
        this.SMSC_CHARSET = SMSC_CHARSET;
    }

    public boolean isSMSC_DEBUG() {
        return SMSC_DEBUG;
    }

    public void setSMSC_DEBUG(boolean SMSC_DEBUG) {
        this.SMSC_DEBUG = SMSC_DEBUG;
    }

    public boolean isSMSC_POST() {
        return SMSC_POST;
    }

    public void setSMSC_POST(boolean SMSC_POST) {
        this.SMSC_POST = SMSC_POST;
    }
}
