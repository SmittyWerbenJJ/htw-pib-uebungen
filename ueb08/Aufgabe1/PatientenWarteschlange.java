/**
 * ueb08 - PatientenWarteschlange
 * 
 * @author Smitty
 *
 */
public class PatientenWarteschlange {
	private Patient[] warteSchlange;
	private int front;
	private int rear;
	private int capacity;

	/**
	 * 
	 * Creating a Queue for Patients
	 * 
	 * @param warteSchlangenGroesse The Size of the Queue
	 * @throws IllegalArgumentException when QueueSize not a positive integer
	 */
	PatientenWarteschlange(int warteSchlangenGroesse) {
		if (warteSchlangenGroesse < 0) {
			throw new IllegalArgumentException("Queue Size has to be greater 0");
		}
		this.warteSchlange = new Patient[warteSchlangenGroesse];
		front = 0;
		rear = 0;
		capacity = warteSchlangenGroesse;
	}

	public String toString() {
		StringBuffer ausgabe = new StringBuffer("\nWarteListe:\n");
		for (Patient patient : warteSchlange) {
			if (patient == null) {
				continue;
			}
			ausgabe.append(patient.toString()+"\n");
			
		}
		return ausgabe.toString();
	}

	/**
	 * Adding A new Patient to the Q
	 * 
	 * @param i       Patient ID
	 * @param string  Patient's Name
	 * @param string2 Patient's Surname
	 */
	public void neuerPatient(int i, String name, String surname) {

		// check Q size
		if (capacity == rear) {
			throw new IllegalArgumentException("Queue Is Full");
		}

		// check if The patient ID is already in the Q
		for (int x = 0; x < warteSchlange.length; x++) {
			if (warteSchlange[x] == null) {
				continue;
			} else if (warteSchlange[x].getID() == i) {
				throw new IllegalArgumentException("Patient Already in Q");
			}
		}

		// check for valid Patient Name
		name = name.trim();
		surname = surname.trim();
		if (name.isEmpty() || surname.isEmpty()) {
			throw new IllegalArgumentException("Invalid Name. No Empty Names allowed!");
		}

		// create New Patient
		Patient newPatient = new Patient(i, name, surname);

		// add new Patient to Q
		warteSchlange[rear] = newPatient;
		rear++;
	}

	/**
	 * Removing a Patient of the Q
	 * 
	 * @param i The Patients Position in the Q
	 * @return
	 */
	public Patient entfernePatient(int i) {
		int QPosition = -1;

		if (front == rear) {
			// Q is Empty
			return null;
		}

		// check if patient ID is in q
		for (int j = 0; j < warteSchlange.length; j++) {
			if (warteSchlange[j] == null) {
				continue;
			}
			if (warteSchlange[j].getID() == i) {
				QPosition = j;
				break;
			}
		}

		if (QPosition == -1) {
			throw new IllegalArgumentException("Patient is not in Queue!");
		}

		// remove patient from q
		Patient targetPatient = warteSchlange[QPosition];
		warteSchlange[QPosition] = null;

		// Last Patient
		if (QPosition == rear) {
			rear--;
		}else {
			updateQueue();			
		}
		return targetPatient;
	}

	/**
	 * Gets The Next Patient from The Queue
	 * 
	 * @return the Patient or nullObject
	 */
	public Patient derNaechsteBitte() {
		Patient naechsterPatient;
		if (front == rear) {
			naechsterPatient = null;
		} else {
			naechsterPatient = warteSchlange[front];
			warteSchlange[front]=null;
			updateQueue();
		}
		return naechsterPatient;
	}

	/**
	 * Updates The Queue. Removes gaps
	 */
	private void updateQueue() {
		if (front == rear) {
			return;
		}
		for (int i = 0; i < rear - 1; i++) {
			if (warteSchlange[i] == null) {
				for (int j = i; j < rear - 1; j++) {
					warteSchlange[j] = warteSchlange[j + 1];
				}
				warteSchlange[rear - 1] = null;
				rear--;
			}
		}
	}

}
