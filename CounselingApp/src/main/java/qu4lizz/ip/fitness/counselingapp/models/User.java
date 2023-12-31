package qu4lizz.ip.fitness.counselingapp.models;

public class User {
    private String name;
    private String surname;
    private String username;
    private String mail;

    public User(String name, String surname, String username, String mail) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
