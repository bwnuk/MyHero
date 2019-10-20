package com.github.wnuk.myhero.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.wnuk.myhero.R
import com.github.wnuk.myhero.infrastracture.db.CharacterDb
import com.github.wnuk.myhero.infrastracture.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule
import kotlin.coroutines.CoroutineContext

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val intent = Intent(this, BaseActivity::class.java)

        Timer("SettingUp", false).schedule(3000){
            startActivity(intent)
            finish()
        }
    }
}
