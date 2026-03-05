
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class manejoCRUD {

    public static ArrayList<Float> extraerDato(String fileName, int indx) throws FileNotFoundException {

        ArrayList<Float> datos = new ArrayList<>();

        File archivo = new File(fileName);
        Scanner pSc = new Scanner(archivo);
        pSc.nextLine();
        while (pSc.hasNextLine()) {
            String line = pSc.nextLine();
            String[] valor = line.split(",");

            float dato = Float.parseFloat(valor[indx].trim());

            datos.add(dato);
        }

        pSc.close();

        return datos;

    }

    public static ArrayList<String> extraerString(String fileName, int indx) throws FileNotFoundException {

        ArrayList<String> strings = new ArrayList<>();

        File archivo = new File(fileName);
        Scanner pSc = new Scanner(archivo);
        pSc.nextLine();
        while (pSc.hasNextLine()) {
            String line = pSc.nextLine();
            String[] valor = line.split(",");

            String string = valor[indx].trim();

            strings.add(string);
        }

        pSc.close();

        return strings;

    }

    public static HashMap<Integer, Integer> cantidadPorProducto(String fileName) throws FileNotFoundException {

        HashMap<Integer, Integer> cantidades = new HashMap<>();

        File archivo = new File(fileName);
        Scanner sc = new Scanner(archivo);

        sc.nextLine();

        while (sc.hasNextLine()) {

            String line = sc.nextLine();
            String[] values = line.split(",");

            int producto_id = Integer.parseInt(values[2].trim());
            int cantidad = Integer.parseInt(values[3].trim());

            if (cantidades.containsKey(producto_id)) {
                cantidades.put(producto_id, cantidades.get(producto_id) + cantidad);
            } else {
                cantidades.put(producto_id, cantidad);
            }
        }

        sc.close();

        return cantidades;
    }

    public static void ordenarVentas(ArrayList<TotalVenta> ventas) {

        int n = ventas.size();

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (ventas.get(j).total < ventas.get(j + 1).total) {

                    TotalVenta temp = ventas.get(j);
                    ventas.set(j, ventas.get(j + 1));
                    ventas.set(j + 1, temp);
                }
            }
        }
    }

    public static void totalVentas() throws FileNotFoundException, IOException {

        ArrayList<Float> precios = extraerDato("productos.csv", 3);
        ArrayList<String> nombres = extraerString("productos.csv", 1);
        HashMap<Integer, Integer> cantidades = cantidadPorProducto("pedidos.csv");

        FileWriter fw = new FileWriter("total_ventas.csv");
        BufferedWriter bw = new BufferedWriter(fw);

        ArrayList<TotalVenta> ventas = new ArrayList<>();

        for (Integer producto_id : cantidades.keySet()) {

            float precio = precios.get(producto_id - 1);
            int cantidad = cantidades.get(producto_id);
            String nombre = nombres.get(producto_id - 1);

            float total = precio * cantidad;

            ventas.add(new TotalVenta(producto_id, nombre, total));

        }

        ordenarVentas(ventas);

        for (TotalVenta elem : ventas) {
            bw.write(elem.producto_id + "," + elem.nombre_producto + "," + elem.total);
            bw.newLine();
        }

        bw.close();
    }
}
