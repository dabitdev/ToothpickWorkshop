package com.nordicloop.toothpickworkshop;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nordicloop.mylibrary.FullName;
import com.nordicloop.mylibrary.LibraryScope;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class Advanced7Activity extends BaseActivity {
  @Inject
  protected FullName mFullName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    LibraryScope.getOrCreateScope();
    Scope scope = Toothpick.openScopes("LIBRARY", "ADV7");

    setContentView(R.layout.activity_base);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    scope.installModules(new Module() {{
      bind(FullName.class).toInstance(new FullName() {
        @Override
        public String getFullName() {
          return "barack obama";
        }
      });
    }});

    Toothpick.inject(this, scope);
    ((TextView) findViewById(R.id.firstField)).setText(baseName.getName());
    ((TextView) findViewById(R.id.secondField)).setText(mFullName.getFullName());

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
