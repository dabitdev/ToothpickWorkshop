package com.nordicloop.toothpickworkshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nordicloop.toothpickworkshop.binding.ConstructorParamsFullNameImpl;
import com.nordicloop.mylibrary.FullName;
import com.nordicloop.toothpickworkshop.binding.InjectableNameImpl;
import com.nordicloop.toothpickworkshop.binding.InjectableSurnameImpl;
import com.nordicloop.mylibrary.Name;
import com.nordicloop.toothpickworkshop.binding.Surname;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class Advanced2Activity extends AppCompatActivity {
  @Inject
  protected FullName mFullName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final Scope scope = Toothpick.openScope("ACTIVITY");

    scope.installModules(new Module() {{
      bind(Name.class).to(InjectableNameImpl.class);
      bind(Surname.class).toInstance(new InjectableSurnameImpl());
      bind(FullName.class).to(ConstructorParamsFullNameImpl.class);
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
