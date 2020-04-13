package com.jinesh.test.PRG1;

import java.util.Objects;

/**
 * Created by Jinesh.Sanghvi on 04/08/2020.
 */
public class TabA {
    private int value ;
    private String bd ;

    public TabA(int value, String bd) {
        this.value = value;
        this.bd = bd;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabA taba = (TabA) o;
        return value == taba.value &&
                bd.equals(taba.bd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, bd);
    }

    @Override
    public String toString(){
        return "val " + this.value + " bd " + this.bd;
    }
}
