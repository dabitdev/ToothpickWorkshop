package com.nordicloop.toothpickworkshop.binding;

import javax.inject.Inject;

public class InjectableSurnameImpl implements Surname {
  @Inject
  public InjectableSurnameImpl() {
  }

  @Override
  public String getSurname() {
    return "wonder";
  }
}
