package com.example.tpcheck.Core.LocalDB.Elements;

public class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public boolean isEqual(String s1, String s2) {
        return s1.equals(s2);
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
