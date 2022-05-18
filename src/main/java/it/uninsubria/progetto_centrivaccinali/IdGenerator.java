package it.uninsubria.progetto_centrivaccinali;

import java.util.*;

public class IdGenerator {
    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    /*
    public static void main(String[] args) {
        for (int i=0; i<5; i++){
            int id=0;
            do {
                id=generateUniqueId();
            }while(id>99999999);
            System.out.println(id);
        }
    }
     */
}
