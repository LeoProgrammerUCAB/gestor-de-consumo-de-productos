import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.gestor.de.cosumo.services.RemoteTarro;

public class Tarro implements RemoteTarro {

    public Tarro() throws Exception {
    }

    public synchronized String consultarProductos() throws Exception {
        return this.leerTransacciones().toJSONString();
    }

    public synchronized void agregarProducto(char tipo, int cantidad) throws Exception, RemoteException {

        String key = "producto" + tipo; // las posibles clves en el JSON son productoA o productoB
        Map<String, Integer> productos = this.leerRepositorio();
        if (productos.containsKey(key)) {
            productos.put(key, productos.get(key) + cantidad);
            this.guardarRepositorio(productos);
            this.guardarTransaccion(tipo, cantidad, "Productor");
        } else {
            throw new Exception("Producto " + tipo + " no existe");
        }
    }

    public void consumirProducto(char tipo, int cantidad) throws Exception {
        Map<String, Integer> productos = this.leerRepositorio();

        String key = "producto" + tipo;

        if (productos.containsKey(key)) {
            productos.put(key, productos.get(key) - cantidad);
            this.guardarRepositorio(productos);
            this.guardarTransaccion(tipo, cantidad, "consumidor");
        } else {
            throw new Exception("Producto " + tipo + " no existe");
        }
    }

    private Map<String, Integer> leerRepositorio() throws Exception {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./repositorio.json"));

            JSONObject jsonObject = (JSONObject) obj;

            Map<String, Integer> productos = new HashMap<String, Integer>();

            for (Object key : jsonObject.keySet()) {
                String producto = (String) key;
                int cantidad = ((Long) jsonObject.get(producto)).intValue();
                productos.put(producto, cantidad);
            }

            return productos;

        } catch (FileNotFoundException e) {
            throw new Exception("No se encontro el archivo");
        } catch (IOException e) {
            throw new Exception("Error de lectura");
        } catch (ParseException e) {
            throw new Exception("Error de parseo");
        }
    }

    private JSONArray leerTransacciones() throws Exception {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("./transacciones.json"));
            return (JSONArray) obj;
        } catch (FileNotFoundException e) {
            throw new Exception("No se encontro el archivo");
        } catch (IOException e) {
            throw new Exception("Error de lectura");
        } catch (ParseException e) {
            throw new Exception("Error de parseo");
        }
    }

    private void guardarRepositorio(Map<String, Integer> productos) throws Exception {
        JSONObject jsonObject = new JSONObject();
        for (String producto : productos.keySet()) {
            jsonObject.put(producto, productos.get(producto));
        }
        try {
            FileWriter file = new FileWriter("./repositorio.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new Exception("Error de escritura");
        }
    }

    private void guardarTransaccion(char tipo, int cantidad, String actor) throws Exception {
        JSONArray transacciones = this.leerTransacciones();

        JSONObject nuevaTransaccion = new JSONObject();
        nuevaTransaccion.put("cantidad", cantidad);
        nuevaTransaccion.put("tipo_producto", String.valueOf(tipo));
        nuevaTransaccion.put("actor", actor);
        nuevaTransaccion.put("fecha", new Date().toString());
        nuevaTransaccion.put("inventario_resultante", this.leerRepositorio());

        transacciones.add(nuevaTransaccion);

        try {
            FileWriter file = new FileWriter("./transacciones.json");
            file.write(transacciones.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
