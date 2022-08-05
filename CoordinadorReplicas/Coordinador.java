import java.net.Socket;
import java.util.Map;

import Tarro;


public class Coordinador {
	private Log log;
	private Map<Socket, Participante> connections;
	
	volatile int voteCounter;
	
	public Coordinador(Log log, Map<Socket, Tarro> connections){
		this.log = log;
		this.connections = connections;
	}
	public void startCommit(){
		voteCounter = 0;
		log.write(Log.START_2PC);
		Mensaje voteRequestMessage = new Mensaje("VOTE_REQUEST");	
		try {
			Thread.sleep(Main.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(Participante p: connections.values()){
			p.enviarmensaje(voteRequestMessage, new ResponseEvent() { 
				@Override
				public void notificar(Mensaje mensaje) { 
					if(mensaje.type.equals("VOTE_COMMIT")){
						Commit();
					}else{
						Abort();
					}
				}
			}, new ResponseEvent() {
				
				@Override
				public void notificar(Mensaje mensaje) { //timeout
					participantAbort();
				}
			});
		}
		
	}
	
	private synchronized void Commit(){
		voteCounter++;
		String latestLog = log.latest();
		if(voteCounter == connections.size() && !latestLog.equals(Log.GLOBAL_ABORT)){
			log.write(Log.GLOBAL_COMMIT);
			Mensaje globalCommitMessage = new Mensaje("GLOBAL_COMMIT"); 
			for(Tarro pp: connections.values()){
				pp.enviarmensaje(globalCommitMessage);
			}
		}
		
	}
	private synchronized void Abort(){
		log.write(Log.GLOBAL_ABORT);
		Mensaje globalAbortMessage = new Mensaje("GLOBAL_ABORT"); 
		for(Tarro pp: connections.values()){
			pp.enviarmensaje(globalAbortMessage);
		}
	}
	

}
