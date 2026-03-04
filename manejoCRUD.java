import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
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
}