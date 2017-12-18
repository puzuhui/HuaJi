package com.mingxuan.huaji.layout.four.model;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class MyBankCardModel {

    /**
     * bank : CCB
     * validated : true
     * cardType : DC
     * key : 6236683760009754232
     * messages : []
     * stat : ok
     */

    private String bank;
    private boolean validated;
    private String cardType;
    private String key;
    private String stat;
    private List<?> messages;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<?> getMessages() {
        return messages;
    }

    public void setMessages(List<?> messages) {
        this.messages = messages;
    }

}
