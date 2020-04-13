package com.jinesh.test.PRG1;

import java.util.Objects;

/**
 * Created by Jinesh.Sanghvi on 04/08/2020.
 */
public class TabB {
    private String position ;
    private String bd ;

    public TabB(String position, String bd) {
        this.position = position;
        this.bd = bd ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabB tabb = (TabB) o;
        return position.equals(tabb.position) &&
                bd.equals(tabb.bd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, bd);
    }

    @Override
    public String toString(){
        return "position " + this.position + " bd " + this.bd ;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }
}
