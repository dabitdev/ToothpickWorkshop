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
import com.nordicloop.mylibrary.Singleton;
import com.nordicloop.toothpickworkshop.bindings.Name;
import com.nordicloop.toothpickworkshop.bindings.NameSpanishImpl;
import com.nordicloop.toothpickworkshop.bindings.Surname;
import com.nordicloop.toothpickworkshop.bindings.SurnameSpanishImpl;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class Library2Activity extends AppCompatActivity {
  @Inject
  protected FullName mFullName;

  private Scope scope;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //this will create scope with EnglishFullName implementation of Fullname
    Singleton.getInstance();

    scope = Toothpick.openScope("LIBRARY");

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

    Toothpick.inject(this, scope);
    ((TextView) findViewById(R.id.firstField)).setText(mFullName.getFullName());
    ((TextView) findViewById(R.id.secondField)).setText(null);

  }

  protected void installChildScope(){
    scope = Toothpick.openScopes("LIBRARY", "APPLICATION");
    scope.installTestModules(new Module(){{
      bind(FullName.class).toInstance(new FullName() {
        @Override
        public String getFullName() {
          return "lionel messi";
        }
      });
    }});
    Toothpick.inject(this, scope);
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
