public class Pedidos{
    private int id;
    private int cliente_id;
    private int producto_id;
    private int cantidad;
    private int fecha;

    

    public String toString(){
        return (id+","+cliente_id+","+producto_id+","+cantidad+","+fecha);
    }
}