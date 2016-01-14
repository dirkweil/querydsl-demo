package de.gedoplan.beantrial.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class A
{
  @Id
  int id;

  @ManyToOne
  B   b;
}
