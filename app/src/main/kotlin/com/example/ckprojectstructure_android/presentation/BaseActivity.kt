package com.example.ckprojectstructure_android.presentation

import android.app.Dialog
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import com.example.ckprojectstructure_android.util.receiver.ConnectivityReceiver
import com.projectstructure.ck.utilitylibrary.view.PzDialogConfirmFragment

typealias OnCompleted<T> = (T) -> Unit
typealias OnFailed<E> = (E) -> Unit

abstract class BaseActivity : PermissionActivity() {

    private var connectivityReceiver: ConnectivityReceiver =
        ConnectivityReceiver()

    abstract fun setUpView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectivityReceiver)
    }

    fun showDialogMessage(title: String, message: String) {
        showDialogMessage(title, message, true, PzDialogConfirmFragment.State.NORMAL)
    }

    fun showDialogMessage(title: String, message: String, onOneCallback: ((Dialog) -> Unit)?) {
        showDialogMessage(
            title,
            message,
            false,
            PzDialogConfirmFragment.State.NORMAL,
            onOneCallback
        )
    }

    fun showDialogMessage(title: String, message: String, isCancelable: Boolean) {
        showDialogMessage(title, message, isCancelable, PzDialogConfirmFragment.State.NORMAL)
    }

    fun showDialogMessage(
        title: String,
        message: String,
        isCancelable: Boolean,
        onOneCallback: ((Dialog) -> Unit)?
    ) {
        showDialogMessage(
            title,
            message,
            isCancelable,
            PzDialogConfirmFragment.State.NORMAL,
            onOneCallback
        )
    }

    fun showDialogMessage(title: String, message: String, state: PzDialogConfirmFragment.State) {
        showDialogMessage(title, message, true, state)
    }

    fun showDialogMessage(
        title: String,
        message: String,
        isCancelable: Boolean,
        state: PzDialogConfirmFragment.State
    ) {
        showDialogMessage(title, message, isCancelable, state, null)
    }

    fun showDialogMessage(
        title: String,
        message: String,
        isCancelable: Boolean,
        state: PzDialogConfirmFragment.State,
        onOneCallback: ((Dialog) -> Unit)?
    ) {
        PzDialogConfirmFragment.Builder(supportFragmentManager)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(isCancelable)
            .setState(state)
            .setStrOk("ตกลง")
            .setOnDialogListener { _, dialog ->
                if (onOneCallback != null) {
                    onOneCallback(dialog)
                } else {
                    dialog.dismiss()
                }
            }.build()
    }

    fun showDialogMessage(
        title: String,
        message: String,
        isCancelable: Boolean,
        state: PzDialogConfirmFragment.State,
        onOneCallback: ((Dialog) -> Unit)?,
        onTwoCallback: ((Dialog) -> Unit)?
    ) {
        PzDialogConfirmFragment.Builder(supportFragmentManager)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(isCancelable)
            .setState(state)
            .setOnDialogListener(object : PzDialogConfirmFragment.OnTwoDialogListener {
                override fun onPositiveButtonClick(tag: Int, d: Dialog) {
                    if (onOneCallback != null) {
                        onOneCallback(d)
                    }
                }

                override fun onNegativeButtonClick(tag: Int, d: Dialog) {
                    if (onTwoCallback != null) {
                        onTwoCallback(d)
                    }
                }
            }).build()
    }

}