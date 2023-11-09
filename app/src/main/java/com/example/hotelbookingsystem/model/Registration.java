package com.example.hotelbookingsystem.model;


public class Registration {
    private String reg_user;
    private String reg_pwd;
    private String reg_last;
    private String reg_first;
    private String name_on_card;
    private String card_num;
    private String expiry_date;
    private String reg_staddr;
    private String reg_city;
    private String reg_state;
    private String reg_zip;
    private String reg_email;
    private String reg_phone;
    private String reg_ctype;

    public Registration(String reg_user, String reg_pwd, String reg_last, String reg_first, String name_on_card, String card_num, String expiry_date, String reg_staddr, String reg_city, String reg_state, String reg_zip, String reg_email, String reg_phone, String reg_ctype) {
        this.reg_user = reg_user;
        this.reg_pwd = reg_pwd;
        this.reg_last = reg_last;
        this.reg_first = reg_first;
        this.name_on_card = name_on_card;
        this.card_num = card_num;
        this.expiry_date = expiry_date;
        this.reg_staddr = reg_staddr;
        this.reg_city = reg_city;
        this.reg_state = reg_state;
        this.reg_zip = reg_zip;
        this.reg_email = reg_email;
        this.reg_phone = reg_phone;
        this.reg_ctype = reg_ctype;
    }

    public String getReg_user() {
        return reg_user;
    }

    public void setReg_user(String reg_user) {
        this.reg_user = reg_user;
    }

    public String getReg_pwd() {
        return reg_pwd;
    }

    public void setReg_pwd(String reg_pwd) {
        this.reg_pwd = reg_pwd;
    }

    public String getReg_last() {
        return reg_last;
    }

    public void setReg_last(String reg_last) {
        this.reg_last = reg_last;
    }

    public String getReg_first() {
        return reg_first;
    }

    public void setReg_first(String reg_first) {
        this.reg_first = reg_first;
    }

    public String getName_on_card() {
        return name_on_card;
    }

    public void setName_on_card(String name_on_card) {
        this.name_on_card = name_on_card;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getReg_staddr() {
        return reg_staddr;
    }

    public void setReg_staddr(String reg_staddr) {
        this.reg_staddr = reg_staddr;
    }

    public String getReg_city() {
        return reg_city;
    }

    public void setReg_city(String reg_city) {
        this.reg_city = reg_city;
    }

    public String getReg_state() {
        return reg_state;
    }

    public void setReg_state(String reg_state) {
        this.reg_state = reg_state;
    }

    public String getReg_zip() {
        return reg_zip;
    }

    public void setReg_zip(String reg_zip) {
        this.reg_zip = reg_zip;
    }

    public String getReg_email() {
        return reg_email;
    }

    public void setReg_email(String reg_email) {
        this.reg_email = reg_email;
    }

    public String getReg_phone() {
        return reg_phone;
    }

    public void setReg_phone(String reg_phone) {
        this.reg_phone = reg_phone;
    }

    public String getReg_ctype() {
        return reg_ctype;
    }

    public void setReg_ctype(String reg_ctype) {
        this.reg_ctype = reg_ctype;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "reg_user='" + reg_user + '\'' +
                ", reg_pwd='" + reg_pwd + '\'' +
                ", reg_last='" + reg_last + '\'' +
                ", reg_first='" + reg_first + '\'' +
                ", name_on_card='" + name_on_card + '\'' +
                ", card_num='" + card_num + '\'' +
                ", expiry_date='" + expiry_date + '\'' +
                ", reg_staddr='" + reg_staddr + '\'' +
                ", reg_city='" + reg_city + '\'' +
                ", reg_state='" + reg_state + '\'' +
                ", reg_zip='" + reg_zip + '\'' +
                ", reg_email='" + reg_email + '\'' +
                ", reg_phone='" + reg_phone + '\'' +
                ", reg_ctype='" + reg_ctype + '\'' +
                '}';
    }
}
