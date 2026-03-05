
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("3. Calcular total ventas.");
        System.out.print("Ingrese que opcion desea realizar: ");
        int opcion = sc.nextInt();

        while (opcion != 5) {
            switch (opcion) {
                case 3:
                    try {
                        manejoCRUD.totalVentas();
                        System.out.println("Archivo total_ventas.csv creado con exito");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del menu...");
                    break;
                default:
                    throw new AssertionError();
            }
            if (opcion != 5) {
                System.out.print("Ingrese que opcion desea realizar: ");
                opcion = sc.nextInt();
            }
        }

    }
}
