package com.example.ckprojectstructure_android.util.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectivityReceiver : BroadcastReceiver() {

    private var mConnectivityReceiverListener: ConnectivityReceiverListener? = null

    override fun onReceive(context: Context, intent: Intent) {
        if (mConnectivityReceiverListener != null) {
            mConnectivityReceiverListener!!.onNetworkConnectionChanged(
                isConnectedOrConnecting(
                    context
                )
            )
        }
    }

    private fun isConnectedOrConnecting(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun setConnectivityReceiverListener(connectivityReceiverListener: ConnectivityReceiverListener) {
        mConnectivityReceiverListener = connectivityReceiverListener
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }
}