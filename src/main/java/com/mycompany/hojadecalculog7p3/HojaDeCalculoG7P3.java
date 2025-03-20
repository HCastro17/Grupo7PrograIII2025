/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hojadecalculog7p3;

import java.util.Scanner;

class Celda {
    int fila, columna; // Posición de la celda en la hoja de cálculo
    double valor; // Valor almacenado en la celda
    Celda derecha, abajo; // Referencias a la celda a la derecha y abajo

    // Constructor de la celda
    public Celda(int fila, int columna, double valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
        this.derecha = null;
        this.abajo = null;
    }
}

class HojaCalculo {
    private Celda cabeza; // Referencia a la primera celda de la hoja

    // Constructor de la hoja de cálculo
    public HojaCalculo() {
        this.cabeza = null;
    }

    // Método para insertar una nueva celda con un valor
    public void insertar(int fila, int columna, double valor) {
        if (cabeza == null) {
            cabeza = new Celda(fila, columna, valor);
            return;
        }

        Celda actual = cabeza, anterior = null;
        // Se recorre la lista para encontrar la posición de inserción
        while (actual != null && (actual.fila < fila || (actual.fila == fila && actual.columna < columna))) {
            anterior = actual;
            actual = actual.derecha;
        }

        Celda nueva = new Celda(fila, columna, valor);
        if (anterior == null) {
            nueva.derecha = cabeza;
            cabeza = nueva;
        } else {
            nueva.derecha = anterior.derecha;
            anterior.derecha = nueva;
        }
    }

    // Método para modificar el valor de una celda existente
    public void modificar(int fila, int columna, double nuevoValor) {
        Celda actual = cabeza;
        while (actual != null) {
            if (actual.fila == fila && actual.columna == columna) {
                actual.valor = nuevoValor;
                return;
            }
            actual = actual.derecha;
        }
        System.out.println("Celda no encontrada.");
    }

    // Método para eliminar una celda de la hoja de cálculo
    public void eliminar(int fila, int columna) {
        Celda actual = cabeza, anterior = null;
        while (actual != null && (actual.fila != fila || actual.columna != columna)) {
            anterior = actual;
            actual = actual.derecha;
        }
        if (actual == null) return; // Celda no encontrada
        if (anterior == null) cabeza = actual.derecha;
        else anterior.derecha = actual.derecha;
    }

    // Método para obtener el valor almacenado en una celda
    public double obtenerValor(int fila, int columna) {
        Celda actual = cabeza;
        while (actual != null) {
            if (actual.fila == fila && actual.columna == columna) {
                return actual.valor;
            }
            actual = actual.derecha;
        }
        return 0; // Retorna 0 si la celda no existe
    }

    // Método para realizar operaciones matemáticas en un rango de celdas
    public double operar(String operacion, int fila1, int col1, int fila2, int col2) {
        double resultado = 0;
        Celda actual = cabeza;
        while (actual != null) {
            if (actual.fila >= fila1 && actual.fila <= fila2 && actual.columna >= col1 && actual.columna <= col2) {
                switch (operacion) {
                    case "sumar": resultado += actual.valor; break;
                    case "restar": resultado -= actual.valor; break;
                    case "multiplicar": resultado *= actual.valor; break;
                    case "dividir": resultado /= actual.valor; break;
                }
            }
            actual = actual.derecha;
        }
        return resultado;
    }

    // Método para imprimir la hoja de cálculo
    public void imprimirHoja() {
        Celda actual = cabeza;
        while (actual != null) {
            System.out.println("Celda (" + actual.fila + ", " + actual.columna + ") = " + actual.valor);
            actual = actual.derecha;
        }
    }
}

public class HojaDeCalculoG7P3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HojaCalculo hoja = new HojaCalculo();
       
    }
}
