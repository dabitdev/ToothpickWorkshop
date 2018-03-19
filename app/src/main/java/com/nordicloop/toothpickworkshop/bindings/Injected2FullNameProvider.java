package com.nordicloop.toothpickworkshop.bindings;

import com.nordicloop.mylibrary.FullName;

import javax.inject.Inject;
import javax.inject.Provider;

public class Injected2FullNameProvider implements Provider<FullName> {

  Name name;
  Surname surname;

  @Inject
  public Injected2FullNameProvider(Name name, Surname surname) {
    this.name = name;
    this.surname = surname;
  }

  @Override
  public FullName get() {
    return new FullName() {
      @Override
      public String getFullName() {
        return name.getName() + " " + surname.getSurname();
      }
    };
  }
}