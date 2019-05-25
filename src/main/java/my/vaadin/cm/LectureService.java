package my.vaadin.cm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An in memory dummy "database" for the example purposes. In a typical Java app
 * this class would be replaced by e.g. EJB or a Spring based service class.
 * <p>
 * In demos/tutorials/examples, get a reference to this service class with
 * {@link LectureService#getInstance()}.
 */
public class LectureService {

	private static LectureService instance;
	private static final Logger LOGGER = Logger.getLogger(LectureService.class.getName());

	private final HashMap<Long, Lecture> contacts = new HashMap<>();
	private long nextId = 0;

	private LectureService() {
	}

	/**
	 * @return a reference to an example facade for Customer objects.
	 */
	public static LectureService getInstance() {
		if (instance == null) {
			instance = new LectureService();
			instance.ensureTestData();
		}
		return instance;
	}

	/**
	 * @return all available Customer objects.
	 */
	public synchronized List<Lecture> findAll() {
		return findAll(null);
	}

	/**
	 * Finds all Customer's that match given filter.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @return list a Customer objects
	 */
	public synchronized List<Lecture> findAll(String stringFilter) {
		ArrayList<Lecture> arrayList = new ArrayList<>();
		for (Lecture contact : contacts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(LectureService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Lecture>() {

			@Override
			public int compare(Lecture o1, Lecture o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}

	/**
	 * Finds all Lecture's that match given filter and limits the resultset.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @param start
	 *            the index of first result
	 * @param maxresults
	 *            maximum result count
	 * @return list a Customer objects
	 */
	public synchronized List<Lecture> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Lecture> arrayList = new ArrayList<>();
		for (Lecture contact : contacts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(LectureService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Lecture>() {

			@Override
			public int compare(Lecture o1, Lecture o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}

	/**
	 * @return the amount of all customers in the system
	 */
	public synchronized long count() {
		return contacts.size();
	}

	/**
	 * Deletes a customer from a system
	 *
	 * @param value
	 *            the Customer to be deleted
	 */
	public synchronized void delete(Lecture value) {
		contacts.remove(value.getId());
	}

	/**
	 * Persists or updates customer in the system. Also assigns an identifier
	 * for new Customer instances.
	 *
	 * @param entry
	 */
	public synchronized void save(Lecture entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Customer is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
			return;
		}
		if (entry.getId() == null) {
			entry.setId(nextId++);
		}
		try {
			entry = (Lecture) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		contacts.put(entry.getId(), entry);
	}

	/**
	 * Sample data generation
	 */
	public void ensureTestData() {
		if (findAll().isEmpty()) {
			final String[] names = new String[] { "Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
					"Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
					"Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
					"Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
					"Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
					"Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
					"Jaydan Jackson", "Bernard Nilsen" };
			Random r = new Random(0);
			for (String name : names) {
				String[] split = name.split(" ");
				Lecture c = new Lecture();
				c.setFirstName(split[0]);
				c.setLastName(split[1]);
				c.setEmail(split[0].toLowerCase() + "@" + split[1].toLowerCase() + ".com");
				c.setStatus(SubjectEnum.values()[r.nextInt(SubjectEnum.values().length)]);
                int daysOld = 0 - r.nextInt(365 * 15 + 365 * 60);
                c.setBirthDate(LocalDate.now().plusDays(daysOld));
				save(c);
			}
		}
	}

}