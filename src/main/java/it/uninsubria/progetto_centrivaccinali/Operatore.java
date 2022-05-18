package it.uninsubria.progetto_centrivaccinali;

public class Operatore {
    private String username;
    private String password;

    public Operatore(String user, String psw){
        username = user;
        password = psw;
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }

    public String toString(){
        return this.username + this.password;
    }
}
