package de.gedoplan.beantrial.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class C
{
  @Id
  int id;

  @ManyToOne
  D   d;
}
