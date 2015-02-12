/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuadradomagico;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Copete
 */
public class MagicSquare {
    
    private final int SIZE_SQUARE = 3;
    private int size;
    private int[][] matrix;
    private Random numberNew = new Random();
    private ArrayList<Integer> list;
    private ArrayList<Integer> auxList;
    private int sumaCorrecta;
    
    public MagicSquare(int size){
        this.size = size;
        generateSquare();
    }
    public MagicSquare(){
        this.size = SIZE_SQUARE;
        generateSquare();
    }
    
    private void generateSquare(){
        
        //Generamos una Lista con todos los numeros
        generateList();
        
        //Muestra la suma correcta
        sumaCorrecta = getSumCorrect();
        System.out.println("Suma: " + sumaCorrecta);
        
        //Guardamos el Numero de Combinaciones
        long combinaciones = 0;
        
        while (!newAlternateSquare()){
            combinaciones++;
            
            if ((combinaciones % 1000000) == 0.0)
                System.out.println("Se han alcanzado " + combinaciones + " combinaciones");
        }
        printSquare();
        System.out.println("Numero de Combinaciones: " + combinaciones);
        
    }
    
    private int getSumCorrect(){
        return ((SIZE_SQUARE * SIZE_SQUARE * SIZE_SQUARE) + SIZE_SQUARE) / 2;
    }
    
    private boolean newSquare(){
        
        //Declaramos el tamaño de la Matriz
        matrix = new int[size][size];
        
        //Clonamos la lista Original
        auxList = (ArrayList<Integer>) list.clone();

        //Generamos y comprobamos la Matriz
        for (int x=0; x<size; x++){
            //Generamos la Fila y Comprobamos la Suma
            if (generaFila(x) != sumaCorrecta)
                return false;
            //Generamos la Columna y Comprobamos la Suma
            else if (generaColu(x) != sumaCorrecta)
                return false;
        }
        //Comprobamos la Primera Diagonal
        if (getDiagonalA() != sumaCorrecta)
            return false;
        //Comprobamos la Segunda Diagonal
        else if (getDiagonalB() != sumaCorrecta)
           return false;

        //Todo ha ido bien
        return true;
    }
    
    private int generaFila(int fila){        
        
        int suma = 0;
        
        for (int x=0; x<size; x++){
            
            if (x >= fila)
                matrix[fila][x] = getNexNumber(); 
           
            suma = suma + matrix[fila][x];
        }
        return suma;
    }
    
    private int generaColu(int colu){        
        
        int suma = 0;
        
        for (int x=0; x<size; x++){
            
            if (x > colu)
                matrix[x][colu] = getNexNumber(); 
           
            suma = suma + matrix[x][colu];
        }
        return suma;
    }
    
    private int getDiagonalA(){        
        
        int suma = 0;
        
        for (int x=0; x<size; x++)           
            suma = suma + matrix[x][x];
        
        return suma;
    }

    private int getDiagonalB(){        
        
        int suma = 0;
        
        for (int x=0; x<size; x++)           
            suma = suma + matrix[(size-1) - x][x];
        
        return suma;
    }
    
    //Generamos una Lista con todos los numeros posibles
    private void generateList(){
        //Total de Celdas
        int numCeldas = size * size;
        
        //Reservamos el tamaño para la Lista
        list = new ArrayList();
        
        //Generamos una lista con todos los numeros
        for (int x=0; x<numCeldas; x++)
            list.add(x + 1);
    }
    
    //Retorna un numero al Azar de la lista y luego lo Elimina
    private int getNexNumber(){
        //Calculamos una Posicion al Azar de la Lista
        int posLista = numberNew.nextInt(auxList.size());
        int number = auxList.get(posLista);
        auxList.remove(posLista);
        return number;
    }
    
    public void printSquare(){
        //Recorremos la Matriz
        for (int x=0; x<size; x++){            
            for (int y=0; y<size; y++){
                System.out.print(matrix[x][y] + "   ");
            }
            System.out.println();
        }
    }
    

    
    //Codigo Alternativo
    private boolean newAlternateSquare(){
        
        //Declaramos el tamaño de la Matriz
        matrix = new int[size][size];
        
        //Clonamos la lista Original
        auxList = (ArrayList<Integer>) list.clone();
        
        //Genero y Compruebo la primera Diagonal /
        if (generaDiagonalA() != sumaCorrecta)
            return false;
        //Genero y Compruebo la segunda Diagonal \
        else if (generaDiagonalB() != sumaCorrecta)
            return false;
        
        //Generamos y comprobamos la Matriz
        for (int x=0; x<size; x++){
            //Generamos la Fila y Comprobamos la Suma
            if (generaFilaAlt(x) != sumaCorrecta)
                return false;
            //Generamos la Columna y Comprobamos la Suma
            else if (generaColuAlt(x) != sumaCorrecta)
                return false;
        }
        //Todo ha ido bien
        return true;
    }
    
    
    private int generaDiagonalA(){        

        int suma = 0;
        
        for (int x = 0; x < size; x++) {
            matrix[(size - 1) - x][x] = getNexNumber();
            suma = suma + matrix[(size - 1) - x][x];
        }        
        return suma;        
    }
    private int generaDiagonalB(){

        int suma = 0;

        //Si es Par no se cruzan en medio
        if (esPar(size)){
            for (int x=0; x<size; x++){

                matrix[x][x] = getNexNumber();
                suma = suma + matrix[x][x];
            }
        }
        //Si es Impar se cruzan en medio
        else{
            int posMedio = size / 2;
            
            for (int x=0; x<size; x++){
                if (x != posMedio)
                    matrix[x][x] = getNexNumber();                    

                suma = suma + matrix[x][x];
            }
        }
        return suma;
    }
    private boolean esPar(int x) {
        if ((x % 2) == 0)
            return true;
 
        return false;
    }
    
    private int generaFilaAlt(int fila){        
        
        int suma = 0;
        
        for (int x=0; x<size; x++){
            
            if (x > fila)
                matrix[fila][x] = getNexNumber(); 
           
            suma = suma + matrix[fila][x];
        }
        return suma;
    }
    
    private int generaColuAlt(int colu){        
        
        int suma = 0;
        
        for (int x=0; x<size; x++){
            
            if (x > colu)
                matrix[x][colu] = getNexNumber(); 
           
            suma = suma + matrix[x][colu];
        }
        return suma;
    }
}


