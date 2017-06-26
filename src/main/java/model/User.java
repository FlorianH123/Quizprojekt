package model;

import dao.SchnittstelleBenutzer;

/**
 * Created by Florian on 13.06.2017.
 * Containerklasse für einen Benutzer
 */
public class User {
    private String name;
    private String e_mail;
    private String avatar_link;
    private String passwort;
    private int id;

    private SchnittstelleBenutzer schnittBen = new SchnittstelleBenutzer();

    public User() {
        this.e_mail = "";
        this.passwort = "";
        this.avatar_link = "";
        this.name = "";
    }

    public User(String e_mail, String passwort, String avatar_link, String name ) {
        this.e_mail = e_mail;
        this.passwort = passwort;
        this.avatar_link = avatar_link;
        this.name = name;
        this.id = schnittBen.getNextID();

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getAvatar_link() {
        return avatar_link;
    }

    public void setAvatar_link(String avatar_link) {
        this.avatar_link = avatar_link;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("E-Mail: ").append(e_mail).append("\n").append("Passwort: ")
                .append(passwort).append("\n").append("Avatar Link: ")
                .append(avatar_link).append("\n").append("Name: ").append(name);

        return sb.toString();
    }
}
