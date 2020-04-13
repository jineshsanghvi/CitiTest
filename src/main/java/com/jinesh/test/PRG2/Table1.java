package com.jinesh.test.PRG2;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Jinesh.Sanghvi on 04/11/2020.
 */
public class Table1
{
    private String security;
    private String position ;
    private String identifier ;
    private BigDecimal qty ;
    private Date businessDate ;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public Table1(String security, String position, String identifier, String qty, String businessDate )
    {
        this.security=security;
        this.position=position;
        this.identifier=identifier;
        setBusinessDate(businessDate);
        setQty(qty);
    }

    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return "position " + position + " security " + security + " identifier " + identifier + " business_date " + dateFormat.format(businessDate) + " qty " + this.qty ;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(String qty) {
        if(qty != null)
            setQty(new BigDecimal(qty));
    }

    public void setQty(BigDecimal qty) {
        this.qty = formatBigDecimal(qty) ;
    }

    private BigDecimal formatBigDecimal(BigDecimal bd) {
        if(bd != null)
            bd.setScale(2, BigDecimal.ROUND_HALF_EVEN).stripTrailingZeros() ;
        return bd;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public void setBusinessDate(String businessDate) {
        if(businessDate != null)
            this.businessDate = Date.from(LocalDate.parse(businessDate, dateTimeFormatter).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()) ;
    }
}
