
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese que opcion desea realizar: ");
        int opcion = sc.nextInt();

        while (opcion != 6) {
            switch (opcion) {
                case 2:
                    manejoCRUD.agregarCliente();
                    break;
                case 6:
                     
                    break;
                default:
                    throw new AssertionError();
            }
        }

    }
}