package com.nordicloop.toothpickworkshop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nordicloop.mylibrary.LibraryScope;
import com.nordicloop.mylibrary.Name;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

public abstract class BaseActivity extends AppCompatActivity {
  @Inject
  Name baseName;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // This is not really needed if the activity that extends
    // this uses the same scope and it calls inject. Now, baseName will be injected twice.
    LibraryScope.getOrCreateScope();
    Scope scope = Toothpick.openScopes("LIBRARY");
    Toothpick.inject(this, scope);
  }
}
