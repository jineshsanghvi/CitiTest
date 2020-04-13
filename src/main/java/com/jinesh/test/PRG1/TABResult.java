package com.jinesh.test.PRG1;

import java.util.Objects;

/**
 * Created by Jinesh.Sanghvi on 04/08/2020.
 */
public class TABResult {
    private String position ;
    private int value ;

    public TABResult(String position, int value){
        this.position = position;
        this.value = value ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TABResult tabResult = (TABResult) o;
        return value == tabResult.value &&
                position.equals(tabResult.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, value);
    }

    @Override
    public String toString() {
        return "position " + this.position + " sum val " + this.value;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
