package com.github.wnuk.myhero

import android.app.Application
import com.github.wnuk.myhero.infrastracture.receiver.ConnectionReceiver

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun setConnectionListener(listener: ConnectionReceiver.ConnectionRecieverListener){
        ConnectionReceiver.connectionRecieverListener = listener
    }

    companion object{
        @get:Synchronized
        lateinit var instance: MyApplication
    }
}