import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Tarro implements RemoteTarro{

    public Tarro() throws Exception {
    }

    public synchronized String consultarProductos() throws Exception {
        return this.leerRepositorio().toString();
    }

    public synchronized void agregarProducto(String producto, int cantidad) throws Exception, RemoteException {

        Thread.sleep(15000);
        Map<String, Integer> productos = this.leerRepositorio();
        if (productos.containsKey("producto"+producto)) {
            productos.put("producto"+producto, productos.get("producto"+producto) + cantidad);
            this.guardarRepositorio(productos);
        } else {
            throw new Exception("Producto " + producto + " no existe");
        }
    }
   
    private Map<String, Integer> leerRepositorio() throws Exception{
        JSONParser parser = new JSONParser();
        try {     
            Object obj = parser.parse(new FileReader("./repositorio.json"));

            JSONObject jsonObject =  (JSONObject) obj;
            
            Map<String, Integer> productos = new HashMap<String, Integer>();

            for (Object key : jsonObject.keySet()) {
                String producto = (String) key;
                System.out.println(producto);
                System.out.println(jsonObject.get(producto));
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

    private void guardarRepositorio(Map<String, Integer> productos) throws Exception{
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

}
