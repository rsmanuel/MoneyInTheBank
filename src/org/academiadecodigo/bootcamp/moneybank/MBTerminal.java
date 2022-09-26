package org.academiadecodigo.bootcamp.moneybank;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TopLevelElement;

public class MBTerminal {
    public static void getBalanceMenu(Bank bank, Person user){
        Main.clearTerminal();
        System.out.println("Tem " + Main.ANSI_GREEN + bank.getBalance() + "€" + Main.ANSI_RESET + " no seu banco");
        System.out.print("\nPrima qualquer tecla para voltar ao menu principal: ");
        Main.scanner.next();
        terminal(bank, user);
        return;
    }

    public static void doTransfer(Bank bank, Person user) {
        Main.clearTerminal();
        System.out.print("IBAN do recetor: ");
        String receiver = Main.scanner.next();
        System.out.print("Valor (€): ");
        String amount = Main.scanner.next();
        System.out.print("\nPrima 1 para confirmar: ");
        String option = Main.scanner.next();
        if (option.equals("1") && Integer.parseInt(amount) < bank.getBalance()) {
            for(int i = 0; i < Bank.ibans.length; i++){
                if (Integer.parseInt(receiver) == Bank.ibans[i].getPublicId()){
                    Bank.ibans[i].setBalance(Integer.parseInt(amount), Operations.ADD);
                    bank.setBalance(Integer.parseInt(amount), Operations.REMOVE);
                    terminal(bank, user);
                    return;
                }
            }
        }
        terminal(bank, user);
        return;
    }

    public static void withdrawMenu(Bank bank, Person user){
        Main.clearTerminal();
        System.out.println("1- Inserir valor a levantar");
        System.out.println("0- Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
        String option = Main.scanner.next();
        if(option.equals("1")) {
            System.out.print("Valor (€): ");
            double value = Double.parseDouble(Main.scanner.next());
            if(value > bank.getBalance()) {
                System.out.println("Não foi possível levantar o valor inserido. A voltar ao menu principal...");
                terminal(bank, user);
                return;
            }
            bank.setBalance(value, Operations.REMOVE);
            user.setWallet(value, Operations.ADD);
            terminal(bank, user);
            return;
        } else if (option.equals("0")) {
            terminal(bank, user);
            return;
        }
        System.out.println("A opção que selecionou não foi reconhecida, tente novamente.");
        withdrawMenu(bank, user);
        return;
    }

    public static void depositMenu(Bank bank, Person user){
        Main.clearTerminal();
        System.out.println("1- Inserir valor a depositar");
        System.out.println("0- Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
        String option = Main.scanner.next();
        if(option.equals("1")) {
            System.out.print("Valor (€): ");
            double value = Double.parseDouble(Main.scanner.next());
            if(value > user.getWallet()) {
                System.out.println("Não foi possível depositar o valor inserido. A voltar ao menu principal...");
                terminal(bank, user);
                return;
            }
            bank.setBalance(value, Operations.ADD);
            user.setWallet(value, Operations.REMOVE);
            terminal(bank, user);
            return;
        } else if (option.equals("0")) {
            terminal(bank, user);
            return;
        }
        System.out.println("A opção que selecionou não foi reconhecida, tente novamente.");
        withdrawMenu(bank, user);
        return;
    }

    public static void showIban(Bank bank, Person user) {
        Main.clearTerminal();
        System.out.println("IBAN: " + Main.ANSI_GREEN + bank.getPublicId() + Main.ANSI_RESET + "\n");
        System.out.print("\nPrima qualquer tecla para voltar ao menu principal: ");
        Main.scanner.next();
        terminal(bank, user);
    }

    public static void terminal(Bank bank, Person user) {
        Main.clearTerminal();
        System.out.println("1- Consultar Saldo");
        System.out.println("2- Levantar");
        System.out.println("3- Depositar");
        System.out.println("4- IBAN");
        System.out.println("5- Efetuar Transferência");
        System.out.println("\n0- Fechar Terminal");
        System.out.print("Escolha uma opção: ");
        String option = Main.scanner.next();
        if (option.equals("1")) {
            getBalanceMenu(bank, user);
            return;
        }
        else if (option.equals("2")) {
            withdrawMenu(bank, user);
            return;
        }
        else if (option.equals("3")) {
            depositMenu(bank, user);
            return;
        }
        else if (option.equals("4")) {
            showIban(bank, user);
        }
        else if (option.equals("5")) {
            doTransfer(bank, user);
        }
        else if (option.equals("0")) {
            user.leaveMbTerminal();
        }
        System.out.println("A opção que selecionou não foi reconhecida, tente novamente.");
        terminal(bank, user);
        return;
    }
}
