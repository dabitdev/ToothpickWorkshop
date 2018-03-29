package com.nordicloop.toothpickworkshop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.nordicloop.mylibrary.Name
import com.nordicloop.toothpickworkshop.binding.NameEnglishImpl
import com.nordicloop.toothpickworkshop.binding.Surname
import com.nordicloop.toothpickworkshop.binding.SurnameEnglishImpl
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.content_base.*
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class Simple1KotlinActivity : AppCompatActivity() {
    @Inject
    lateinit var mName: Name
    @Inject
    lateinit var mSurname: Surname

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setSupportActionBar(toolbar)

        val scope = Toothpick.openScope("ACTIVITY")
        scope.installModules(object : Module() {
            init {
                bind<Name>(Name::class.java).toInstance(NameEnglishImpl())
                bind<Surname>(Surname::class.java).toInstance(SurnameEnglishImpl())
            }
        })

        Toothpick.inject(this, scope)
        firstField.text = mName.name
        secondField.text = mSurname.surname

        Toothpick.reset(scope)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
}
