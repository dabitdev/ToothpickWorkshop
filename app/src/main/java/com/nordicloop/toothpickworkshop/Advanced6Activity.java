package com.nordicloop.toothpickworkshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nordicloop.mylibrary.FullName;
import com.nordicloop.toothpickworkshop.annotation.English;
import com.nordicloop.toothpickworkshop.annotation.Spanish;
import com.nordicloop.toothpickworkshop.binding.EnglishFullName;
import com.nordicloop.toothpickworkshop.binding.InjectableSurnameImpl;
import com.nordicloop.toothpickworkshop.binding.InjectedFullNamedNameProvider;
import com.nordicloop.toothpickworkshop.binding.Name;
import com.nordicloop.toothpickworkshop.binding.NameEnglishImpl;
import com.nordicloop.toothpickworkshop.binding.NameSpanishImpl;
import com.nordicloop.toothpickworkshop.binding.SpanishFullName;
import com.nordicloop.toothpickworkshop.binding.Surname;
import com.nordicloop.toothpickworkshop.binding.SurnameEnglishImpl;
import com.nordicloop.toothpickworkshop.binding.SurnameSpanishImpl;

import javax.inject.Inject;
import javax.inject.Named;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class Advanced6Activity extends AppCompatActivity {
  @Inject
  @Spanish
  protected FullName mSpanish;

  @Inject
  @Named("English")
  protected FullName mEnglish;

//  @Inject
//  @Named("English")
//  protected Name name;

  @Inject
  @Spanish
  protected Name name;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final Scope scope = Toothpick.openScope("ACTIVITY");

    scope.installModules(new Module() {{
      bind(Name.class).withName("English").toInstance(new NameEnglishImpl());
      bind(Name.class).withName(Spanish.class).toInstance(new NameSpanishImpl());
      bind(Surname.class).withName("English").toInstance(new SurnameEnglishImpl());
      bind(Surname.class).withName(Spanish.class).toInstance(new SurnameSpanishImpl());
      bind(FullName.class).withName(Spanish.class).to(SpanishFullName.class);
      bind(FullName.class).withName("English").to(EnglishFullName.class);
    }});

    Toothpick.inject(this, scope);
    ((TextView) findViewById(R.id.firstField)).setText(mSpanish.getFullName());
    ((TextView) findViewById(R.id.secondField)).setText(mEnglish.getFullName());

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
