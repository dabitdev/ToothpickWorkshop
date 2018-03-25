package com.nordicloop.toothpickworkshop.binding;

import com.nordicloop.mylibrary.FullName;
import com.nordicloop.mylibrary.Name;

import javax.inject.Inject;
import javax.inject.Named;

public class EnglishFullName implements FullName {
  @Inject
  @Named("English")
  Name name;

  @Inject
  @Named("English")
  Surname surname;

  @Override
  public String getFullName() {
    return name.getName() + " " + surname.getSurname();
  }
}
