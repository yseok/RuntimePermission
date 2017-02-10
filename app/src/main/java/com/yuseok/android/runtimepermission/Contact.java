package com.yuseok.android.runtimepermission;

import java.util.ArrayList;

/**
 * Created by YS on 2017-02-01.
 */

/**
 * 전화번호부 Pure Old Java Project = POJO
 */

// 각 선언된 정보들의 getter와 setter
public class Contact {
    private int id;
    private String name;
    private ArrayList<String> tel;

    public Contact(){
        tel = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTel() {
        return tel;
    }

    public String getTelOne(){
        if(tel.size() > 0)
            return tel.get(0);
        else
            return null;
    }

    public void setTel(ArrayList<String> tel) {
        this.tel = tel;
    }

    public void addTel(String tel){
        this.tel.add(tel);
    }

    public void removeTel(String tel){
        this.tel.remove(tel);
    }
}

