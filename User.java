package org.example;

import java.util.Scanner;

public abstract class User {
    protected String email;
    protected String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public abstract boolean login(String email, String password);

    public abstract void showMenu(Scanner scanner);
}