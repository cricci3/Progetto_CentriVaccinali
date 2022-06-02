package it.uninsubria.progetto_centrivaccinali;

import java.rmi.*;

/**
 * TesterAddCentro
 * @author Macrina Alessandro mtr. 737128 VA
 * @author Ricci Claudio mtr. 747555 VA
 */
public class TesterAddCentro extends Thread {
    /**
     * Stub Server
     */
    InterfaceRMI stub;
    /**
     * ID
     */
    int myId;

    /**
     * costruttore
     * @param stub riferimento all'interfaccia
     * @param i id thread
     */
    public TesterAddCentro(InterfaceRMI stub, int i) {
        this.stub = stub;
        myId = i;

        start();
    }

    /**
     * run
     */
    @Override
    public void run() {
        String nomeCV = "Centro"+myId;
        String indirizzo = "via "+myId;
        String comune = "Comune"+myId;
        String provincia = String.valueOf(myId);
        int cap =  Integer.parseInt( "210"+myId);
        String tipologia = "hub";

        CentroVaccinale nuovoCentro = new CentroVaccinale(nomeCV, indirizzo, comune, provincia, cap, tipologia);
        boolean response;
        try {
            response = stub.addCentroVaccinale(nuovoCentro);
            if(response){
                System.out.println("Test "+myId+" centro aggiunto");
            }else
                System.err.println("Test "+myId+" centro NON aggiunto");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
