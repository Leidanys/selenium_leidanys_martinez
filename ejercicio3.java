package clase3;

import java.util.Scanner;

public class ejercicio3 {
    public static void main (String [] args){

        System.out.println("EL DOBLE ES: "+ obtenerDoble(pedirNumero("INGRESE UN NUMERO PARA OBTENER SU DOBLE")));
    }

    //Escribir un método que reciba un número y retorne su doble
    public static int obtenerDoble(int newNumero){
        return 2* newNumero;
    }

    public static int pedirNumero(String mensaje){

        Scanner input= new Scanner(System.in);
        System.out.println(mensaje);
        int numero=0;
        try {
            numero= input.nextInt();
        }catch (Exception error){

            System.out.println("ERROR: No se ha ingresado un numero valido. El numero tomara valor 0. "+error.getMessage());

        }
        return numero;

    }
}
