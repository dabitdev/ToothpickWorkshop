package com.nordicloop.toothpickworkshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    RecyclerView recyclerView = findViewById(R.id.recyclerView);

    recyclerView.setHasFixedSize(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    Row[] rows = {
        new Row(Simple1Activity.class,
            "Simple binding of interfaces Name.class and Surname.class using `toInstance` to new NameEnglishImpl() and new SurnameEnglishImpl()."),
        new Row(Simple2Activity.class,
            "Simple binding of interfaces Name.class and Surname.class using `to` with implementations that have @Inject the default constructor."),
        new Row(Simple3Activity.class,
            "Similar than Simple2Activity but using on demand injection with Scope#getOrCreateScope()"),
        new Row(Advanced1Activity.class,
            "Using a second module to inject a class FullNameInjectionInsideImpl.class that opens the scope inside of the constructor."),
        new Row(Advanced2Activity.class,
            "Binding the interface FullName.class with ConstructorParamsFullNameImpl.class that contains injected parameters in the constructor." +
                " Important to realize that the order of the binding does not matter."),
        new Row(Advanced3Activity.class,
            "A simple module with params that binds a Provider<FullName.class> using toProviderInstance."),
        new Row(Advanced4Activity.class,
            "A simple usage of Provider<FullName.class> that has the members injected in InjectedFullNameProvider without using a constructor and binding the provider using toProvider."),
        new Row(Advanced5Activity.class,
            "Same as Advanced4Activity but now the injections happens in the constructor of Injected2FullNameProvider.java."),
        new Row(Advanced6Activity.class, ""),
        new Row(LibraryActivity.class,
            "Using a scope defined in the libraryDemo module, the scope is created by calling LibraryScope.getOrCreateScope()"),
        new Row(Library2Activity.class,
            "Using a scope defined in the libraryDemo module and installing a child scope in the application that overrides the binding defined in the library.")
    };

    recyclerView.addItemDecoration(new
        DividerItemDecoration(this,
        DividerItemDecoration.VERTICAL));

    RecyclerView.Adapter adapter = new DemoAdapter(rows);
    recyclerView.setAdapter(adapter);
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
