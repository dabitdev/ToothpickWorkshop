package com.nordicloop.toothpickworkshop;

import android.support.v7.app.AppCompatActivity;

import com.nordicloop.mylibrary.Name;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {
  @Inject
  Name baseName;
}
