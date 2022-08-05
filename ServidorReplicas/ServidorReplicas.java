import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorReplicas {

    public static void main(String[] args) throws IOException {

        //Request port from user input
        System.out.println("Ingrese el puerto de escucha (5001, 5002): ");
        int listenPort = Integer.parseInt(System.console().readLine());


        ServerSocket ss = new ServerSocket(listenPort);

        String str = "";

        while (!str.equals("stop")) {
            Socket s = ss.accept();
            System.out.println("Conexion establecida");
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            String respuesta = din.readUTF();
            System.out.println("Mensaje recibido: " + respuesta);
            String[] informacion = respuesta.split(";");
            String transacciones = "";
            if (informacion.length > 1){
                transacciones = informacion[1];
                System.out.println("Transacciones: " + transacciones);
            } else{
                System.out.println("No hay transacciones");

            }
            String comando = informacion[0];
            System.out.println("Comando: " + comando);

            switch(comando){
                case "VOTE_REQUEST":
                    dout.writeUTF(responderAleatoriamente());
                    break;
                case "GLOBAL_COMMIT":
                    guardarTransacciones(transacciones);
                    break;
                case "GLOBAL_ABORT":
                    System.out.println("Replica abortada");
                    break;
                }

            //s.close();
        }

            ss.close();
    }


    private static String responderAleatoriamente() {   
        if (Math.random() < 0.75) {
            return "VOTE_COMMIT";
        } else {
            return "VOTE_ABORT";
        }
    }

    private static void guardarTransacciones(String transacciones) {
        System.out.println("Transacciones: " + transacciones); 
    }
}
