package test;
//package test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Vector;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import businessLogic.BLFacadeImplementation;
//import configuration.ConfigXML;
//import configuration.UtilDate;
//import dataAccess.DataAccess;
//import domain.Event;
//import domain.Question;
//
//class deleteEventDATest {
//
//	static DataAccess da = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
//	static BLFacadeImplementation sut = new BLFacadeImplementation(da);
//	private Event ev1, ev2;
//
//	@BeforeEach
//	public void setUp() {
//		sut = new BLFacadeImplementation(da);
//	}
//
//	@Test
//	void test1() {
//		try {
//			// configure the state of the system (create object in the dabatase)
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			Date oneDate = sdf.parse("05/10/2022");
//
//			da.open(true);
//			Event ev1 = new Event("partido1", UtilDate.newDate(2022, 12, 22));
//			boolean inserted = da.insertEvent(ev1);
//			da.close();
//
//			// invoke System Under Test (sut) and Assert
//			assertTrue(inserted);
//			assertTrue(sut.deleteEvent(ev1));
//
//		} catch (ParseException e) {
//			fail("It should be correct: check the date format");
//		}
//	}
//
//	@Test
//	void test2() {
//		try {
//			// configure the state of the system (create object in the dabatase)
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			Date oneDate = sdf.parse("05/10/2022");
//
//			da.open(true);
//			Event ev1 = new Event("partido1", oneDate);
//			boolean inserted = da.insertEvent(ev1);
//			da.close();
//
//			// invoke System Under Test (sut) and Assert
//			assertTrue(inserted);
//			assertTrue(sut.deleteEvent(ev1));
//
//		} catch (ParseException e) {
//			fail("It should be correct: check the date format");
//		}
//	}
//
//	@Test
//	void test3() {
//		try {
//			// configure the state of the system (create object in the dabatase)
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			Date oneDate = sdf.parse("05/10/2022");
//
//			da.open(true);
//			Event ev1 = new Event("partido1", oneDate);
//			boolean inserted = da.insertEvent(ev1);
//			ev1.addQuestion("¿Quién ganará?", 20.0f);
//			da.close();
//
//			// invoke System Under Test (sut) and Assert
//			assertTrue(inserted);
//			assertTrue(sut.deleteEvent(ev1));
//			Vector<Question> questions = new Vector<Question>();
//			// assertEquals(questions, ev1.getQuestions());
//
//			Vector<Event> ar = new Vector<Event>();
//			// Asi sabemos tambien que el evento se ha eliminado correctamente
//			assertEquals(ar, sut.getEvents(oneDate));
//
//		} catch (ParseException e) {
//			fail("It should be correct: check the date format");
//		}
//	}
//
//	@Test
//	void test4() {
//		try {
//			// configure the state of the system (create object in the dabatase)
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			Date oneDate = sdf.parse("05/10/2022");
//
//			da.open(true);
//			Event ev1 = new Event("partido1", oneDate);
//			boolean inserted = da.insertEvent(ev1);
//			ev1.addQuestion("¿Quién ganará?", 20.0f);
//			ev1.addQuestion("¿Quién metera mas goles?", 50.0f);
//			da.close();
//
//			// invoke System Under Test (sut) and Assert
//			assertTrue(inserted);
//			assertTrue(sut.deleteEvent(ev1));
//			Vector<Question> questions = new Vector<Question>();
//			// assertEquals(questions, ev1.getQuestions());
//
//			Vector<Event> ar = new Vector<Event>();
//			// Asi sabemos tambien que el evento se ha eliminado correctamente
//			assertEquals(ar, sut.getEvents(oneDate));
//
//		} catch (ParseException e) {
//			fail("It should be correct: check the date format");
//		}
//	}
//
//	@Test
//	public void Test5() {
//		try {
//			// configure the state of the system (create object in the dabatase)
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			Date oneDate = sdf.parse("05/10/2022");
//			Date oneDate2 = sdf.parse("06/10/2022");
//
//			da.open(true);
//			Event ev3 = new Event("partido2", UtilDate.newDate(2022, 12, 22));
//			da.close();
//			// invoke System Under Test (sut) and Assert
//			assertFalse(sut.deleteEvent(ev3));
//
//		} catch (ParseException e) {
//			fail("It should be correct: check the date format");
//		}
//
//	}
//
//	@Test
//	public void Test6() {
//		try {
//			// configure the state of the system (create object in the dabatase)
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			Date oneDate = sdf.parse("05/10/2022");
//			Date oneDate2 = sdf.parse("06/10/2022");
//
//			da.open(true);
//			Event ev3 = null;
//			da.close();
//			// invoke System Under Test (sut) and Assert
//			assertFalse(sut.deleteEvent(ev3));
//
//		} catch (ParseException e) {
//			fail("It should be correct: check the date format");
//		}
//
//	}
//
//}
