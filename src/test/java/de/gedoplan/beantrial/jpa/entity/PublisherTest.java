package de.gedoplan.beantrial.jpa.entity;

import de.gedoplan.beantrial.jpa.TestBase;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.querydsl.jpa.impl.JPAQueryFactory;

// CHECKSTYLE:OFF
/**
 * Test der Persistence-Fuktionalität bzgl. der Entity Publisher.
 *
 * @author dw
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PublisherTest extends TestBase
{

  public static Publisher   testPublisher1        = new Publisher("O'Melly Publishing");
  public static Publisher   testPublisher2        = new Publisher("Expert Press");
  public static Publisher   testPublisher3        = new Publisher("Books reloaded");
  public static Publisher[] testPublishers        =
                                                  {
                                                  testPublisher1, testPublisher2, testPublisher3
                                                  };
  public static Book        testBook11            = new Book("Better JPA Programs", "12345-6789-0", 340);
  public static Book        testBook12            = new Book("Inside JPA", "54321-9876-X", 265);
  public static Book        testBook13            = new Book("Java and Databases", "11111-2222-6", 850);
  public static Book        testBook21            = new Book("Java for Beginners", "564534-432-2", 735);
  public static Book        testBook22            = new Book("Java vs. C#", "333333-123-0", 145);
  public static Book        testBook23            = new Book("Optimizing Java Programs", "765432-767-8", 230);
  public static Book        testBook30            = new Book("Is there a World after Java?", null, 1);
  public static Book[]      testBooks             =
                                                  {
                                                  testBook11, testBook12, testBook13, testBook21, testBook22, testBook23, testBook30
                                                  };
  public static Book[]      testBooksOfPublisher1 =
                                                  {
                                                  testBook11, testBook12, testBook13
                                                  };
  public static Book[]      testBooksOfPublisher2 =
                                                  {
                                                  testBook21, testBook22, testBook23
                                                  };

  static
  {
    for (Book book : testBooksOfPublisher1)
    {
      book.setPublisher(testPublisher1);
    }

    for (Book book : testBooksOfPublisher2)
    {
      book.setPublisher(testPublisher2);
    }
  }

  @Test
  public void test_00_clearData()
  {
    this.entityManager.createNativeQuery("delete from " + Book.TABLE_NAME).executeUpdate();
    this.entityManager.createNativeQuery("delete from " + Publisher.TABLE_NAME).executeUpdate();
  }

  @Test
  public void test_01_insertData() throws Exception
  {
    for (Publisher publisher : testPublishers)
    {
      this.entityManager.persist(publisher);
    }
    for (Book book : testBooks)
    {
      this.entityManager.persist(book);
    }
  }

  /**
   * Alle Testdaten ausgeben.
   *
   * Dies ist kein Unit-Test im eigentlichen Sinne. Er kann probeweise für eine Ausgabe der Testdaten genutzt werden.
   *
   * Um dabei die zur DB gesendeten SQL-Befehle beobachten zu können, muss das Logging für den genutzten Provider eingeschaltet
   * werden (Eclipselink: persistence.xml; alle anderen: log4j.properties).
   */
  @Test
  @Ignore
  public void test_02_showAll()
  {
    TypedQuery<Publisher> query = this.entityManager.createQuery("select x from Publisher x", Publisher.class);
    List<Publisher> publishers = query.getResultList();
    for (Publisher publisher : publishers)
    {
      System.out.println(publisher);
      for (Book book : publisher.getBooks())
      {
        System.out.println("  " + book);
      }
    }
  }

  @Test
  public void test_03_showAll()
  {
    JPAQueryFactory queryFactory = new JPAQueryFactory(this.entityManager);

    QPublisher publisher = QPublisher.publisher;
    QBook book = QBook.book;

    List<String> resultList = queryFactory
        .select(publisher.name)
        .from(publisher)
        .join(publisher.books, book)
        .where(book.name.like("%Java%"))
        .fetch();

    for (String string : resultList)
    {
      System.out.println(string);
    }
  }
}
