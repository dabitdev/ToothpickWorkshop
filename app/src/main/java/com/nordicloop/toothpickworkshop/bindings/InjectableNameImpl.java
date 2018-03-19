package com.nordicloop.toothpickworkshop.bindings;

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
