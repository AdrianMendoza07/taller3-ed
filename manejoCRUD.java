import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;
import java.util.ArrayList;

class manejoCRUD {
    public static void main(String[] args) {

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
