package de.gedoplan.beantrial.jpa.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = Publisher.TABLE_NAME)
public class Publisher
{
  public static final String TABLE_NAME = "BEANTRIAL_PUBLISHER";

  @Id
  @GeneratedValue
  private Integer            id;

  private String             name;

  @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
  private List<Book>         books;

  protected Publisher()
  {
  }

  public Publisher(String name)
  {
    this.name = name;

    this.books = new ArrayList<>();
  }

  public Integer getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public List<Book> getBooks()
  {
    return Collections.unmodifiableList(this.books);
  }

  void addBook(Book book)
  {
    this.books.add(book);
  }

  void removeBook(Book book)
  {
    this.books.remove(book);
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (getClass() != obj.getClass())
    {
      return false;
    }
    Publisher other = (Publisher) obj;
    if (this.id == null)
    {
      return false;
    }
    else if (!this.id.equals(other.id))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return "Publisher [id=" + this.id + ", name=" + this.name + "]";
  }
}
