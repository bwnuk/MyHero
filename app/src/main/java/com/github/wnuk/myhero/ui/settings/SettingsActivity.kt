package com.github.wnuk.myhero.ui.settings

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.wnuk.myhero.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    var allowToRotate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(findViewById(R.id.custom_toolbar_settings))
        readPref()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        settings_switch.isChecked = allowToRotate
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            writePref()
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    fun readPref() {
        val sharedPref = this.getSharedPreferences(
            getString(R.string.pref_file_key), Context.MODE_PRIVATE)  ?: return
        allowToRotate = sharedPref.getBoolean(getString(R.string.pref_file_rotate), true)
    }

    fun writePref(){
        val sharedPref = this.getSharedPreferences(
            getString(R.string.pref_file_key), Context.MODE_PRIVATE)  ?: return
        with (sharedPref.edit()) {
            putBoolean(getString(R.string.pref_file_rotate), settings_switch.isChecked)
            commit()
        }
        Log.d("SettingsActivity", "SharedPreferences: ${settings_switch.isChecked}")

    }

    override fun onStop() {
        super.onStop()
    }
}
