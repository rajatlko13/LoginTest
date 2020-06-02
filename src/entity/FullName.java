package entity;

import javax.persistence.Embeddable;

@Embeddable
public class FullName {
	
	String firstname;
	String lastname;
	
	public FullName()
	{
		
	}
	
	public FullName(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	

}
