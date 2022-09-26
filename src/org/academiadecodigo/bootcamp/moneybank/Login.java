package org.academiadecodigo.bootcamp.moneybank;

public class Login {
    public static int logins = 0;
    private String username;
    private String password;

    private String name;

    private Person person;

    public Login(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.person = new Person();
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
