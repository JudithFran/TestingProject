package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;



class CodeFragmentConQat {

    int revision = -1;
    String filepath = "";
    int startline = -1, endline = -1;
    String changetype = "-1";

    String[] lines = new String[5000];

    public void getFragment() {

        String abs_filepath = "E:/Systems_Only/Brlcad/Repository/version-12/" + filepath;

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

        String abs_filepath = "E:/Systems_Only/Brlcad/Repository/version-12/" + filepath;

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

public class ConQat {

    public void testingConQatInputFile() {
        try {
            CodeFragmentConQat[][] cfFile = new CodeFragmentConQat[1000][1000];
            CodeFragmentConQat[][] cfFile1 = new CodeFragmentConQat[1000][1000];
            CodeFragmentConQat[][] cfFile2 = new CodeFragmentConQat[1000][1000];

            File fileConQat = new File("E:/ConQat_Clones/Brlcad/version-12/clones-gapped.xml"); //All Type

            if (fileConQat.exists()) {

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileConQat))); // All Type

                String str = "";
                int i = 0;
                int j = 0;
                int k = 0;
                int flagRegular = 0;
                String[] sourceFile = new String[500];

                // Reading each clone file (each output file of ConQat) and excluding micro-clones
                while ((str = br.readLine()) != null) {

                    if(str.contains("<sourceFile ")){
                        sourceFile[k] = str;
                        System.out.println("sourceFile[" + k + "] = " + sourceFile[k]);
                        k++;
                    }


                    if (str.contains("<cloneClass ")) {
                        if(flagRegular == 1) {
                            i++;
                            flagRegular = 0;
                            j = 0;
                            continue;
                        }
                    }

                    if (str.contains("<clone ")) {

                        int sourceFileID, startLine, endLine;
                        cfFile[i][j] = new CodeFragmentConQat();
                        cfFile[i][j].revision = 12;
                        startLine = Integer.parseInt(str.split("[\"]+")[5].trim());
                        endLine = Integer.parseInt(str.split("[\"]+")[7].trim());
                        int cloneSize = (endLine-startLine)+1;

                        if(cloneSize>4) { // For regular clones
                        //if(cloneSize<5) {    // For micro clones
                            flagRegular = 1;
                            cfFile[i][j].startline = Integer.parseInt(str.split("[\"]+")[5].trim());
                            //System.out.print("cfFile[" + i + "][" + j + "].startline = " + cfFile[i][j].startline);

                            cfFile[i][j].endline = Integer.parseInt(str.split("[\"]+")[7].trim());
                            //System.out.print(" cfFile[" + i + "][" + j + "].endline = " + cfFile[i][j].endline);

                            sourceFileID = Integer.parseInt(str.split("[\"]+")[13].trim());
                            //System.out.print(" sourceFileID = " + sourceFileID);

                            String filePath = "";
                            for (k = 0; sourceFile[k] != null; k++)
                                if (sourceFileID == Integer.parseInt(sourceFile[k].split("[\"]+")[1].trim())) {
                                    filePath = sourceFile[k].split("[\"]+")[3].trim();
                                    cfFile[i][j].filepath = filePath.split("project/")[1].trim();
                                }
                            //System.out.println(" cfFile[" + i + "][" + j + "].filepath = " + cfFile[i][j].filepath);
                            j++;
                        }
                    }

                }
            }

            System.out.println("\ncfFile after excluding micro clones: \n");
            for (int m = 0; m < cfFile.length; m++) {
                for (int n = 0; n < cfFile.length; n++) {
                    if (cfFile[m][n] != null) {
                        System.out.println("cfFile[" + m + "][" + n + "] Revision = " + cfFile[m][n].revision + " File Path = " + cfFile[m][n].filepath
                                + " Start Line = " + cfFile[m][n].startline + " End Line = " + cfFile[m][n].endline);
                    }
                    else
                        break;
                }
            }

            // Discard empty clones from the cfFile array
            int m = 0;
            int n = 0;
            for (int i = 0; i < cfFile.length; i++) {
                n = 0;
                for (int j = 0; j < cfFile.length; j++) {
                    if (cfFile[i][j] != null && cfFile[i][j].startline != -1) {
                        cfFile1[m][n] = cfFile[i][j];
                        n++;
                    }
                    else if (cfFile[i][j] != null && cfFile[i][j].startline == -1) {
                        m--;
                    }
                }
                m++;
            }

            System.out.println("\ncfFile1 after excluding empty clones: \n");
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


            // Discard those clone classes which contain only one clone.
            m = 0;
            n = 0;
            for (int i = 0; i < cfFile1.length; i++) {
                n = 0;
                for (int j = 0; j < cfFile1.length; j++) {
                    if (cfFile1[i][j] != null && cfFile1[i][1] != null) {
                        cfFile2[m][n] = cfFile1[i][j];
                        n++;
                    }
                    else if (cfFile1[i][j] != null && cfFile1[i][1] == null) {
                        System.out.println("Single clone class found.");
                        m--;
                    }
                }
                m++;
            }

            System.out.println("\ncfFile2 after excluding single clone classes: \n");
            for (m = 0; m < cfFile2.length; m++) {
                for (n = 0; n < cfFile2.length; n++) {
                    if (cfFile2[m][n] != null) {
                        System.out.println("cfFile2[" + m + "][" + n + "] Revision = " + cfFile2[m][n].revision + " File Path = " + cfFile2[m][n].filepath
                                + " Start Line = " + cfFile2[m][n].startline + " End Line = " + cfFile2[m][n].endline);
                    }
                    else
                        break;
                }
            }
            //*/

        } catch (Exception e) {
            System.out.println("Error in testingConQatInputFile() method = " + e);
            e.printStackTrace();
        }
    }
}
