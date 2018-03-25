package com.nordicloop.toothpickworkshop.binding;

import com.nordicloop.mylibrary.FullName;

import javax.inject.Inject;
import javax.inject.Provider;

public class InjectedFullNameProvider implements Provider<FullName> {

  @Inject
  Name name;
  @Inject
  Surname surname;

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