package cl.utalca;

//InventarioLetras mantiene un inventario de las 26 letras del alfabeto en ingles.
//Este mismo ignora cualquier caracter no alfabetico y no distingue entre
//mayusculas y minusculas, se tratan como si fueran iguales.

public class InventarioLetras {
    private final int[] counts;
    private int totalCount;
    private int nonZeroCount;

    //construye un recuento de las letras alfabeticas ignorando Mayus/minus y caracter no alfabeticos
    public InventarioLetras(String data) {
        counts = new int[26];
        totalCount = 0;
        nonZeroCount = 0;
        if (data == null) return;
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)){
                char lower = Character.toLowerCase(c);
                if (lower >= 'a' && lower <= 'z') {
                    int idx = lower - 'a';
                    if (counts[idx] == 0) nonZeroCount++;
                    counts[idx]++;
                    totalCount++;
                }
            }
        }
    }
    //cifra un caracter utilizando Cesar con desplazamiento fijo de 3 posiciones (solo letras)
    public char encriptarCesar(char letra){
        if (!Character.isLetter(letra)) return letra;
        char base = Character.isUpperCase(letra) ? 'A' : 'a';
        int offset = (Character.toLowerCase(letra) - 'a' - 3) % 26;
        return (char) (base + offset);
    }

    //descifra un caracter cifrado Cesar con un desplazamiento de 3 (es decir de forma inversa)
    public char decriptarCesar(char letra){
        if (!Character.isLetter(letra)) return letra;
        char base = Character.isUpperCase(letra) ? 'A' : 'a';
        int offset = (Character.toLowerCase(letra) - 'a' - 3) % 26;
        if (offset < 0 ) offset += 26;
        return (char) (base + offset);
    }

    public String encriptarPalabra(String palabra, int desplazamiento){
        if (palabra == null) return null;
        StringBuilder sb = new StringBuilder();
        int d = ((desplazamiento % 26) + 26) % 26;
        for (char c : palabra.toCharArray()) {
            if (!Character.isLetter(c)) {
                sb.append(c);
            }else {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int offset = (Character.toLowerCase(c) - 'a' + d) % 26;
                char out = (char) (base + offset);
                sb.append(c);
            }
        }
        return sb.toString();
    }
    //descifra la palabra coon el desplazamiento
    public String desencriptarPalabra(String palabra, int desplazamiento){
        return encriptarPalabra(palabra, -desplazamiento);
    }
    public int get(char letra){
        if(!Character.isLetter(letra) throw new IllegalArgumentException("no es un letra: " + letra);

    }



}
