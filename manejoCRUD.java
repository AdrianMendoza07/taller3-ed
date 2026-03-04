
import java.util.Scanner;


class manejoCRUD {

    public static void main(String[] args) {
        

    @SuppressWarnings("ConvertToTryWithResources")
    public static int leerPrecio()
            throws IOException {
        int precio;
        Scanner sc = new Scanner(new File("productos.csv"));

        while (sc.hasNextLine()) {
            String[] datos = sc.nextLine().split(",");
            lista.add(new Usuario(
                    Integer.parseInt(datos[0]),
                    datos[1],
                    datos[2]));
        }
        sc.close();
        return lista;
    }
}
}
