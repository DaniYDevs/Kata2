import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProcesadorDataset {
    public static void main(String[] args) {
        List<Registro> registros = new ArrayList<>();

        cargarDataset(registros, "src/datos.csv");

        HashMap<Integer,Integer> histogramaEdades = new HashMap<>();
        for(Registro registro: registros){
            int edad = registro.getEdad();
            histogramaEdades.put(edad, histogramaEdades.getOrDefault(edad, 0) +1);
        }

        for(Integer edad : histogramaEdades.keySet()) {
            System.out.println("Edad: " + edad + ", Frecuencia: " + histogramaEdades.get(edad));
        }
    }

    private static void cargarDataset(List<Registro> registros, String s) {
        try (BufferedReader br = new BufferedReader(new FileReader(s))){
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] valores = line.split(";");
                registros.add(new Registro(valores[0], Integer.parseInt(valores[1])));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
