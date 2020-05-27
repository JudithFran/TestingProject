package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class CodeFragment {

    int revision = -1;
    String filepath = "";
    int startline = -1, endline = -1;
    String changetype = "-1";

    String[] lines = new String[5000];

    public void getFragment() {

        String abs_filepath = "C:/ConQat_Clones/Jabref/version-20/clones-gapped.xml";

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
            System.out.println("error.getFragment." + e);
        }

    }

    public void showFragment() {

        String abs_filepath = "C:/ConQat_Clones/Jabref/version-20/clones-gapped.xml";

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
            System.out.println("error.showFragment." + e);
        }
    }
}

public class ConQat {

    public void testingConQatInputFile() {
        try {
            File fileConQat = new File("C:/ConQat_Clones/Jabref/version-20/clones-gapped.xml"); //All Type

            if (fileConQat.exists()) {

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileConQat))); // All Type

                String str = "";
                int i = -1;
                int j = -1;
                int k = 0;
                String[] sourceFile = new String[500];
                CodeFragment[][] cfFile = new CodeFragment[1000][1000];

                while ((str = br.readLine()) != null) {

                    if(str.contains("<sourceFile ")){
                        sourceFile[k] = str;
                        System.out.println("sourceFile[" + k + "] = " + sourceFile[k]);
                        k++;
                    }

                    if (str.contains("<cloneClass ")) {
                        i++;
                        j = -1;
                        continue;
                    }

                    if (str.contains("<clone ")) {

                        j++;

                        int sourceFileID;
                        cfFile[i][j] = new CodeFragment();

                        cfFile[i][j].startline = Integer.parseInt(str.split("[\"]+")[5].trim());
                        System.out.println("cfFile[" + i + "][" + j + "].startline = " + cfFile[i][j].startline);

                        cfFile[i][j].endline = Integer.parseInt(str.split("[\"]+")[7].trim());
                        System.out.println("cfFile[" + i + "][" + j + "].endline = " + cfFile[i][j].endline);

                        sourceFileID = Integer.parseInt(str.split("[\"]+")[13].trim());
                        System.out.println("sourceFileID = " + sourceFileID);

                        String filePath = "";
                        for(k = 0; sourceFile[k] != null; k++)
                            if(sourceFileID == Integer.parseInt(sourceFile[k].split("[\"]+")[1].trim())) {
                                filePath = sourceFile[k].split("[\"]+")[3].trim();
                                cfFile[i][j].filepath = filePath.split("project/")[1].trim();
                            }
                        System.out.println("cfFile[" + i + "][" + j + "].filepath = " + cfFile[i][j].filepath);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error in testingConQatInputFile() method = " + e);
            e.printStackTrace();
        }
    }
}
