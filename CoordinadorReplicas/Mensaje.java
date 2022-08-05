import java.io.Serializable;

public class Mensaje implements Serializable{
	public String type;
	
	public Mensaje(String type){
		this.type = type;
	}
	
	public boolean equals(Object other){
		if(other instanceof Mensaje){
			return (((Mensaje) other).type.equals(type));
		}else{
			return false;
		}
		
	}
}