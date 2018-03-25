package com.nordicloop.toothpickworkshop.binding;


import com.nordicloop.mylibrary.FullName;
import com.nordicloop.mylibrary.Name;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

public class FullNameInjectionInsideImpl implements FullName {
  @Inject
  Name mName;
  @Inject
  Surname mSurname;

  public FullNameInjectionInsideImpl() {
    Scope scope = Toothpick.openScope("ACTIVITY");
    Toothpick.inject(this, scope);
  }

  @Override
  public String getFullName() {
    return mName.getName() + " " + mSurname.getSurname();
  }
}
