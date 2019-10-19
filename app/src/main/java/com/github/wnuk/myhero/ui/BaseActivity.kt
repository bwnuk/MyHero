package com.github.wnuk.myhero.ui

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.github.wnuk.myhero.BuildConfig
import com.github.wnuk.myhero.R
import com.github.wnuk.myhero.ui.settings.SettingsActivity
import com.log4k.DefaultAppender
import com.log4k.Level
import com.log4k.Log4k
import com.log4k.android.AndroidAppender
import kotlinx.android.synthetic.main.base_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.File
import java.io.PrintWriter

class BaseActivity : AppCompatActivity() {
    var isRotate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_main)
        pref()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(findViewById(R.id.custom_toolbar))
        if (BuildConfig.DEBUG) {
            Log4k.add(Level.Verbose, ".*", AndroidAppender())
            Log4k.add(Level.Verbose, "com\\.log4k\\.sample\\..+", DefaultAppender())
            //Log4k.add(Level.Verbose, ".*", DefaultAppender(writer = PrintWriter(File(externalCacheDir, "debug-log.txt"))))
        } else {
            Log4k.add(
                Level.Assert,
                "com\\.log4k\\.sample\\..+",
                DefaultAppender(writer = PrintWriter(File(filesDir, "log.txt")))
            )
        }
    }

    override fun onRestart() {
        super.onRestart()
        pref()
    }

    override fun onResume() {
        super.onResume()
        pref()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun pref() {
        val sharedPref = this.getSharedPreferences(
            getString(R.string.pref_file_key), Context.MODE_PRIVATE) ?: return
        isRotate = sharedPref.getBoolean(getString(R.string.pref_file_rotate), true)
        Log.d("BaseActivity", "SharedPreferences: ${isRotate}")
        if (isRotate) {
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        startActivity(Intent(this, SettingsActivity::class.java))
        return true
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (isRotate) {
            super.setRequestedOrientation(requestedOrientation)
        }else{
            super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }
}
