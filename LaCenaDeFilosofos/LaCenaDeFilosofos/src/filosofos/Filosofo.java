/*
* Estructura de Datos y Algoritmos II - Proyecto #2: "Programación paralela"
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

public class Filosofo extends Thread{
    
    private Random random = new Random();

    //Guarda temporalmente el número aleatorio creado para poder imprimirlo.
    private int rand;

    private int id;
    private Tenedor izqda, dcha;
    private Portero_del_Comedor comensal;

    /**
     * Constructor de la clase Filosofo
     * 
     * @param id ID del filósofo.
     * @param dcha Tenedor derecho para el filósofo.
     * @param izqda Tenedor izquierdo para el filósofo.
     * @param comensal Comensal que el filósofo debe tener para empezar el  proceso de comer.
     */
    
    public Filosofo(int id, Tenedor dcha, Tenedor izqda, Portero_del_Comedor comensal){
        this.id = id;
        this.dcha = dcha;
        this.izqda = izqda;
        this.comensal = comensal;
    }
    
    @Override
    public void run(){

        while(true){ 

                try {
                
                // Obtener el número de comensal para poder comer.
                comensal.tomarComensal(id);

                // Obtener el Tenedor Derecho.
                dcha.tomarTenedor(id);

                // Obtener el Tenedor Izquierdo.
                if (!izqda.tomarTenedorIzqdo(id)){
                    
                    // Si no se consigue el izquierdo: el filósofo tendra que volver a casilla de salida y volver a obtener el número de comensal:
                    System.out.println("El Filósofo " + (id+1) + " tendrá que soltar el tenedor " + (id+1) + " por exceso de tiempo y salir a pensar.");
                    
                    // Como no ha conseguido el Tenedor izquierdo suelta el derecho
                    dcha.soltarTenedor(id);

                    // Como no ha conseguido el Tenedor izquierdo suelta el comensal
                    comensal.soltarComensal(id);
                    // Y ahora el Filósofo piensa *********************
                    System.out.println("El Filósofo " + (id+1) + " está pensando.");
                    
                    try {
                        rand = random.nextInt(1000) + 100; 
                        // El tiempo que tarda el filósofo en pensar, entre 100 y 1000 milisegundos:
                        Filosofo.sleep(rand);
                        System.out.println("\n\nEl filósofo " + (id+1) + " pensó durante " + rand + " milisegundos.");
                    } catch (InterruptedException ex) {
                        System.out.println("Error. Descripción: " + ex.toString());
                    }
                    // Fin de Ahora el Filósofo piensa *********************
                    
                    continue; // Se vuelve a poner en la casilla de salida y volver a obtener el comensal.
                    //-----------------------------------------------------------------------------------------------------------------
                }
                // Si ha conseguido el Tenedor Izquierdo. El filósofo sigue adelante:

                // Y ahora el Filósofo come =========================================================================================

                System.out.println("El Filósofo " + (id+1) + " está comiendo.");
                // Simular el tiempo que tarda el filósofo en comer, entre 0.5 y 1 segundos:
                try {
                    rand = random.nextInt(1000) + 500;
                    sleep(rand);
                    System.out.println("\n\nEl filósofo " + (id+1) + " comió durante " + rand + " milisegundos.");
                } catch (InterruptedException ex) {
                    System.out.println("Error. Descripción: " + ex.toString());
                } // Fin de Simular el tiempo que tarda el filósofo en comer, entre 0.5 y 1 segundo.
                
                // Fin de Ahora el Filósofo come ====================================================================================
                
                // Suelta el Tenedor izquierdo:
                izqda.soltarTenedor(id);
                // Suelta el Tenedor derecho:
                dcha.soltarTenedor(id);
                // Suelta el comensal:
                comensal.soltarComensal(id);
                
                // Ahora el Filósofo piensa *********************************************************************************************
                System.out.println("El Filósofo " + (id+1) + " está pensando.");
                // El tiempo que tarda el filósofo en pensar, entre 100 y 1000 milisegundos:
                try {
                    rand = random.nextInt(1000) + 100;
                    Filosofo.sleep(rand);
                    System.out.println("\n\nEl filósofo " + (id+1) + " pensó durante " + rand + " milisegundos.");

                } catch (InterruptedException ex) {
                    System.out.println("Error. Descripción: " + ex.toString());
                }
            } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    System.err.println("Se ha producido un error. Descripción: " + ex.toString());
                // Fin de Ahora el Filósofo piensa **************************************************************************************

            }  // Fin de Se repite infinitamente While
        }
    }
}
