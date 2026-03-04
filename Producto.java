public class Producto {

    private int id;
    private String nombre;
    private String categoria;
    private int cantidad;
    private int precio;

    

    @Override
    public String toString (){
        return(id +","+nombre+","+categoria+","+cantidad+","+precio);
    }

}