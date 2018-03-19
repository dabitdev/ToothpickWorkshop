package com.nordicloop.toothpickworkshop;

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
import com.nordicloop.toothpickworkshop.bindings.InjectableNameImpl;
import com.nordicloop.toothpickworkshop.bindings.InjectableSurnameImpl;
import com.nordicloop.toothpickworkshop.bindings.Injected2FullNameProvider;
import com.nordicloop.toothpickworkshop.bindings.Name;
import com.nordicloop.toothpickworkshop.bindings.Surname;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class Advanced5Activity extends AppCompatActivity {
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
        Snackbar.make(view, "No more demos", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
        Toothpick.reset(scope);
        Toothpick.closeScope(scope);
      }
    });

    scope.installModules(new Module() {{
      bind(Name.class).to(InjectableNameImpl.class);
      bind(Surname.class).to(InjectableSurnameImpl.class);
      bind(FullName.class).toProvider(Injected2FullNameProvider.class);
    }});

    Toothpick.inject(this, scope);
    ((TextView) findViewById(R.id.firstField)).setText(mFullName.getFullName());
    ((TextView) findViewById(R.id.secondField)).setText(null);

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
