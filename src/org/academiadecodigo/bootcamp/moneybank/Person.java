package org.academiadecodigo.bootcamp.moneybank;

public class Person {

    private double wallet;

    private Bank personBank;

    private boolean isPersonWorking;

    public Person (){
        this.personBank = new Bank(0);
        this.wallet = 0;
        this.isPersonWorking = false;
    }
    public void openMbTerminal() {
        Main.clearTerminal();
        MBTerminal.terminal(personBank, this);
    }

    public void leaveMbTerminal() {
        Main.initialMenu(this);
        return;
    }

    public double getWallet() {
        return wallet;
    }


    public boolean getIsPersonWorking() {
        return isPersonWorking;
    }

    public void setIsPersonWorking(boolean value) {
        this.isPersonWorking = value;
    }

    public void setWallet(double value, Operations operation) {
        if (operation == operation.ADD) {
            this.wallet += value;
            return;
        }
        this.wallet -= value;
    }

    public void startWorking() {
        setWallet(5, Operations.ADD);
    }
}
