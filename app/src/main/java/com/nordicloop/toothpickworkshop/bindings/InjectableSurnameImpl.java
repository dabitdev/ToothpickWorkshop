package com.nordicloop.toothpickworkshop.bindings;

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
