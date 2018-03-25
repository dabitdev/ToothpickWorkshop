package com.nordicloop.toothpickworkshop.binding;

import com.nordicloop.mylibrary.FullName;
import com.nordicloop.toothpickworkshop.annotation.English;
import com.nordicloop.toothpickworkshop.annotation.Spanish;

import javax.inject.Inject;
import javax.inject.Provider;

public class InjectedFullNamedNameProvider implements Provider<FullName> {
  @Inject
  @Spanish
  Name spanishName;

  @Inject
  @English
  Name englishName;

  @Inject
  Surname surname;

  private boolean isSpanish;
  public InjectedFullNamedNameProvider(boolean isSpanish) {
    this.isSpanish = isSpanish;
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