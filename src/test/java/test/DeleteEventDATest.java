package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import utility.TestUtilityDataAccess;

class DeleteEventDATest {

	static DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	static TestUtilityDataAccess testDA = new TestUtilityDataAccess();

	private Event ev, ev2;

	@BeforeEach
	public void setUp() {
		sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		testDA = new TestUtilityDataAccess();
		testDA.deleteAllQuestions();
	}

	@Test
	// sut.deleteEvent: Se elimina un evento pero este evento no tiene preguntas
	// asociadas ya que no hay ninguna en la BD
	// asociadas
	void test1() {

		try {
			// configure the state of the system (create object in the dabatase)
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("08/10/2022");
			String eventText = "Event 1 Text Test 2";

			testDA.open();
			ev = testDA.addEvent(eventText, oneDate);
			assertEquals(0, ev.getQuestions().size());
			testDA.close();

			// invoke System Under Test (sut) and Assert
			Vector<Event> vecti = new Vector<Event>();
			Vector<Question> vecti2 = new Vector<Question>();
			assertTrue(sut.deleteEvent(ev));
			// el resultado es el esperado pero aun asi da error
			// assertEquals(ev2, sut.getAllEvents());
			assertEquals(0, sut.getAllEvents().size());
			assertEquals(0, sut.getAllQuestions().size());

			testDA.open();
			boolean eliminado = testDA.removeEvent(ev);

			testDA.close();
			assertFalse(eliminado);

		} catch (ParseException e) {
			fail("It should be correct: check the date format");
		}

		// Remove the created objects in the database (cascade removing)
		testDA.open();
		boolean b = testDA.removeEvent(ev);
		System.out.println("Removed event " + b);

		testDA.deleteAllQuestions();
		testDA.deleteAllEvents();

		testDA.close();

	}

	@Test
	// sut.deleteEvent: Se elimina un evento pero este evento no tiene preguntas
	// asociadas, sin embargo en este caso si hay preguntas en la BD
	// asociadas
	void test2() {

		try {
			// configure the state of the system (create object in the dabatase)
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("09/10/2022");
			String eventText = "Event1 Text Test2";
			Date twoDate = sdf.parse("10/10/2022");
			String eventText2 = "Event2 Text Test2";
			String queryText = "Query1 Text Test2 ";
			float qty = 0f;

			testDA.open();
			ev = testDA.addEvent(eventText, oneDate);
			ev2 = testDA.addEventWithQuestion(eventText2, twoDate, queryText, qty);
			assertEquals(0, ev.getQuestions().size());
			assertEquals(1, ev2.getQuestions().size());
			testDA.close();

			// invoke System Under Test (sut) and Assert
			Vector<Event> vecti = new Vector<Event>();
			Vector<Question> vecti2 = new Vector<Question>();
			assertTrue(sut.deleteEvent(ev));
			// el resultado es el esperado pero aun asi da error
			// assertEquals(ev2, sut.getAllEvents());
			assertEquals(1, sut.getAllEvents().size());
			assertEquals(1, sut.getAllQuestions().size());

			testDA.open();
			boolean eliminado = testDA.removeEvent(ev);
			testDA.close();
			assertFalse(eliminado);

		} catch (ParseException e) {
			fail("It should be correct: check the date format");
		}

		// Remove the created objects in the database (cascade removing)
		testDA.open();
//		boolean b = testDA.removeEvent(ev);
//		System.out.println("Removed event " + b);
		boolean b2 = testDA.removeEvent(ev2);
		System.out.println("Removed event " + b2);
		testDA.deleteAllQuestions();
		testDA.deleteAllEvents();
		testDA.close();

	}

	@Test
	// sut.deleteEvent: Existe un solo evento y es el que queremos eliminar. Además
	// este evento tiene una pregunta asociada
	void test3() {

		try {
			// configure the state of the system (create object in the dabatase)
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("11/10/2022");
			String eventText = "Event1 Text Test3";
			String queryText = "Query1 Text Test3";
			Float betMinimum = 2f;

			testDA.open();
			ev = testDA.addEventWithQuestion(eventText, oneDate, queryText, betMinimum);
			ev.addQuestion("why?", 5f);
			assertEquals(2, ev.getQuestions().size());
			testDA.close();

			// invoke System Under Test (sut) and Assert
			Vector<Event> vecti = new Vector<Event>();
			Vector<Question> vecti2 = new Vector<Question>();
			assertTrue(sut.deleteEvent(ev));

		} catch (ParseException e) {
			fail("It should be correct: check the date format");
		}

		// Remove the created objects in the database (cascade removing)
		testDA.open();
		boolean b = testDA.removeEvent(ev);
		System.out.println("Removed event " + b);
		testDA.deleteAllQuestions();
		testDA.deleteAllEvents();
		testDA.close();

	}

	@Test
	// sut.deleteEvent: Existe más de un evento y cada uno tiene preguntas, solo se
	// borraran el evento seleccionado y con el sus preguntas
	void test4() {

		try {
			// configure the state of the system (create object in the dabatase)
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("12/10/2022");
			Date twoDate = sdf.parse("13/10/2022");
			String eventText = "Event1 Text Test4";
			String queryText = "Query1 Text Test4";
			String eventText2 = "Event2 Text Test4";
			String queryText2 = "Query2 Text2 Test4";
			Float betMinimum = 2f;
			Float betMinimum2 = 4f;

			testDA.open();
			ev = testDA.addEventWithQuestion(eventText, oneDate, queryText, betMinimum);
			ev2 = testDA.addEventWithQuestion(eventText2, twoDate, queryText2, betMinimum2);
			ev.addQuestion("why?", 5f);
			assertEquals(2, ev.getQuestions().size());
			assertEquals(1, ev2.getQuestions().size());
			testDA.close();

			// invoke System Under Test (sut) and Assert
//			Vector<Event> vecti = new Vector<Event>();
//			Vector<Question> vecti2 = new Vector<Question>();
			assertTrue(sut.deleteEvent(ev));

		} catch (ParseException e) {
			fail("It should be correct: check the date format");
		}

		// Remove the created objects in the database (cascade removing)
		testDA.open();
		boolean b = testDA.removeEvent(ev);
		System.out.println("Removed event " + b);
		boolean b2 = testDA.removeEvent(ev2);
		System.out.println("Removed event " + b2);
		testDA.deleteAllQuestions();
		testDA.deleteAllEvents();
		testDA.close();

	}

	@Test
	// sut.deleteEvent: The event does not belong to the database.
	void test5() {
		try {
			// configure the state of the system (create object in the dabatase)
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date twoDate = sdf.parse("06/10/2022");
			String eventText2 = "Event 1 Text Test 5";
			Event ev2 = new Event(eventText2, twoDate);

			assertFalse(sut.deleteEvent(ev2));

		} catch (ParseException e) {
			fail("It should be correct: check the date format");
		}
		testDA.open();
		testDA.deleteAllQuestions();
		testDA.deleteAllEvents();
		testDA.close();
		// Remove the created objects in the database (cascade removing)
//		testDA.open();
//		boolean b = testDA.removeEvent(ev);
//		System.out.println("Removed event " + b);
//		testDA.close();
	}

	@Test
	// sut.deleteEvent: The event is null.
	void test6() {
		try {
			// configure the state of the system (create object in the dabatase)
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("07/10/2022");
			String eventText = "Event1 Text Test6";
			Event ev = new Event(null, eventText, oneDate);

			// invoke System Under Test (sut)
			Boolean eliminado = sut.deleteEvent(ev);

			// verify the results returned
			// he modificado el createQuestion()
			assertFalse(eliminado);

		} catch (ParseException e) {
			fail("It should be correct: check the date format");
		}
		testDA.open();
		testDA.deleteAllQuestions();
		testDA.deleteAllEvents();
		testDA.close();
	}

}