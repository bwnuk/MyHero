package com.github.wnuk.myhero.ui

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.github.wnuk.myhero.BuildConfig
import com.github.wnuk.myhero.MyApplication
import com.github.wnuk.myhero.R
import com.github.wnuk.myhero.infrastracture.receiver.ConnectionReceiver
import com.github.wnuk.myhero.ui.settings.SettingsActivity
import com.log4k.DefaultAppender
import com.log4k.Level
import com.log4k.Log4k
import com.log4k.android.AndroidAppender
import java.io.File
import java.io.PrintWriter

class BaseActivity : AppCompatActivity(), ConnectionReceiver.ConnectionRecieverListener {
    var isAllowToRotate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_main)
        pref()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(findViewById(R.id.custom_toolbar))

        baseContext.registerReceiver(ConnectionReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        MyApplication.instance.setConnectionListener(this)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        startActivity(Intent(this, SettingsActivity::class.java))
        return true
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (isAllowToRotate) {
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

    override fun onNetworkConnectionChange(isConnected: Boolean) {
        if (!isConnected) {
            Toast.makeText(this, "NO INTERNET", Toast.LENGTH_SHORT).show()
        }
    }

    fun pref() {
        val sharedPref = this.getSharedPreferences(
            getString(R.string.pref_file_key), Context.MODE_PRIVATE) ?: return
        isAllowToRotate = sharedPref.getBoolean(getString(R.string.pref_file_rotate), true)
        Log.d("BaseActivity", "SharedPreferences: ${isAllowToRotate}")
        if (isAllowToRotate) {
            this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        }
    }


}
