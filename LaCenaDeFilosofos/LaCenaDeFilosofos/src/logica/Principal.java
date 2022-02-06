/*
* Estructura de Datos y Algoritmos II - Proyecto #2: "Programación paralela"
* Algoritmo: "La cena de los filósofos"
* Grupo: 05
* Equipo: 1
* Clase principal del programa.
 */
package logica;

/**
 * Integrantes del equipo:
 *
 * @autor López Lara Marco Antonio
 * @autor Olivera Martinez Jenyffer Brigitte
 * @autor Télles González Jorge Luis
 */
import filosofos.Filosofo;
import filosofos.Tenedor;
import filosofos.Portero_del_Comedor;
import java.util.Timer;
import java.util.TimerTask;

public class Principal {

    public static void main(String[] args) {

        int n = 10;// Cantidad de filosofos y tenedores. El  programa está diseñado para n filososfos. Por cada filosofo hay un tenedor. 

        // Se crea el Array para contener las n instancias de Tenedores:
        Tenedor[] tenedor = new Tenedor[n];

        // Se crea el Array para contener las n instancias de Filósofos:
        Filosofo[] filosofo = new Filosofo[n];

        // Se crea una sola instancia de Portero_del_Comedor:
        Portero_del_Comedor comensal = new Portero_del_Comedor(n);
        /*Se le pasa n al portero por medio de constructor para que asigne la cantidad de comensales,
        * la cual será n-1 para asegurar que siempre haya comensales comiendo
        * es decir, que haya suficientes tenedores para que siempre haya filósofos comiendo.
         */

        // Se crean las n instancias de Tenedores:
        for (int i = 0; i < tenedor.length; i++) {
            tenedor[i] = new Tenedor(i);
        }

        // Se crean las n instancias de Filósofos:
        for (int i = 0; i < filosofo.length; i++) {

            filosofo[i] = new Filosofo(i, tenedor[i], tenedor[(i + 1) % n], comensal);
            // Ahora al filósofo se le pasa: un ID, un tenedor Dercho, un tenedor Izdo, el comensal.
            /* El filósofo toma el tenedor de la izquierda 
            *  y el de la derecha se contabiliza con el módulo(%)
            *  porque cuando llega a n-1 el siguiente es cero.
             */
        }

        // Se echa a andar todos los Filósofos:
        for (int i = 0; i < filosofo.length; i++) {
            filosofo[i].start();
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

    }

    static TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            System.out.println("\n***Ha transcurrido 1 segundo de ejecución!***\n");
        }
    };

}
