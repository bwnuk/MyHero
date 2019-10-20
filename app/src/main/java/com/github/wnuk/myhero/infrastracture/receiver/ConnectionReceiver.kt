package com.github.wnuk.myhero.infrastracture.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.github.wnuk.myhero.MyApplication

class ConnectionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, p1: Intent?) {
        val isConnected = checkConnection(context)
        if (connectionRecieverListener != null) {
            connectionRecieverListener!!.onNetworkConnectionChange(isConnected)
        }
    }

    interface ConnectionRecieverListener {
        fun onNetworkConnectionChange(isConnected: Boolean)
    }

    companion object {
        var connectionRecieverListener: ConnectionRecieverListener? = null

        val isConnected: Boolean
            get() {
                val cm =
                    MyApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                return (activeNetwork != null && activeNetwork.isConnected)
            }
    }

    private fun checkConnection(context: Context): Boolean {
        val cm = MyApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return (activeNetwork != null && activeNetwork.isConnected)
    }
}