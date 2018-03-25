package com.nordicloop.toothpickworkshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nordicloop.toothpickworkshop.bindings.InjectableNameImpl;
import com.nordicloop.toothpickworkshop.bindings.InjectableSurnameImpl;
import com.nordicloop.toothpickworkshop.bindings.Name;
import com.nordicloop.toothpickworkshop.bindings.Surname;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class Simple3Activity extends AppCompatActivity {
  protected Name mName;
  protected Surname mSurname;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final Scope scope = Toothpick.openScope("ACTIVITY");
    Toothpick.reset(scope);

    scope.installModules(new Module() {{
      bind(Name.class).to(InjectableNameImpl.class);
      bind(Surname.class).to(InjectableSurnameImpl.class);
    }});

    mName = scope.getInstance(Name.class);
    mSurname = scope.getInstance(Surname.class);

    ((TextView) findViewById(R.id.firstField)).setText(mName.getName());
    ((TextView) findViewById(R.id.secondField)).setText(mSurname.getSurname());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
