package com.nordicloop.toothpickworkshop.binding;


import com.nordicloop.mylibrary.FullName;

import javax.inject.Inject;

public class ConstructorParamsFullNameImpl implements FullName {
  private Name mName;
  private Surname mSurname;

  @Inject
  public ConstructorParamsFullNameImpl(Name name, Surname surname) {
    mName = name;
    mSurname = surname;
  }

  @Override
  public String getFullName() {
    return mName.getName() + " " + mSurname.getSurname();
  }
}
