package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class CodeFragmentiClones {

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

public class iClones {
    public void testingiClonesInputFile() {
        try {
            CodeFragmentConQat[][] cfFile = new CodeFragmentConQat[1000][1000];

            File fileiClones = new File("E:/iClones_Clones/Brlcad/12.txt"); //All Type

            if (fileiClones.exists()) {

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileiClones))); // All Type

                String str = "";
                int i = 0;
                int j = 0;
            }

        } catch (Exception e) {
            System.out.println("Error in testingiClonesInputFile() method = " + e);
            e.printStackTrace();
        }
    }
}
