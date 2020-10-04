package com.example.myxbox;

public class UsersDB_Class {




    private String Password;
    private String RePassword;
    private String FullName;
    private String PhoneNumber;
    private String Address;
    private String NickName;


    public UsersDB_Class() {

    }

    public UsersDB_Class(String password, String rePassword, String fullName, String phoneNumber, String address, String nickName) {
        Password = password;
        RePassword = rePassword;
        FullName = fullName;
        PhoneNumber = phoneNumber;
        Address = address;
        NickName = nickName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRePassword() {
        return RePassword;
    }

    public void setRePassword(String rePassword) {
        RePassword = rePassword;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

}
