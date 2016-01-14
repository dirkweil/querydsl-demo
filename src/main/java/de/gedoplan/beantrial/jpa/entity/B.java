package de.gedoplan.beantrial.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class B
{
  @Id
  int id;

  @ManyToOne
  C   c;
}
