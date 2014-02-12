import javax.persistence.Entity;

import org.sahin.model.BaseEntity;

@Entity
public class Student4 extends BaseEntity {
	String name, emailAdress, phoneNumber;
	String university, department;
	String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String toString() {

		String str = "";
		str = str + "\t Ad: " + name;
		str = str + "\t Mail: " + emailAdress;
		str = str + "\t Telefon: " + phoneNumber;
		str = str + "\t Universite: " + university;
		str = str + "\t Bolum: " + department;
		str = str + "\t Not: " + note;
		return str;
	}

}
