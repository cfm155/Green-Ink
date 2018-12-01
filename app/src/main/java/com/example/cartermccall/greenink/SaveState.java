package com.example.cartermccall.greenink;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SaveState extends RealmObject implements Serializable {
    @PrimaryKey
    private int save = 1;
    private Country country;
    private int year, month, dangerCount;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDangerCount() {
        return dangerCount;
    }

    public void setDangerCount(int dangerCount) {
        this.dangerCount = dangerCount;
    }
}
