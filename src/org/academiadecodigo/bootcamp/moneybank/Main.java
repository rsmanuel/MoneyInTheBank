package org.academiadecodigo.bootcamp.moneybank;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static Scanner scanner = new Scanner(System.in);

    public static Timer timer;

    public static void clearTerminal() {
        for(int i = 0; i < 30 * 300; i++){
            System.out.println("\n");
        }
    }

    public static void initialMenu(Person person) {
        clearTerminal();
        System.out.println("1- Abrir Terminal de Multibanco");
        System.out.println("2- Saldo da Carteira");
        if (!person.getIsPersonWorking()){
            System.out.println("3- Começar a trabalhar");
        } else {
            System.out.println("3- Acabar de trabalhar");
        }
        System.out.println("\n0- Log out");
        System.out.print("Escolha uma opção: ");
        String option = scanner.nextLine();
        if (option.equals("1")) {
            person.openMbTerminal();
            return;
        } else if (option.equals("2")) {
            clearTerminal();
            System.out.println("Tem " + ANSI_GREEN + person.getWallet() + "€" + ANSI_RESET + " na carteira");
            System.out.print("\nPrima qualquer tecla para voltar ao menu principal: ");
            scanner.nextLine();
            initialMenu(person);
            return;
        } else if(option.equals("3")){
            if(person.getIsPersonWorking() == false){
                timer = new Timer();
                person.setIsPersonWorking(true);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        person.startWorking();
                    }
                };
                timer.schedule(task, 2000, 10000);
                initialMenu(person);
                return;
            }
            person.setIsPersonWorking(false);
            timer.cancel();
            timer.purge();
            initialMenu(person);
            return;
        }else if (option.equals("0")) {
            firstMenu();
        }
        System.out.println("A opção que selecionou não foi reconhecida, tente novamente.");
        initialMenu(person);
        return;
    }

    public static Login createUser(String name, String username, String password) {
        clearTerminal();
        System.out.println("Bem vindo!");
        System.out.println("\nVamos criar o seu sistema financeiro!\n\nPrimeiro temos que criar um utilizador\n");
        if(name.equals("")) {
            System.out.print("Nome: ");
            name = scanner.next();
        }
        if(username.equals("")) {
            System.out.print("Nome de utilizador: ");
            username = scanner.next();
        }
        if(password.equals("")){
            System.out.print("Password: ");
            password = scanner.next();
        }
        System.out.print("Repita a password: ");
        String repeatPassword = scanner.next();
        if (!repeatPassword.equals(password)){
            createUser(name, username, password);
            return null;
        }
        Login.logins++;
        return new Login(username, password, name);
    }

    public static Operations loginMenu(String error) {
        clearTerminal();
        if (Login.logins == 0) {
            return Operations.EMPTYLOGINS;
        }
        if (!error.equals("")){
            System.out.println(error);
        }
        System.out.println("Se já pussui uma conta, insira os seus dados:\n");
        System.out.print("Login: ");
        String option = scanner.next();
        int i = 0;
        while (i < login.length && login[i] != null){
            if(!option.equals(login[i].getUsername())) {
                i++;
                continue;
            }
            else if (option.equals(login[i].getUsername()) && i == (login.length - 1)){
                loginMenu("Não foi encontrado o seu username na nossa base de dados! Tente novamente...");
                return null;
            }
            else {
                break;
            }
        }
        if (i == -1){
            i = 0;
        }
        System.out.print("Password: ");
        option = scanner.next();
        System.out.println(i);
        if (login[i].getPassword().equals(option)){
            System.out.println(i);
            initialMenu(login[i].getPerson());
            return null;
        }
        loginMenu("A password está incorreta, tente novamente...");
        return null;
    }

    public static void firstMenu() {
        clearTerminal();
        System.out.println("Bem vindo às suas finanças!\n");
        System.out.println("1- Login");
        System.out.println("2- Criar Utilizador\n");
        System.out.println("0- Fechar programa\n");
        System.out.print("Escolha uma opção: ");
        String option = scanner.nextLine();
        if (option.equals("1")) {
            Operations ret = loginMenu("");
            if (ret == Operations.EMPTYLOGINS) {
                firstMenu();
                return;
            }
            return;
        }
        else if (option.equals("2")) {
            login[Login.logins] = createUser("", "", "");
            firstMenu();
            return;
        }
        else if (option.equals("0")) {
            System.exit(0);
        }
        System.out.println("A opção que selecionou não foi reconhecida, tente novamente.");
        firstMenu();
        return;
    }

    public static Login[] login = new Login[1000];

    public static void main(String[] args) {
        firstMenu();
    }
}
