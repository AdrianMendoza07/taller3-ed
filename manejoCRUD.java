import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;

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
            System.out.println("Nombre: " + clientes.get(i)[1] + "Email: " + clientes.get(i)[2]);
        }
    }
}