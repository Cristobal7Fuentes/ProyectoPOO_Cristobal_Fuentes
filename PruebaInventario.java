package cl.utalca;

public class PruebaInventario {
    public static void main(String[] args) {
        //ejemplo de uso
        InventarioLetras inv1 = new InventarioLetras("Alan Turing");
        InventarioLetras inv2 = new InventarioLetras("Ada Lovelace");
        InventarioLetras suma = inv1.add(inv2);
        System.out.print("ejemplo de uso: " + (suma));
        System.out.print("\n");

        // prueba sugerida
        System.out.print("-----prueba sugerida-----");
        System.out.print("\n");
        InventarioLetras inv = new InventarioLetras("Hola Mundo");
        System.out.println("size: " + inv.size());
        System.out.println("isEmpty: " + inv.isEmpty());
        System.out.println("get('o'): " + inv.get('o'));
        System.out.println(inv);
        System.out.println(inv.encriptarCesar( 'a'));
        System.out.println(inv.encriptarPalabra("play", 3));
    }
}
