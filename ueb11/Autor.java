import java.io.Serializable;

public class Autor implements Serializable {
	private String vorname;
	private String nachname;

	public Autor(String vorname, String nachname) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}
	
	public String getNachname() {
		return nachname;
	}
}
