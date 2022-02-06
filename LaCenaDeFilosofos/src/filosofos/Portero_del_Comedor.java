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

public class Portero_del_Comedor {
    
    private int comensales; // Es el número de comensales total (filósofos-1).

     /**
     * Constructor de la clase Portero_del_Comedor
     * 
     * @param comensales
     */

    public Portero_del_Comedor(int comensales){
        this.comensales = (comensales - 1);
    }
    /**
     * Monitor para tomar un comensal de los n-1(filosofos) y poder seguir el proceso de ejecución de los filósofos.
     * 
     * @param id_f ID del fiósofo
     * @throws InterruptedException Posibles errores
     */
    public synchronized void tomarComensal(int id_f) throws InterruptedException{
        while(comensales==0){ // Si no hay comensales libres toca esperar
            this.wait();
        } 
        System.out.println("El Filósofo " + (id_f+1) + " es el comensal " + comensales);
        comensales--; // Conteo de comensales
    }
    
    /**
     * Monitor para soltar un comensal de los n-1(filosofos) y poder seguir el proceso de ejecución de los filósofos.
     * 
     * @param id_f ID del fiósofo
     * @throws InterruptedException Posibles errores
     */
    public synchronized void soltarComensal(int id_f) throws InterruptedException{
        System.out.println("El Filósofo " + (id_f+1) + " ya NO es un comensal y se retira de la mesa.");
        comensales++; // Conteo de comensales
        this.notify(); // Notificación al siguiente de que hay comensal disponible.
    }
}
