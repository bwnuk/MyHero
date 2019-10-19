package com.github.wnuk.myhero.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.github.wnuk.myhero.BuildConfig
import com.github.wnuk.myhero.R
import com.log4k.DefaultAppender
import com.log4k.Level
import com.log4k.Log4k
import com.log4k.android.AndroidAppender
import java.io.File
import java.io.PrintWriter

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        if (BuildConfig.DEBUG) {
            Log4k.add(Level.Verbose, ".*", AndroidAppender())
            Log4k.add(Level.Verbose, "com\\.log4k\\.sample\\..+", DefaultAppender())
            //Log4k.add(Level.Verbose, ".*", DefaultAppender(writer = PrintWriter(File(externalCacheDir, "debug-log.txt"))))
        } else {
            Log4k.add(Level.Assert, "com\\.log4k\\.sample\\..+", DefaultAppender(writer = PrintWriter(File(filesDir, "log.txt"))))
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }
}
