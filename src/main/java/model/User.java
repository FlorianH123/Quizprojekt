package model;

/**
 * Created by Florian on 13.06.2017.
 */
public class User {
    private String name;
    private String e_mail;
    private String avatar_link;
    private String passwort;

    public User( String e_mail, String passwort, String avatar_link, String name ) {
        this.e_mail = e_mail;
        this.passwort = passwort;
        this.avatar_link = avatar_link;
        this.name = name;
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
}
