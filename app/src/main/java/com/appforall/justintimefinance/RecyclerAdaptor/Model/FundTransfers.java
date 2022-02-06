package com.appforall.justintimefinance.RecyclerAdaptor.Model;

public class FundTransfers {
    private int id;
    private String amount;
    private String email;

    public FundTransfers() {  }

    public FundTransfers(String amount, String email)
    {
        this.setAmount(amount);
        this.setEmail(email);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
