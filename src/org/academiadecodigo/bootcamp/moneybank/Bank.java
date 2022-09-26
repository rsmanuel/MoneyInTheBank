package org.academiadecodigo.bootcamp.moneybank;

public class Bank {

    public static Bank[] ibans = new Bank[1000];

    private double balance;

    private int publicId;

    public Bank (double initialBalance) {
        this.balance = initialBalance;
        this.publicId = (int)(Math.random() * 1000000000) + 100000000;
        Bank.ibans[Login.logins - 1] = this;
    }

    public double getBalance() {
        return balance;
    }

    public int getPublicId() {
        return publicId;
    }

    public void setBalance(double value, Operations operation) {
        if (operation == operation.ADD) {
            this.balance += value;
            return;
        }
        this.balance -= value;
    }
}
