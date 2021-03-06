package com.ms.userms.models;

public class Info {

    private int id;
    private String name;
    private String phone;
    private boolean isCheck;

    public Info() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public Info(int id, String name, String phone, boolean isCheck) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.isCheck = isCheck;
    }

    public Info(String name, String phone, boolean isCheck) {
        this.name = name;
        this.phone = phone;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
