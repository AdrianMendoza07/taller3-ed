public class Producto {

    private int id;
    private String nombre;
    private String categoria;
    private int cantidad;
    private int precio; 

    public Producto(int id, String nombre, String categoria, int cantidad, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Override
    public String toString (){
        return(id +","+nombre+","+categoria+","+cantidad+","+precio);
    }

}