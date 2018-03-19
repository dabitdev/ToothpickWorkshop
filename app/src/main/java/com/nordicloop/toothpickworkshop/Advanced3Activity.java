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

import com.nordicloop.mylibrary.FullName;
import com.nordicloop.toothpickworkshop.bindings.FullNameProvider;
import com.nordicloop.toothpickworkshop.bindings.Name;
import com.nordicloop.toothpickworkshop.bindings.NameEnglishImpl;
import com.nordicloop.toothpickworkshop.bindings.Surname;
import com.nordicloop.toothpickworkshop.bindings.SurnameEnglishImpl;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class Advanced3Activity extends AppCompatActivity {
  @Inject
  protected FullName mFullName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final Scope scope = Toothpick.openScope("ACTIVITY");

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Opening next example", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
        Intent intent = new Intent(view.getContext(), Advanced4Activity.class);
        Toothpick.reset(scope);
        Toothpick.closeScope(scope);
        startActivity(intent);
      }
    });

    final Name name = new NameEnglishImpl();
    final Surname surname = new SurnameEnglishImpl();

    scope.installModules(new ModuleImpl(name, surname));

    Toothpick.inject(this, scope);
    ((TextView) findViewById(R.id.firstField)).setText(mFullName.getFullName());
    ((TextView) findViewById(R.id.secondField)).setText(null);

  }

  private static class ModuleImpl extends Module {
    public ModuleImpl(Name name, Surname surname) {
      bind(FullName.class).toProviderInstance(new FullNameProvider(name, surname));
    }
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