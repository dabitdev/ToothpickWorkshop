package com.nordicloop.mylibrary;


import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class Singleton {
  private static Singleton singleton;
  private static Scope scope;
  private Singleton() {
    scope = Toothpick.openScope("LIBRARY");
    scope.installModules(new Module(){{
      bind(FullName.class).toInstance(new EnglishFullName());
    }});
  }

  public static Singleton getInstance() {
    if(singleton == null) {
      singleton = new Singleton();
    }
    return  singleton;
  }

  public static void release(){
    if (singleton != null) {
      singleton = null;
    }
  }

  public Scope getScope(){
    return scope;
  }
}
