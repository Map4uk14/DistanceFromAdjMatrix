package org.example;

import java.io.*;
import java.util.ArrayList;
public abstract class HUB {

//haiiii
    protected int[][] finalResult;
    protected int[][] resultTemp;
    protected int[][] matrix;
    protected int k;
    protected int len; // len is number of nodes
    protected ArrayList<Integer> exzentrizitaten;


    public HUB() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Matrix.csv"))) {
            String line = br.readLine();

            len = line.split(";").length;

            matrix = new int[len][len];
            for (int i = 0; i < len; i++) {
                if (line != null) {
                    String[] strArray = line.split(";");
                    for (int j = 0; j < len; j++) {
                        if (!strArray[j].isEmpty() || strArray[j] != null || !strArray[j].isBlank()) {
                            if (Integer.parseInt(strArray[j]) >= 2) { //
                                throw new IllegalArgumentException("CSV input has wrong number: " + strArray[j]);
                            }
                            matrix[i][j] = Integer.parseInt(strArray[j]);
                        }
                    }
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Buffred reader error: " + e);
        }
        this.resultTemp = matrix;
        this.finalResult = new int[len][len];
        k = 2;
    }
}
