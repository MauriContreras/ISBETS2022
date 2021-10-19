package dataAccess;

//hello
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Event;
import domain.Question;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccessContreras2 {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccessContreras2(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccessContreras2() {
		new DataAccess(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean deleteEvent(Event evento) {
		try {
			db.getTransaction().begin();

			// se ha cmbiado de evento.getEventDate() a evento.getEventNumber()
			try {
				Event event1 = db.find(Event.class, evento.getEventNumber());
				if (event1 == null) {
					return false;
				}
			} catch (IllegalArgumentException ex) {
				return false;
			}

			Query query1 = db.createQuery("DELETE FROM Event e WHERE e.getEventNumber()=?1");
			query1.setParameter(1, evento.getEventNumber());

			TypedQuery<Question> query2 = db.createQuery("SELECT qu FROM Question qu", Question.class);
			List<Question> preguntasDB = query2.getResultList();

			for (Question q : preguntasDB) {
				if (q.getEvent().equals(evento)) {
					System.out.println("pregunta eliminada: " + q);
					db.remove(q);
				} else {
					System.out.println("pregunta NO ELIMINADA");
				}
			}

			int events = query1.executeUpdate();
			db.getTransaction().commit();
			System.out.println("Evento eliminado: " + evento);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public Vector<Event> getAllEvents() {
		System.out.println(">> DataAccess: getAllEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev", Event.class);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	public Vector<Question> getAllQuestions() {
		System.out.println(">> DataAccess: getAllQuestions");
		Vector<Question> res = new Vector<Question>();
		TypedQuery<Question> query = db.createQuery("SELECT qu FROM Question qu", Question.class);
		List<Question> questions = query.getResultList();
		for (Question qu : questions) {
			System.out.println(qu.toString());
			res.add(qu);
		}
		return res;
	}

	public void deleteAllQuestions() {
		try {
			db.getTransaction().begin();
			db.createQuery("DELETE * FROM Question");
			db.getTransaction().commit();
			System.out.println("preguntas borradas de la DB");
		} catch (Exception e) {

		}

	}
}
