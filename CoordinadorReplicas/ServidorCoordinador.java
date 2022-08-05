import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCoordinador {

    private static Coordinador coordinador;

    public static int LISTEN_PORT = 5000;
	
	public static void main(String[] args) throws IOException {
        coordinador = new Coordinador(new Log());
		ServerSocket ss = new ServerSocket(LISTEN_PORT);

        String str = "";

        while (!str.equals("stop")) {
            Socket s = ss.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());

            String respuesta = din.readUTF();
            String[] informacion = respuesta.split(";");
            String transacciones = informacion[1];
            String comando = informacion[0];

            if (comando.equals("START_2PC")){
                coordinador.startCommit(transacciones);
            }
        
            din.close();
            s.close();
        }
        ss.close();
	}
}
