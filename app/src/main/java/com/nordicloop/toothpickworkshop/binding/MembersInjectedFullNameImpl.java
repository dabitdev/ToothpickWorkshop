package com.nordicloop.toothpickworkshop.binding;


import com.nordicloop.mylibrary.FullName;
import com.nordicloop.mylibrary.Name;

import javax.inject.Inject;

public class MembersInjectedFullNameImpl implements FullName {
  @Inject
  Name mName;
  @Inject
  Surname mSurname;

  @Override
  public String getFullName() {
    return mName.getName() + " " + mSurname.getSurname();
  }
}
