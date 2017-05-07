/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algsubs;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Amaro Neto
 *
 * Matrícula: 11427666
 */
public class AlgSubs {

    public static ArrayList<Integer> lista = new ArrayList<>();

    private static ArrayList<Integer> arquivo(String arq) {
        Scanner ler = null;
        try {
            ler = new Scanner(new File(arq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo \n");
            e.getMessage();
        }
        while (ler.hasNext()) {
            lista.add(ler.nextInt());
        }
        ler.close();
        return lista;
    }

    public static void FIFO() {
        int faltaPag = 0;
        int numQuadros = lista.get(0);
        ArrayDeque<Integer> quadros = new ArrayDeque<>(numQuadros);

        for (int i = 1; i < lista.size(); i++) {
            int valor = lista.get(i);
            if (!quadros.contains(valor)) {
                faltaPag++;
                if (quadros.size() == numQuadros) {
                    quadros.removeFirst();
                }
                quadros.addLast(valor);
            }
        }

        System.out.println("FIFO " + faltaPag);
    }

    public static void OPT() {
        int faltaPag = 0;
        int numQuadros = lista.get(0);
        ArrayList<Integer> quadros = new ArrayList<>(numQuadros);
    }

    public static void LRU() {
        int faltaPag = 0;
        int numQuadros = lista.get(0);
        int relogio = 0;
        int[] frequencia = new int[numQuadros];
        ArrayList<Integer> quadros = new ArrayList<>(numQuadros);

        //vai fazendo o mesmo processo que o fifo
        for (int i = 1; i < lista.size(); i++) {
            relogio++;
            int valor = lista.get(i);
            if (!quadros.contains(valor)) {
                faltaPag++;
                //ATÉ AQUI
                //daí vai começar a verificar as coisas
                if (quadros.size() == numQuadros) {
                    int LRU = frequencia[0];
                    int indexLRU = 0;
                    for (int j = 1; j < frequencia.length; j++) {
                        if (frequencia[j] < LRU) {
                            LRU = frequencia[j];
                            indexLRU = j;
                        }
                    }
                    quadros.set(indexLRU, valor);
                    frequencia[indexLRU] = relogio;
                } else {
                    quadros.add(valor);
                    frequencia[quadros.size() - 1] = relogio;
                }
            } else {
                frequencia[quadros.indexOf(valor)] = relogio;
            }

        }
        System.out.println("LRU " + faltaPag);
    }

    public static void main(String[] args) {
        arquivo("src/1.txt");
//        arquivo("src/2.txt");
        FIFO();
        OPT();
        LRU();

    }

}
