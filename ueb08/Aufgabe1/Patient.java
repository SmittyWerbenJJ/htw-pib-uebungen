/**
 * 
 * Patient Class. Holds Patient Identification Information
 * 
 * @version 02.01.2022
 * @author Smitty
 *
 */
public class Patient {
	private int ID;
	private String name;
	private String surname;

	public Patient(int ID, String name, String surname) {
		this.setID(ID);
		this.setName(name);
		this.setSurname(surname);
	}

	public String toString() {
		String ausgabe = String.format("%d\t%s, %s", this.getID(), this.getName(), this.getSurname());
		return ausgabe;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
