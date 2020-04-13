package com.jinesh.test.PRG2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Jinesh.Sanghvi on 04/12/2020.
 */
public class PositionSecurityBusinessDateKey {

    private String position;
    private String security;
    private Date businessDate ;

    public PositionSecurityBusinessDateKey(String position, String security, Date businessDate){
        this.position=position;
        this.security=security;
        this.businessDate=businessDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionSecurityBusinessDateKey key = (PositionSecurityBusinessDateKey) o;
        return position.equals(key.position) &&
                security.equals(key.security) &&
                businessDate.equals(key.businessDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, security, businessDate);
    }

    @Override
    public String toString(){
        SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
        return "position " + this.position + " security " + this.security + " business date " + sf.format(this.businessDate);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }
}
