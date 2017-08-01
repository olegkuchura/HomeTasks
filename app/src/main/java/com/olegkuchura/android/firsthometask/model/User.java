package com.olegkuchura.android.firsthometask.model;

import java.util.UUID;

/**
 * Created by Oleg on 24.07.2017.
 */

public class User {

    private int userId;
    private String userName;
    private boolean isOnline;
    private String userAddress;
    private Category category;

    public User(int userId, String userName, boolean isOnline, String userAddress, Category category) {
        this.userId = userId;
        this.userName = userName;
        this.isOnline = isOnline;
        this.userAddress = userAddress;
        this.category = category;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public enum Category {
        FRIENDS, FAMILY, WORK, OTHERS
    }
}
