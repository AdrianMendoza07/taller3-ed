
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {

            System.out.println("\n=== MENU ===");
            System.out.println("1. Ordenar productos por precio");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opcion: ");

            opcion = sc.nextInt();  // 🔹 AHORA se actualiza cada vez

            switch (opcion) {

                case 1:
                    try {
                        manejoCRUD.ordenarproducto();
                        System.out.println("Archivo creado con éxito");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }

        sc.close();
    }
}