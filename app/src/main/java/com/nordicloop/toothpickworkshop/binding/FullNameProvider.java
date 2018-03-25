package com.nordicloop.toothpickworkshop.binding;

import com.nordicloop.mylibrary.FullName;

import javax.inject.Provider;

public class FullNameProvider implements Provider<FullName> {
  Name name;
  Surname surname;

  public FullNameProvider(Name name, Surname surname) {
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