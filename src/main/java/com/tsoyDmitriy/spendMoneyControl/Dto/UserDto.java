package com.tsoyDmitriy.spendMoneyControl.Dto;

public class UserDto {

    long id;
    String username;
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public UserDto(long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
