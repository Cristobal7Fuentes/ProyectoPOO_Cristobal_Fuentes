package cl.utalca;

public class PruebaInventario {
    public static void main(String[] args) {
        InventarioLetras inv = InventarioLetras("Hola Mundo");
        System.out.println("size: " + inv.size());
        System.out.println("isEmpty: " + inv.isEmpty());
        System.out.println("get('o'): " + inv.get('o'));
        System.out.println(inv);
        System.out.println(inv.encriptarCesar('a'));
        System.out.println(inv.encriptarPalabra("play", 3));
    }
}
