/*
* Estructura de Datos y Algoritmos II - Proyecto #2: "Programación concurrente"
* Algoritmo: "La cena de los filósofos"
* Grupo: 05
* Equipo: 1
 */

package filosofos;

/**
 * Integrantes del equipo:
 * 
 * @autor López Lara Marco Antonio
 * @autor Olivera Martinez Jenyffer Brigitte
 * @autor Télles González Jorge Luis
 */

import java.util.Random;

public class Tenedor {
    
    // Variable para generar números aleatorios:
    private Random random = new Random();
    
    //Guarda temporalmente el número aleatorio creado para poder imprimirlo.
    private int rand;
    
    // ID del Tenedor.
    private int id;
    
    // Determina si está ocupado el tenedor o no.
    private boolean libre = true;
    
    /**
     * Constructor de la clase Tenedor
     * 
     * @param id ID del Tenedor
     */

    public Tenedor(int id){
        this.id = id;
    }
    
    // Crear métodos synchronized => Monitores
    // Solo puede acceder un Thread a la vez.

    /**
     * Monitor para tomar el tenedor derecho y poder seguir el proceso de ejecución de los filósofos.
     * 
     * @param id_f ID del Filósofo
     * @throws InterruptedException Posibles errores
     */

    public synchronized void tomarTenedor(int id_f) throws InterruptedException{

        while(!libre) 
            this.wait();
        System.out.println("El Filósofo " + (id_f+1) + " toma el tenedor derecho:" + (id+1));
        libre = false;
    
    }

    /**
     * Monitor para tomar el tenedor izquierdo y poder seguir el proceso de ejecución de los filósofos,
     * Pero si no consigue tomarlo en un tiempo x retornará false y tendra que salir a pensar y no podra comer,
     * Tendrá que volver a empezar el proceso de comer.
     * 
     * @param id_f ID del Filósofo
     * @return Boolean True: puede continuar tiene los dos tenedores Y False: tiene que volver a empezar el proceso
     * @throws InterruptedException Posibles errores
     */

    public synchronized boolean tomarTenedorIzqdo(int id_f) throws InterruptedException{
        
        while(!libre){

            rand = random.nextInt(1000) + 500;
            this.wait(rand); // Sólo espera aleatoriamente entre 0.5 y 1 seg y si no, retorna false
            System.out.println("\n\nEl filósofo " + (id_f +1) + " esperó " + rand + " milisegundos por el tenedor izquierdo.\n");
            return false;
        }

        System.out.println("El Filósofo " + (id_f+1) + " toma el tenedor izquierdo: " + (id+1));
        libre = false;
        return true;
    }

    /**
     * Monitor para soltar un tenedor izquierdo o derecho y salir a pensar.
     *
     * @param id_f ID del Filósofo
     * @throws java.lang.InterruptedException
    */
    
    public synchronized void soltarTenedor(int id_f) throws InterruptedException {
        libre = true;
        System.out.println("El Filósofo " + (id_f+1) + " suelta el tenedor " + (id+1));
        this.notify();
    }
    
}
