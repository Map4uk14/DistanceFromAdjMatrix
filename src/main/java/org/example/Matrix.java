package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
public class Matrix extends HUB {
    public int[][] berechnenMatrix(int gradZahl) {
        int[][] tmp;

        if (gradZahl > 1) { //check recursion
            tmp = new int[len][len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    for (int l = 0; l < len; l++) {
                        tmp[i][j] += matrix[i][l] * resultTemp[l][j]; // Matrix multiplication
                    }
                }
            }
            resultTemp = tmp;
            return berechnenMatrix(gradZahl - 1); // Recursion
        } else {
            finalResult = resultTemp;
            resultTemp = matrix;
            return finalResult;
        }
    }

    public int[][] distanzMatrixv1() {
        int[][] distanzMat = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                distanzMat[i][j] = matrix[i][j];
                if (distanzMat[i][j] == 0 && i != j) {
                    distanzMat[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int h = 0;
        while (h < len - 1) {
            berechnenMatrix(k);
            for (int p = 0; p < len; p++) {
                for (int l = 0; l < len; l++) {
                    if (distanzMat[p][l] == Integer.MAX_VALUE && finalResult[p][l] > 0) {
                        distanzMat[p][l] = k;
                    }
                }
            }
            k++;
            h++;
        }
        k = 2;
        return distanzMat;
    }

    public ArrayList<Integer> berechnExzentrizitaeten() {
        exzentrizitaten = new ArrayList<>();
        int[][] distanzMat = distanzMatrixv1();
        for (int i = 0; i < len; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < len; j++) {
                if (max < distanzMat[i][j]) {
                    max = distanzMat[i][j];
                }
            }
            if (max != Integer.MAX_VALUE) {
                exzentrizitaten.add(max);
            } else {
                System.out.println("Graph ist nicht zusammenhaengend!");
            }
        }
        return exzentrizitaten;
    }

    public int[][] wegMatrix() {
        int[][] wayMatrix = new int[len][len];
        for (int i = 0; i < len; i++) { //COPY csv and add 1's in diagonal
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    wayMatrix[i][j] = 1;
                } else {
                    wayMatrix[i][j] = matrix[i][j];
                }
            }
        }
        //code below add 1's in wayMatrics when MatrixMulti[i][j] > 0
        int h = 0;
        while (h < len) {
            berechnenMatrix(h + 1);
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (finalResult[i][j] > 0) {
                        wayMatrix[i][j] = 1;
                    }
                }
            }
            h++;
        }
        return wayMatrix;
    }

    public int componentsCount(int[][] chooseWayMat) {
        HashSet<String> zeilen = new HashSet<>();
        for (int[] array : chooseWayMat) {
            String arrayAsString = Arrays.toString(array);
            zeilen.add(arrayAsString);
        }
        return zeilen.size();
    }

    public void radDurchZentr() { //I merged Rad Durchm. Zentrum nur um Zeit zu sparen (trennen sowieso leicht)
        berechnExzentrizitaeten();
        if (exzentrizitaten.isEmpty()) {
            System.out.println("Graph ist nicht zusammenhaengend!");
        } else {
            berechnExzentrizitaeten();
            ArrayList<String> zentrums = new ArrayList<>();
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (Integer i : exzentrizitaten) {
                if (max < i) {
                    max = i;
                }
                if (min > i) {
                    min = i;
                }
            }

            int a = 0;
            for (int i : exzentrizitaten) {
                a++;
                if (i == min) {
                    zentrums.add(Integer.toString(a));
                }
            }
            System.out.println("\n    Radius/Durchmesser/Zentrum: " +
                    "\nrad(G)=" + min + "; dm(G)=" + max + ";" +
                    " Z(G)= {" + zentrums + "}");
        }
    }

    public ArrayList<Integer> berechArtikulat() {
        ArrayList<Integer> artikul = new ArrayList<>();
        int initialCompCount = componentsCount(wegMatrix());
        int node = 0;
        int g = 0;
        int[][] tempMatrix = new int[len][len];

        for (int i = 0; i < len; i++) { //COPY FROM CSV(matrix) TO tempMatrix
            System.arraycopy(matrix[i], 0, tempMatrix[i], 0, len);
        }

        while (g < len) {
            for (int i = 0; i < len; i++) {//this will reset matrix after it get edited(code below)
                System.arraycopy(tempMatrix[i], 0, matrix[i], 0, len);
            }

            for (int j = 0; j < len; j++) {
                matrix[node][j] = 0;
                matrix[j][node] = 0;
            }
            if (componentsCount(wegMatrix()) > initialCompCount + 1) {
                artikul.add(node + 1);
            }
            node++;
            g++;
        }
        return artikul;
    }

    public ArrayList<String> berechBruecken() {
        ArrayList<String> brueck = new ArrayList<>();
        int[][] tempMatrix = new int[len][len];
        int g = 0;
        int initialCompCount = componentsCount(wegMatrix());
        int firstNode = 0, secondNode = 0;

        for (int i = 0; i < len; i++) {  //SET TMP
            System.arraycopy(matrix[i], 0, tempMatrix[i], 0, len);
        }

        while (g <= (len * (len / 2))) {
            for (int i = 0; i < len; i++) { //RESET MATRIX with TMP
                System.arraycopy(tempMatrix[i], 0, matrix[i], 0, len);
            }

            outerLoop: //BREAK FROM TWO NESTED LOOPs
            for (int i = firstNode; i < len; i++) {
                for (int l = secondNode; l < len; l++) {
                    firstNode = i;
                    secondNode = l;
                    if (matrix[i][l] == 1) {
                        matrix[i][l] = 0; matrix[l][i] = 0;
                        secondNode = l + 1;
                        break outerLoop;
                    }
                }
                if (secondNode >= len - 1) {
                    secondNode = 0;
                }
            }
            if (componentsCount(wegMatrix()) > initialCompCount && secondNode != 0) {
                if (!brueck.contains((secondNode) + ":" + (firstNode + 1))) {
                    brueck.add((firstNode + 1) + ":" + (secondNode));
                }
            }
            g++;
        }
        return brueck;
    }

    public void print2DArray(int[][] mat) {
        System.out.println(Arrays.deepToString(mat)
                .replace("], ", "]\n")
                .replace("[[", "[")
                .replace("]]", "]")
                .replace(String.valueOf(Integer.MAX_VALUE), "∞"));
    }
}
