package com.example.gymmanagementadmin.model;

public class AdminsInfo {
    private String  userPassword,userFullName, userPhoneNumber, userImageLink;

    public AdminsInfo() {
    }

    public AdminsInfo(String userPassword, String userFullName, String userPhoneNumber, String userImageLink) {
        this.userPassword = userPassword;
        this.userFullName = userFullName;
        this.userPhoneNumber = userPhoneNumber;
        this.userImageLink = userImageLink;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public void setUserImageLink(String userImageLink) {
        this.userImageLink = userImageLink;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getUserImageLink() {
        return userImageLink;
    }

}
