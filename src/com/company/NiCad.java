package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// This will test input file for RQ5 only

class CodeFragmentNiCad {

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

public class NiCad {
    public void testingNiCadInputFile() {
        try {
            CodeFragmentNiCad[] cfFile = new CodeFragmentNiCad[2000];

            File regularXmlFile = new File("C:/RegularClones/Ctags/Repository/version-" + 12 + "_blocks-blind-clones/version-" + 12 + "_blocks-blind-clones-0.30-classes.xml"); //All Type

            if(regularXmlFile.exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(regularXmlFile))); // All Type

                String str;
                int i = 0;

                while((str = br.readLine()) != null){
                    if(str.contains("<source")){
                        cfFile[i] = new CodeFragmentNiCad();
                        cfFile[i].revision = 12;

                        cfFile[i].filepath = str.split("[\"]+")[1].trim();
                        //System.out.println(" i = " + i + " cfFile[i].filepath = " + cfFile[i].filepath);

                        cfFile[i].startline = Integer.parseInt(str.split("[\"]+")[3].trim());
                        //System.out.println(" i = " + i + " cfFile[i].startline = " + cfFile[i].startline);

                        cfFile[i].endline = Integer.parseInt(str.split("[\"]+")[5].trim());
                        //System.out.println(" i = " + i + " cfFile[i].endline = " + cfFile[i].endline);

                        if (cfFile[i].filepath.contains("version-")) {
                            cfFile[i].filepath = cfFile[i].filepath.replaceAll(".ifdefed", "");

                            String[] filePath = cfFile[i].filepath.split("version-\\d*\\/");
                            cfFile[i].filepath = filePath[1];

                            //System.out.println("cfFile[" + i + "] = " + cfFile[i].filepath + " Start Line = " + cfFile[i].startline
                            //+ " End Line = " + cfFile[i].endline);
                        }

                    }

                }

            }
        } catch (Exception e) {
            System.out.println("Error in testingNiCadInputFile() method = " + e);
            e.printStackTrace();
        }
    }
}
