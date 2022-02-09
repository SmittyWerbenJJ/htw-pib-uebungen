import java.io.Serializable;

public class BuchMitAutor implements Serializable {
	private String titel;
	private int seitenanzahl;
	private double preis;
	private transient double nettoPreis;
	private Autor autor;
	
	public BuchMitAutor(String titel, int seitenanzahl, double preis, Autor autor) {
		super();
		this.titel = titel;
		this.seitenanzahl = seitenanzahl;
		this.preis = preis;
		this.nettoPreis = preis + preis * 0.19;
		this.autor = autor;
	}

	public String getTitel() {
		return titel;
	}
	
	public int getSeitenanzahl() {
		return seitenanzahl;
	}
	
	public double getPreis() {
		return preis;
	}
	
	public double getNettoPreis() {
		return nettoPreis;
	}

	@Override
	public String toString() {
		return "BuchMitAutor [titel=" + titel + ", seitenanzahl=" + seitenanzahl + ", preis=" + preis + ", nettoPreis="
				+ nettoPreis + ", autor=" + autor + "]";
	}
}
