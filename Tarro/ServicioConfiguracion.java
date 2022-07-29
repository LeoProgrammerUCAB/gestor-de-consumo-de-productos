import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServicioConfiguracion {
    private Map<String, String> config = new HashMap<String, String>();

    public ServicioConfiguracion(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                config.put(parts[0], parts[1]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCantidadInicial(String producto) {
        System.out.println("cantidadInicial" + producto);
        if (config.containsKey("cantidadInicial" + producto)) {
            return Integer.parseInt(config.get("cantidadInicial" + producto));
        } else {
            return 0;
        }
    }

    public int getPuerto() {
        // Verify if puerto exists in config file
        if (config.containsKey("puerto")) {
            return Integer.parseInt(config.get("puerto"));
        } else {
            return 0;
        }
    }

    public int getTiempoDeRespuesta() {
        // Verify if tiempoDeRespuesta exists in config file
        if (config.containsKey("tiempoDeRespuesta")) {
            return Integer.parseInt(config.get("tiempoDeRespuesta"));
        } else {
            return 0;
        }
    }
}
