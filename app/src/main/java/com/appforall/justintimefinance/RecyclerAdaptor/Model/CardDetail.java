package com.appforall.justintimefinance.RecyclerAdaptor.Model;

public class CardDetail {
    private int id;
    private String bankid;
    private String cardnumber;
    private String expirydate;
    private String cvv;

    public CardDetail(String bankid, String cardnumber, String expirydate, String cvv)
    {
        this.setBankid(bankid);
        this.setCardnumber(cardnumber);
        this.setExpirydate(expirydate);
        this.setCvv(cvv);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
