package de.gedoplan.beantrial.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

/**
 * Basisklasse für JPA-Tests.
 *
 * Diese Klasse hält eine einmalig initialisierte EntityManagerFactory für alle Tests bereit. Zusätzlich wird vor jedem Test ein
 * EntityManager erstellt und eine Transaktion gestartet. Die Transaktion wird nach jedem Test wieder beendet und der
 * EntityManager geschlossen.
 *
 * @author dw
 *
 */
public abstract class TestBase
{
  protected static EntityManagerFactory entityManagerFactory;
  protected EntityManager               entityManager;

  /**
   * EntityManagerFactory liefern.
   *
   * Die EntityManagerFactory wird beim ersten Aufruf erstellt.
   *
   * @return EntityManagerFactory
   */
  protected synchronized EntityManagerFactory getEntityManagerFactory()
  {
    if (entityManagerFactory == null)
    {
      entityManagerFactory = Persistence.createEntityManagerFactory("test");
    }
    return entityManagerFactory;
  }

  /**
   * Test-Vorbereitung: EntityManager öffnen und Transakion starten.
   */
  @Before
  public void before()
  {
    this.entityManager = getEntityManagerFactory().createEntityManager();
    this.entityManager.getTransaction().begin();
  }

  /**
   * Test-Nachbereitung: Transaktion beenden und EntityManager schliessen.
   */
  @After
  public void after()
  {
    try
    {
      EntityTransaction transaction = this.entityManager.getTransaction();
      if (transaction.isActive())
      {
        if (!transaction.getRollbackOnly())
        {
          transaction.commit();
        }
        else
        {
          transaction.rollback();
        }
      }
    }
    finally
    {
      try
      {
        this.entityManager.close();
      }
      catch (Exception e) // CHECKSTYLE:IGNORE
      {
        // ignore
      }
    }
  }

}
