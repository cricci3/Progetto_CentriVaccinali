package it.uninsubria.progetto_centrivaccinali;

import java.rmi.*;

public class TestAddCentro extends Thread {
    InterfaceRMI stub;
    int myId;

    public TestAddCentro(InterfaceRMI stub, int i) {
        this.stub = stub;
        myId = i;

        start();
    }

    @Override
    public void run() {
        String nome = "Nome_"+myId;
        String cognome = "Cognome_"+myId;
        String nomeCV = "Centro_100";
        String cf =  "codicefiscale__"+myId;
        String datavaccinazione = "23/05/2022";
        String nomeVaccino = "Vaccino_"+myId;
        int id=0;
        do {
            id= IdGenerator.generateUniqueId();
        }while(id>99999999 || id<=9999999);
        System.out.println(id);

        CittadinoVaccinato nuovoVaccinato = new CittadinoVaccinato(nomeCV,id,nome,cognome,cf,datavaccinazione,nomeVaccino);

        boolean response = false;
        try {
            response = stub.addCittadinoVaccinato(nuovoVaccinato);
            if(response){
                System.out.println("Test "+myId+" cittadino aggiunto");
            }else
                System.err.println("Test "+myId+" cittadino NON aggiunto");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
