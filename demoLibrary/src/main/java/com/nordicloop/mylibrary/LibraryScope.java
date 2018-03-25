package com.nordicloop.mylibrary;


import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class LibraryScope {
  private static LibraryScope instance;
  private static Scope scope;
  private LibraryScope() {
    scope = Toothpick.openScope("LIBRARY");
    scope.installModules(new Module(){{
      bind(FullName.class).toInstance(new EnglishFullName());
    }});
  }

  public static LibraryScope getOrCreateScope() {
    if(instance == null) {
      instance = new LibraryScope();
    }
    return instance;
  }

  public void release(){
    if (instance != null) {
      instance = null;
    }
  }

  public Scope getScope(){
    return scope;
  }
}
