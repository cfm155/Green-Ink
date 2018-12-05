package com.example.cartermccall.greenink;

import java.io.Serializable;

import io.realm.RealmObject;

public class RealmInt extends RealmObject implements Serializable {
    private int val;

    public RealmInt() {
    }

    public RealmInt(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
