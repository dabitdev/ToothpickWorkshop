package com.nordicloop.toothpickworkshop.binding;

import com.nordicloop.mylibrary.FullName;
import com.nordicloop.toothpickworkshop.annotation.Spanish;

import javax.inject.Inject;

public class SpanishFullName implements FullName {
  @Inject
  @Spanish
  Name name;

  @Inject
  @Spanish
  Surname surname;

  @Override
  public String getFullName() {
    return name.getName() + " " + surname.getSurname();
  }
}
