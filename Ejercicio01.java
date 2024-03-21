import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> palabrasReservadas = new ArrayList<>();
        palabrasReservadas.add("abstract");
        palabrasReservadas.add("assert");

        ArrayList<IdyLista> palabrasNoReservadas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoPath))) {
            String linea;
            int numeroLinea = 1;

            while ((linea = br.readLine()) != null) {
               
                String[] palabras = linea.split("\\s+"); // Dividir la línea en palabras

                for (String palabra : palabras) {
                    if (!palabrasReservadas.contains(palabra)) {
                       
                        boolean encontrada = false;
                        for (IdyLista idyLista : palabrasNoReservadas) {
                            if (idyLista.getPalabra().equals(palabra)) {
                                idyLista.agregarLinea(numeroLinea);
                                encontrada = true;
                                break;
                            }
                        }
                       
                        if (!encontrada) {
                            IdyLista nuevaPalabra = new IdyLista(palabra);
                            nuevaPalabra.agregarLinea(numeroLinea);
                            palabrasNoReservadas.add(nuevaPalabra);
                        }
                    }
                }
                numeroLinea++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

       
        for (IdyLista palabra : palabrasNoReservadas) {
            System.out.println(palabra.getPalabra() + ": " + palabra.getNumerosLinea());
        }

        System.out.println("Cantidad de palabras no reservadas: " + palabrasNoReservadas.size());
    }
}

class IdyLista {
    private String palabra;
    private ArrayList<Integer> numerosLinea;

    public IdyLista(String palabra) {
        this.palabra = palabra;
        this.numerosLinea = new ArrayList<>();
    }

    public String getPalabra() {
        return palabra;
    }

    public ArrayList<Integer> getNumerosLinea() {
        return numerosLinea;
    }

    public void agregarLinea(int numeroLinea) {
        numerosLinea.add(numeroLinea);
    }
}
