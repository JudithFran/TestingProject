package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Deckard {
    public void testingDeckardInputFile(){
        try {
            String[] store = new String[500];
            String[][] store2D = new String[100][100];

            File file = new File("C:/Deckard_Clones/Brlcad/Repository/version-10/clusters/cluster_vdb_30_5_allg_0.95_30");

            if (file.exists()) {

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

                String str = "";
                int i = 0;

                while ((str = br.readLine()) != null) {

                    store[i] = str;
                    i++;
                }
            }

            for(int i = 0; i<store.length ; i++)
                if(store[i] != null)
                    System.out.println("store[" + i + "] = " + store[i]);

            int m = 0;
            int n = 0;
            int flag = 0;

            for(int i = 0; i<store.length ; i++)
                if(store[i] != null)
                    if(!store[i].isEmpty()) {
                        flag = 1;
                        store2D[m][n] = store[i];
                        n++;
                    }
                    else if(flag == 1){
                        m++;
                        n = 0;
                        flag =0;
                    }


            for(int i = 0; i<store2D.length ; i++) {
                System.out.println("i = " + i);
                for (int j = 0; j < store2D.length; j++) {
                    System.out.println("j = " + j);
                    if (store2D[i][j] != null)
                        System.out.println("store2D[" + i + "][" + j + "] = " + store2D[i][j]);
                    else
                        break;
                }
                if(store2D[i][0] == null)
                    break;
            }


        } catch (Exception e) {
            System.out.println("Error in testingDeckardInputFile() method = " + e);
            e.printStackTrace();
        }
    }
}
