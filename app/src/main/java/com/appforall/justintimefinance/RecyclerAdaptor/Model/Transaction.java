package com.appforall.justintimefinance.RecyclerAdaptor.Model;

public class Transaction {
    private int id;
    private String amount;
    private String description;

    public  Transaction() {  }

    public Transaction(String amount, String description)
    {
        this.setAmount(amount);
        this.setDescription(description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
