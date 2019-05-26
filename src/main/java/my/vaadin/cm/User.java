package my.vaadin.cm;

import java.io.Serializable;

/**
 * A entity object, like in any other Java application. In a typical real world
 * application this could for example be a JPA entity.
 */
@SuppressWarnings("serial")
public class User implements Serializable, Cloneable {

	private Long id;

	private String email = "";

	private String login = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the value of login
	 *
	 * @return the value of login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set the value of login
	 *
	 * @param login
	 *            new value of login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Get the value of email
	 *
	 * @return the value of email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the value of email
	 *
	 * @param email
	 *            new value of email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPersisted() {
		return id != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.id == null) {
			return false;
		}

		if (obj instanceof User && obj.getClass().equals(getClass())) {
			return this.id.equals(((User) obj).id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (id == null ? 0 : id.hashCode());
		return hash;
	}

	@Override
	public User clone() throws CloneNotSupportedException {
		return (User) super.clone();
	}

	@Override
	public String toString() {
		return email + " " + login;
	}
}