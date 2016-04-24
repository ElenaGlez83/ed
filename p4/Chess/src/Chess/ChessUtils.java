package Chess;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Clase con utilidades para arrays genéricos:
 *      - Cambiar las posiciones dentro de un array de forma aleatoria
 *      - Concatenar el contenido de dos arrays
 * 
 * @author Javier Morcillo
 */
public class ChessUtils {

    private static final Random random = new Random();

    /**
     * Método que hace una organización aleatoria del array que se le pasa como
     * parámetro.
     *
     * @param <T> La clase de los objetos que hay en el array
     * @param array Array de objetos de tipo T
     */
    static public <T> void randomizeArray(T[] array) {
        // Cómo intentamos ordenar de forma aleatoria el array, realizaremos los
        // cambios entre posiciones del array un número de veces igual a la longitud
        // del array al cuadrado, así aseguramos un mínimo de aletoreidad.
        int count = array.length * array.length;

        for (int i = 0; i < count; i++) {
            int index0 = random.nextInt(array.length);
            int index1 = random.nextInt(array.length);
            T piece = array[index0];
            array[index0] = array[index1];
            array[index1] = piece;
        }
    }

    /**
     * Método que concatena los dos arrays que se le pasan por parámetro, si
     * alguno de ellos es nullo, se pasa una copia del no nulo o un array vacío
     * si ambos son nulos.
     *
     * @param <T> La clase de los objetos que hay en el array
     * @param array1 Primer array a concatenar
     * @param array2 Segundo array a concatenar
     * @return Array de objetos de la clase T con los elementos del array1 y el
     * array2
     */
    static public <T> T[] cat(T[] array1, T[] array2) {
        // Si cualquiera de los arrays es nulo o está vacío, devolveremos una
        // copia del otro array
        if (array1 == null || array1.length == 0) {
            return (T[]) Arrays.copyOf(array2, array2 == null ? 0 : array2.length);
        }
        if (array2 == null || array2.length == 0) {
            return (T[]) Arrays.copyOf(array1, array1.length);
        }

        // Si los arrays no son nulos, creamos un nuevo array con una dimensión 
        // igual a la suma de las dos longitudes y concatenamos ambos arrays en 
        // uno sólo que será el que devolvamos.
        T[] array;
        array = (T[]) Array.newInstance(array1[0].getClass(), array1.length + array2.length);
        int index = 0;

        for (T element : array1) {
            array[index++] = element;
        }

        for (T element : array2) {
            array[index++] = element;
        }

        return array;
    }
}
