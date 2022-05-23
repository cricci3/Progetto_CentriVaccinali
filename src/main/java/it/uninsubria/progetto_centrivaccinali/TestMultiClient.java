package it.uninsubria.progetto_centrivaccinali;

import java.rmi.*;
import java.rmi.registry.*;

public class TestMultiClient {
    public static void main(String[] args) {
        Registry registro = null;
        try {
            registro = LocateRegistry.getRegistry(1099);
            InterfaceRMI stub = (InterfaceRMI) registro.lookup("CentriVaccinali");

            for(int i=0; i<9; i++){
                new TesterAddCentro(stub, i);
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
