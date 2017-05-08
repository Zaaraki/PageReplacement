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
//Ler arquivo e adicionar na lista
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
    
//Algoritmo FIFO
    public static void FIFO() {
        int faltaPag = 0;
        int numQuadros = lista.get(0);
        ArrayDeque<Integer> quadros = new ArrayDeque<>(numQuadros);

        for (int i = 1; i < lista.size(); i++) {
            int valor = lista.get(i);
            if (!quadros.contains(valor)) {
                faltaPag++;
                if (quadros.size() == numQuadros) {//Se cheio, remove o primeiro do deque
                    quadros.removeFirst();
                }
                quadros.addLast(valor); //Se houver espaço, coloca no último lugar do deque
            }
        }

        System.out.println("FIFO " + faltaPag);
    }
//Algoritmo Otimo
    public static void OPT() {
        int faltaPag = 0;
        int numQuadros = lista.get(0);
        ArrayList<Integer> quadros = new ArrayList<>(numQuadros);
    }
//Algoritmo Menos usado recentemente
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
                //Verificar quando os quadros estao cheios
                if (quadros.size() == numQuadros) {
                    int LRU = frequencia[0]; // pega a freq do primeiro item do array de freq
                    
                    int indexLRU = 0;   //seta o indice para 0
                    for (int j = 1; j < frequencia.length; j++) { // vê qual é o menor
                    
                        if (frequencia[j] < LRU) {
                            LRU = frequencia[j]; 
                            indexLRU = j;
                        }
                    }
                    quadros.set(indexLRU, valor); //colocara nos quadros o valor no indice da menor freq
            
                    frequencia[indexLRU] = relogio; //a tabela de freq vai receber o clock no indice (atualizou o clock)
                    
                } else {
                    quadros.add(valor); //adiciona normalmente
                    frequencia[quadros.size() - 1] = relogio; //coloca o relogio naquela posicao
            
                    
                }
            } else {
                frequencia[quadros.indexOf(valor)] = relogio; //so atualiza a tabela com o relogio
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
