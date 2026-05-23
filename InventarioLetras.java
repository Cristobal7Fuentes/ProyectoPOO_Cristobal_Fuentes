package cl.utalca;

//InventarioLetras mantiene un inventario de las 26 letras del alfabeto en ingles.
//este mismo ignora cualquier caracter no alfabetico y no distingue entre
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
        int offset = (Character.toLowerCase(letra) - 'a' + 3) % 26;
        return (char) (base + offset);
    }

    //descifra un caracter cifrado Cesar con un desplazamiento de 3 (es decir de forma inversa)
    public char desencriptarCesar(char letra){
        if (!Character.isLetter(letra)) return letra;
        char base = Character.isUpperCase(letra) ? 'A' : 'a';
        int offset = (Character.toLowerCase(letra) - 'a' - 3) % 26;
        if (offset < 0 ) offset += 26;
        return (char) (base + offset);
    }

    public String encriptarPalabra(String palabra, int desplazamiento){
        if (palabra == null) return null;              // se cifra una palabra usando el desplazamiento dado,
        StringBuilder sb = new StringBuilder();        // este desplazamiento puede ser distinto de 3
        int d = ((desplazamiento % 26) + 26) % 26;
        for (char c : palabra.toCharArray()) {
            if (!Character.isLetter(c)) {
                sb.append(c);
            }else {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int offset = (Character.toLowerCase(c) - 'a' + d) % 26;
                char out  = (char) (base + offset);
                sb.append(out);
            }
        }
        return sb.toString();
    }
    //descifra la palabra con el desplazamiento
    public String desencriptarPalabra(String palabra, int desplazamiento){
        return encriptarPalabra(palabra, -desplazamiento);
    }

    // se devuelve el recuento de la letra sin distinguir mayusculas o minusculas,
    // de no ser una letra alfabetica arroja IlegalArgumentException
    public int get(char letra){
        if (!Character.isLetter(letra)) throw new IllegalArgumentException("no es un letra: " + letra);
        char lower = Character.toLowerCase(letra);
        if (lower < 'a' || lower > 'z') throw new IllegalArgumentException("letra fuera de a-z: " + letra);
        return counts[lower - 'a'];
    }
    //esto fija el recuento de la letra, el cual el valor debe ser >= 0
     public void set(char letra, int valor){
        if(!Character.isLetter(letra)) throw new IllegalArgumentException("no es un letra: " + letra);
        if (valor < 0) throw new IllegalArgumentException("valor negativo: " + valor);
        char lower = Character.toLowerCase(letra);
        if (lower < 'a' || lower > 'z') throw new IllegalArgumentException("letra fuera de a-z: " + letra);
        int idx = lower - 'a';
        int prev = counts[idx];
        counts[idx] = valor;
        totalCount += (valor - prev);
        if (prev == 0 && valor > 0) nonZeroCount++;
        if (prev > 0 && valor == 0) nonZeroCount--;
     }

     public int size() {
         return totalCount;  // devuelve la suma de todos los recuentos
     }
     //indica si el inventario esta vacio todos los recuentos son 0
     public boolean isEmpty() {
        return nonZeroCount == 0;
     }

     // organiza la representacion con letras minusculas y en orden alfabetico
     @Override
     public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < counts[i]; k++) {
                sb.append((char) ('a' + i));
            }
        }
        sb.append(']');
        return sb.toString();
     }
     public InventarioLetras add(InventarioLetras otro){        //aca se suman dos inventarios y se devuelve uno nuevo,
        InventarioLetras res = new InventarioLetras("");   // no se modifican los originales
         for (int i = 0; i < 26; i++) {
            res.counts[i] = this.counts[i] + otro.counts[i];
            if (res.counts[i] > 0) res.nonZeroCount++;
            res.totalCount += res.counts[i];
        }
        return res;
     }
     // se multiplican todos los recuentos por n, cuando cumple (n >= 0)
     public InventarioLetras amplifies(int n) {
         if (n < 0) throw new IllegalArgumentException("n debe ser >= 0");
         InventarioLetras res = new InventarioLetras("");
         for (int i = 0; i < 26; i++) {
             res.counts[i] = this.counts[i] * n;
             if (res.counts[i] > 0) res.nonZeroCount++;
             res.totalCount += res.counts[i];
         }
         return res;
     }
     // al finalizar resta otro inventario y si algun resultado es negativo retorna null
     public InventarioLetras subtract(InventarioLetras otro){
        InventarioLetras res = new InventarioLetras("");
        for (int i = 0; i < 26; i++) {
            int val = this.counts[i] - otro.counts[i];
            if (val < 0) return null;
            res.counts[i] = val;
            if (val > 0 ) res.nonZeroCount++;
            res.totalCount += val;
        }
        return res;
     }
}