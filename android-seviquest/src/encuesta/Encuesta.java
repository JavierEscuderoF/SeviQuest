package encuesta;

public class Encuesta {
	public String titulo;
	public String cuerpo;
	public String fecha;
	public String imagen;
	public String opcionA;
	public String opcionB;
	
	public Encuesta () {
		titulo = new String();
		cuerpo = new String();
		fecha = new String();
		imagen = new String();
		opcionA = new String();
		opcionB = new String();
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getCuerpo() {
		return cuerpo;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public String getOpcionA() {
		return opcionA;
	}
	
	public String getOpcionB() {
		return opcionB;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public void setOpcionA(String opcionA) {
		this.opcionA = opcionA;
	}
	
	public void setOpcionB(String opcionB) {
		this.opcionB = opcionB;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
