import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Coordinador {
	private Log log;
	//private static int se;
	private static String transacciones = null;
	private static int voteCounter;
	
	public Coordinador(Log log) throws UnknownHostException, IOException{
		this.log = log;
	}
	public void startCommit(String transaccionesJSON) throws IOException{
		transacciones = transaccionesJSON;
		voteCounter = 0;
		List<Socket> servidores = new ArrayList<Socket>();
		Socket Socket1 = new Socket("localhost", 5001);
		Socket Socket2 = new Socket("localhost", 5002);
		servidores.add(Socket1);
		servidores.add(Socket2);
		log.write("VOTE_REQUEST");
		for(Socket s : servidores){
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			DataInputStream din = new DataInputStream(s.getInputStream());
			dout.writeUTF("VOTE_REQUEST");
			String respuesta = din.readUTF();

			switch(respuesta){
				case "VOTE_COMMIT":
					voteCounter++;
					break;
				case "VOTE_ABORT":
					break;
			}
		}

		//Close all servidores
		for(Socket s : servidores){
			s.close();
		}

		String latestLog = log.latest();
		if(voteCounter == 2 && !latestLog.equals(Log.GLOBAL_ABORT)){
			commit(voteCounter);
		}else{
			abort();
		}
			
	}
	
	private synchronized void commit(int voteCounter) throws IOException{
		
			log.write(Log.GLOBAL_COMMIT);
			List<Socket> servidores = new ArrayList<Socket>();
			Socket Socket1 = new Socket("localhost", 5001);
			Socket Socket2 = new Socket("localhost", 5002);
			servidores.add(Socket1);
			servidores.add(Socket2);

			for(Socket s : servidores){
				System.out.println("VOTE_COMMIT recibido de " + s.getInetAddress().getHostAddress());
				DataOutputStream doutCommit = new DataOutputStream(s.getOutputStream());
				doutCommit.writeUTF("GLOBAL_COMMIT;" + transacciones);
				doutCommit.close();
			}
			for(Socket s : servidores){
				s.close();
			}
	
		
	}

	private synchronized void abort() throws IOException{
		log.write(Log.GLOBAL_ABORT);

		log.write(Log.GLOBAL_COMMIT);
		List<Socket> servidores = new ArrayList<Socket>();
		Socket Socket1 = new Socket("localhost", 5001);
		Socket Socket2 = new Socket("localhost", 5002);
		servidores.add(Socket1);
		servidores.add(Socket2);
		for(Socket s : servidores){
			System.out.println("VOTE_ABORT de " + s.getInetAddress().getHostAddress());
			DataOutputStream doutAbort = new DataOutputStream(s.getOutputStream());
			doutAbort.writeUTF("GLOBAL_ABORT");
			doutAbort.close();
		}
		for(Socket s : servidores){
			s.close();
		}
	}

}
