package com.nordicloop.toothpickworkshop.binding;

import javax.inject.Inject;

public class InjectableNameImpl implements Name {
  @Inject
  public InjectableNameImpl() {

  }

  @Override
  public String getName() {
    return "didac";
  }
}
