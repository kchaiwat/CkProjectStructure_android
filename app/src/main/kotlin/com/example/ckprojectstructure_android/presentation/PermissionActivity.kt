package com.example.ckprojectstructure_android.presentation

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.util.*

abstract class PermissionActivity : AppCompatActivity() {

    internal fun checkPermissionsGranted(
        permissions: Collection<String>,
        onPermissionsGranted: () -> Unit
    ) {
        Dexter.withActivity(this)
            .withPermissions(permissions)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.isAnyPermissionPermanentlyDenied) {
                        val permissionDenied: ArrayList<String> = arrayListOf()
                        report.deniedPermissionResponses.forEach {
                            permissionDenied.add(
                                it.requestedPermission.name.replace(
                                    "android.permission.",
                                    ""
                                )
                            )
                        }
                        showSettingsDialog(permissionDenied.toString())
                    } else {
                        if (report.areAllPermissionsGranted()) {
                            onPermissionsGranted()
                        } else {
                            finish()
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).onSameThread()
            .check()
    }

    internal fun showSettingsDialog(message: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle("Need Permissions")
            .setCancelable(false)
            .setMessage("This app needs $message permission to use this feature. You can grant them in app settings.")
            .setPositiveButton("GOTO SETTINGS") { dialog, _ ->
                dialog.cancel()
                openAppSettings()
            }.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
                finish()
            }
        builder.show()
    }

    private fun openAppSettings() {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
            startActivityForResult(this, 101)
        }
    }

    internal fun openLocationSetting() {
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }
}