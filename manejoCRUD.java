import java.util.*;
import java.io.*;

class manejoCRUD {
    public static void main(String[] args) {
        
    }

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
    public static void agregarCliente() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("clientes.csv"));
        String linea;
        int ultimoId = 0;
        br.readLine();
        while ((linea = br.readLine()) != null){
            String[] datos = linea.split(",");
            ultimoId = Integer.parseInt(datos[0]);
        }
        br.close();

        int nuevoId = ultimoId + 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el email: ");
        String email = sc.nextLine();
        Clientes nuevo = new Clientes(nuevoId, nombre, email);

        BufferedWriter bw = new BufferedWriter(new FileWriter("clientes.csv", true));
        bw.newLine();
        bw.write(nuevo.toString());
        bw.close();
    }
    
    public static void verClientesConCompras() throws IOException {
        ArrayList<Integer> clientesConPedidos = new ArrayList<>();
        BufferedReader brPedidos = new BufferedReader(new FileReader("pedidos.csv"));
        String linea = brPedidos.readLine();
        while ((linea = brPedidos.readLine()) != null){
            String[] datos = linea.split(",");
            int clienteId = Integer.parseInt(datos[1]);
            
            boolean existe = false;
            for (int i = 0; i < clientesConPedidos.size(); i++){
                if (clientesConPedidos.get(i) == clienteId){
                    existe = true;
                    break;
                }
            }
            if (existe == false){
                clientesConPedidos.add(clienteId);
            }
        }
        brPedidos.close();
        ArrayList<String[]> clientes = new ArrayList<>();
        BufferedReader brClientes = new BufferedReader(new FileReader("clientes.csv"));
        brClientes.readLine();
        while ((linea = brClientes.readLine()) != null){
            String[] datos = linea.split(",");
            int id = Integer.parseInt(datos[0]);

            for (int i = 0; i < clientesConPedidos.size(); i++){
                if (clientesConPedidos.get(i) == id){
                    clientes.add(datos);
                    break;
                }
            }   
        }
        brClientes.close();
        for (int i = 0; i < clientes.size() - 1; i++){
            for (int j = 0; j < clientes.size() - i - 1; j++){
                String n1 = clientes.get(j)[1];
                String n2 = clientes.get(j+1)[1];
                if (n1.compareTo(n2)>0){
                    String[] n = clientes.get(j);
                    clientes.set(j, clientes.get(j+1));
                    clientes.set(j+1, n);
                }
            }
        }
        System.out.println("Clientes que han realizado compras: ");
        for (int i = 0; i < clientes.size(); i++){
            System.out.println("Nombre: " + clientes.get(i)[1] + "   Email: " + clientes.get(i)[2]);
        }
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
