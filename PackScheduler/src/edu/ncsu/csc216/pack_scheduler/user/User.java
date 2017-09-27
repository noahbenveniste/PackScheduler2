package edu.ncsu.csc216.pack_scheduler.user;

public abstract class User {

	/** The user's first name */
	private String firstName;
	/** The student's last name */
	private String lastName;
	/** The student's id */
	private String id;
	/** The student's email */
	private String email;
	/** The student's password */
	private String password;

	/**
	 * Sets the fields for the User superclass
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param email
	 * @param hashPW
	 */
	public User(String firstName, String lastName, String id, String email, String hashPW) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setId(id);
		this.setEmail(email);
		this.setPassword(hashPW);
	}
		

	/**
	 * Returns the the student's email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the student's email
	 * @param email the email to set
	 * @throws IllegalArgumentException for null or empty string input, if the email does not contain
	 * the "@" or "." characters, or if the final instance of the "." character comes before the "@" 
	 * character
	 */
	public void setEmail(String email) {
		//First check that input string isn't null
		if (email == null) {
			throw new IllegalArgumentException("Invalid email");
		}
		//Next check that input isn't an empty string
		if (email.equals("")) {
			throw new IllegalArgumentException("Invalid email");
		}
		//Check that the input contains an @ character
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		//Check that the input contains a . character
		if (!email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		//Check that the @ comes before the last . in the string
		if (email.indexOf("@") > email.lastIndexOf(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		//If all conditions are met, set the field
		this.email = email;
	}

	/**
	 * Gets the student's password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the student's password
	 * @param password the password to set
	 * @throws IllegalArgumentException for null or empty string input
	 */
	public void setPassword(String password) {
		//Check that the input isn't null
		if (password == null) {
			throw new IllegalArgumentException("Invalid password");
		}
		//Check that the input is an empty string
		if (password.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		//Set the field if all conditions are met
		this.password = password;
	}

	/**
	 * Gets the student's first name
	 * @return the student's first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the student's first name
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException for null or empty string input
	 */
	public void setFirstName(String firstName) {
		//Check that the input isn't null
		if (firstName == null) {
			throw new IllegalArgumentException("Invalid first name");
		}
		//Check that the input string isn't empty
		if (firstName.equals("")) {
			throw new IllegalArgumentException("Invalid first name");
		}
		//Set field if conditions are met
		this.firstName = firstName;
	}

	/**
	 * Gets the student's last name
	 * @return the student's last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the student's last name
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException for null or empty string input
	 */
	public void setLastName(String lastName) {
		//Check that the input isn't null
		if (lastName == null) {
			throw new IllegalArgumentException("Invalid last name");
		}
		//Check that the input isn't an empty string
		if (lastName.equals("")) {
			throw new IllegalArgumentException("Invalid last name");
		}
		//Set field if all conditions are met
		this.lastName = lastName;
	}

	/**
	 * Gets the student's id
	 * @return the student's id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the student's id
	 * @param id the id to set
	 * @throws IllegalArgumentException for null or empty string input
	 */
	protected void setId(String id) {
		//Check that input isn't null
		if (id == null) {
			throw new IllegalArgumentException("Invalid id");
		}
		//Check that input isn't an empty string
		if (id.equals("")) {
			throw new IllegalArgumentException("Invalid id");
		}
		//Set the field if all conditions are met
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}