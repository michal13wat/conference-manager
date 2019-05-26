package my.vaadin.cm;

import java.io.Serializable;

/**
 * A entity object, like in any other Java application. In a typical real world
 * application this could for example be a JPA entity.
 */
@SuppressWarnings("serial")
public class LectureRow implements Serializable, Cloneable {

	private Long id;

	private String subjectA = "";

	private String subjectB = "";
	
	private String subjectC = "";

	private String dateTime;

	private SubjectEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the value of subjectC
	 *
	 * @return the value of subjectC
	 */
	public String getSubjectC() {
		return subjectC;
	}

	/**
	 * Set the value of subjectC
	 *
	 * @param subjectC
	 *            new value of subjectC
	 */
	public void setSubjectC(String subjectC) {
		this.subjectC = subjectC;
	}

	/**
	 * Get the value of status
	 *
	 * @return the value of status
	 */
	public SubjectEnum getStatus() {
		return status;
	}

	/**
	 * Set the value of status
	 *
	 * @param status
	 *            new value of status
	 */
	public void setStatus(SubjectEnum status) {
		this.status = status;
	}

	/**
	 * Get the value of dateTime
	 *
	 * @return the value of dateTime
	 */
	public String getDateTime() {
		return dateTime;
	}

	/**
	 * Set the value of dateTime
	 *
	 * @param dateTime
	 *            new value of dateTime
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Get the value of lastName
	 *
	 * @return the value of lastName
	 */
	public String getSubjectB() {
		return subjectB;
	}

	/**
	 * Set the value of subjectB
	 *
	 * @param subjectB
	 *            new value of subjectB
	 */
	public void setSubjectB(String subjectB) {
		this.subjectB = subjectB;
	}

	/**
	 * Get the value of subjectA
	 *
	 * @return the value of subjectA
	 */
	public String getSubjectA() {
		return subjectA;
	}

	/**
	 * Set the value of subjectA
	 *
	 * @param subjectA
	 *            new value of subjectA
	 */
	public void setSubjectA(String subjectA) {
		this.subjectA = subjectA;
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

		if (obj instanceof LectureRow && obj.getClass().equals(getClass())) {
			return this.id.equals(((LectureRow) obj).id);
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
	public LectureRow clone() throws CloneNotSupportedException {
		return (LectureRow) super.clone();
	}

	@Override
	public String toString() {
		return subjectA + " " + subjectB;
	}
}