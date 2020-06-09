package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class CodeFragmentDeckard {

    int revision = -1;
    String filepath = "";
    int startline = -1, endline = -1;
    String changetype = "-1";

    String[] lines = new String[5000];

    public void getFragment() {

        String abs_filepath = "C:/Deckard_Clones/Brlcad/Repository/version-12/" + filepath;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(abs_filepath)));
            String str = "";

            int line = 0;
            int i = 0;
            while ((str = br.readLine()) != null) {
                line++;
                if (line > endline) {
                    break;
                }
                if (line >= startline && line <= endline) {
                    lines[i] = str.trim();
                    //System.out.println("lines[" + i + "] = " + lines[i]);
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error.getFragment." + e);
        }

    }

    public void showFragment() {

        String abs_filepath = "C:/Deckard_Clones/Brlcad/Repository/version-12/" + filepath;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(abs_filepath)));
            String str = "";

            System.out.println("\n" + revision + ": " + filepath + ", " + startline + " - " + endline);
            //System.out.println("---------------------------------------------------");
            int line = 0;
            int i = 0;
            while ((str = br.readLine()) != null) {
                line++;
                if (line > endline) {
                    break;
                }
                if (line >= startline && line <= endline) {
                    lines[i] = str.trim();
                    i++;
                    //System.out.println(str);
                }
            }
            //System.out.println("---------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Error.showFragment." + e);
        }
    }
}

public class Deckard {
    public void testingDeckardInputFile(){
        try {
            String[] store = new String[1000];
            String[][] store2D = new String[500][500];

            CodeFragmentDeckard[][] cfFile = new CodeFragmentDeckard[10000][10000];
            CodeFragmentDeckard[][] cfFile1 = new CodeFragmentDeckard[10000][10000];

            File file = new File("C:/Deckard_Clones/Brlcad/Repository/version-12/clusters/cluster_vdb_30_5_allg_0.95_30");

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


            for(int i = 0; i < store2D.length ; i++) {
                //System.out.println("i = " + i);
                for (int j = 0; j < store2D.length; j++) {
                    //System.out.println("j = " + j);
                    if (store2D[i][j] != null)
                        System.out.println("store2D[" + i + "][" + j + "] = " + store2D[i][j]);
                    else
                        break;
                }
                if(store2D[i][0] == null)
                    break;
            }

            /*
            // Previous buggy procedure for reading a clone file
            for (int i = 0; i < store2D.length; i++)
                for (int j = 0; j < store2D.length; j++)
                    if (store2D[i][j] != null) {
                        cfFile[i][j] = new CodeFragmentDeckard();
                        cfFile[i][j].revision = 12;
                        cfFile[i][j].filepath = store2D[i][j].split("[ ]+")[1].trim();
                        String[] filePath = cfFile[i][j].filepath.split("version-\\d*\\/");
                        cfFile[i][j].filepath = filePath[1];
                        //System.out.println("**********************Testing for Deckard version. This is inside fileRead method for testing.*******************************");
                        //System.out.println(" i = " + i + " j = " + j + " cfFile[i][j].filepath = " + cfFile[i][j].filepath);
                        cfFile[i][j].startline = Integer.parseInt(store2D[i][j].split("[:]+")[2].trim());
                        //System.out.println(" i = " + i + " j = " + j + " cfFile[i][j].startline = " + cfFile[i][j].startline);
                        String cs = store2D[i][j].split("[:]+")[3].trim();
                        int cloneSize = Integer.parseInt(cs.split("[ ]+")[0].trim());
                        if(cloneSize>4) {
                            cfFile[i][j].endline = cfFile[i][j].startline + cloneSize;
                            //System.out.println(" i = " + i + " j = " + j + " cfFile[i][j].endline = " + cfFile[i][j].endline);
                        }
                    }

             */



            // Reading each clone file (each output file of Deckard) and excluding micro-clones
            int a, b;
            a = 0;
            flag = 0;
            for (int i = 0; i < store2D.length; i++) {
                b = 0;
                for (int j = 0; j < store2D.length; j++){

                    if (store2D[i][j] != null) {

                        String cs = store2D[i][j].split("[:]+")[3].trim();
                        int cloneSize = Integer.parseInt(cs.split("[ ]+")[0].trim());

                        if (cloneSize > 4) {
                            flag = 1;
                            cfFile[a][b] = new CodeFragmentDeckard();
                            cfFile[a][b].revision = 12;

                            cfFile[a][b].startline = Integer.parseInt(store2D[i][j].split("[:]+")[2].trim());
                            //System.out.println(" i = " + i + " j = " + j + " cfFile[i][j].startline = " + cfFile[i][j].startline);

                            cfFile[a][b].endline = (cfFile[a][b].startline + cloneSize) - 1;
                            //System.out.println(" i = " + i + " j = " + j + " cfFile[i][j].endline = " + cfFile[i][j].endline);

                            cfFile[a][b].filepath = store2D[i][j].split("[ ]+")[1].trim();
                            String[] filePath = cfFile[a][b].filepath.split("version-\\d*\\/");
                            cfFile[a][b].filepath = filePath[1];
                            //System.out.println("**********************Testing for Deckard version. This is inside fileRead method for testing.*******************************");
                            //System.out.println(" i = " + i + " j = " + j + " cfFile[i][j].filepath = " + cfFile[i][j].filepath);
                            b++;
                        }
                    }
                    else
                        break;

                    if (store2D[i][0] == null)
                        break;
                }
                if(flag == 1) {
                    a++;
                    flag = 0;
                }
            }
            //*/



            // Discard those clone classes which contain only one clone.
            m = 0;
            n = 0;
            for (int i = 0; i < cfFile.length; i++) {
                n = 0;
                for (int j = 0; j < cfFile.length; j++) {
                    if (cfFile[i][j] != null && cfFile[i][1] != null) {
                        cfFile1[m][n] = cfFile[i][j];
                        n++;
                    }
                    else if (cfFile[i][j] != null && cfFile[i][1] == null) {
                        System.out.println("Single clone class found.");
                        m--;
                    }
                }
                m++;
            }
            //*/

            System.out.println("\ncfFile after excluding micro clones: \n");
            for (m = 0; m < cfFile.length; m++) {
                for (n = 0; n < cfFile.length; n++) {
                    if (cfFile[m][n] != null) {
                            System.out.println("cfFile[" + m + "][" + n + "] Revision = " + cfFile[m][n].revision + " File Path = " + cfFile[m][n].filepath
                                    + " Start Line = " + cfFile[m][n].startline + " End Line = " + cfFile[m][n].endline);
                    }
                    else
                        break;
                }
            }


            System.out.println("\ncfFile1 after excluding single clone classes: \n");
            for (m = 0; m < cfFile1.length; m++) {
                for (n = 0; n < cfFile1.length; n++) {
                    if (cfFile1[m][n] != null) {
                            System.out.println("cfFile1[" + m + "][" + n + "] Revision = " + cfFile1[m][n].revision + " File Path = " + cfFile1[m][n].filepath
                                    + " Start Line = " + cfFile1[m][n].startline + " End Line = " + cfFile1[m][n].endline);
                    }
                    else
                        break;
                }
            }
            //*/

        } catch (Exception e) {
            System.out.println("Error in testingDeckardInputFile() method = " + e);
            e.printStackTrace();
        }
    }
}
