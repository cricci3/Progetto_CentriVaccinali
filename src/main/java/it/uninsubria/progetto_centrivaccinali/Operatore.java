package it.uninsubria.progetto_centrivaccinali;

public class Operatore {
    private String username;
    private String password;

    /**
     * Costruttore
     * @param user String username operatore
     * @param psw String password operatore
     */
    public Operatore(String user, String psw){
        username = user;
        password = psw;
    }

    /**
     * @param username che si vuole impostare
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password che si vuole impostare
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return parametro username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return parametro password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @return String rappresentante oggetto Operatore
     */
    public String toString(){
        return this.username + this.password;
    }
}
