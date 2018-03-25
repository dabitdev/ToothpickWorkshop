package com.nordicloop.toothpickworkshop.bindings;

import com.nordicloop.mylibrary.FullName;
import com.nordicloop.toothpickworkshop.bindings.Name;
import com.nordicloop.toothpickworkshop.bindings.Surname;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class InjectedFullNamedNameProvider implements Provider<FullName> {

  @Inject
  @Named("spanish")
  Name spanishName;

  @Inject
  @Named("english")
  Name englishName;

  @Inject
  Surname surname;

  private boolean isSpanish;
  public InjectedFullNamedNameProvider() {
//    this.isSpanish = isSpanish;
  }

  @Override
  public FullName get() {
    if (isSpanish) {
      return new FullName() {
        @Override
        public String getFullName() {
          return spanishName.getName() + " " + surname.getSurname();
        }
      };
    } else {
      return new FullName() {
        @Override
        public String getFullName() {
          return englishName.getName() + " " + surname.getSurname();
        }
      };
    }
  }
}