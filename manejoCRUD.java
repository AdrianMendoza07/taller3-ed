import java.io.*;
import java.util.ArrayList;

class manejoCRUD {
    public static void main(String[] args) {

    }

    public static void ordenarproducto() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("productos.csv"));
        ArrayList<String[]> productos = new ArrayList<>();
        String encabezado = br.readLine();
        String linea;
        while ((linea = br.readLine()) != null) {
            if (!linea.trim().isEmpty()) {
                productos.add(linea.split(","));
            }
        }
        br.close();
        for (int i = 0; i < productos.size() - 1; i++) {
            for (int j = 0; j < productos.size() - i - 1; j++) {

                double precio1 = Double.parseDouble(productos.get(j)[3]);
                double precio2 = Double.parseDouble(productos.get(j + 1)[3]);

                if (precio1 > precio2) {
                    String[] temp = productos.get(j);
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("productos.csv", false));
        bw.write(encabezado);
        bw.newLine();
        for (String[] p : productos) {
            bw.write(String.join(",", p));
            bw.newLine();
        }
        bw.close();

        System.out.println("Archivo productos.csv actualizado correctamente.");
    }

}
