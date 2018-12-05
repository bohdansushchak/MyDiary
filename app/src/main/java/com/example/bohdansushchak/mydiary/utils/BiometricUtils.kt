package com.example.bohdansushchak.mydiary.utils

import android.Manifest
import android.content.Context
import android.os.Build
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat


val isBiometricPromptEnabled: Boolean
    get() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
    }

val isSdkVersionSupported: Boolean
    get() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
    }

fun isHardwareSupported(context: Context): Boolean {
    val fingerprintManager = FingerprintManagerCompat.from(context)
    return fingerprintManager.isHardwareDetected
}

fun isFingerprintAvailable(context: Context): Boolean {
    val fingerprintManager = FingerprintManagerCompat.from(context)
    return fingerprintManager.hasEnrolledFingerprints()
}

fun isPermissionGranted(context: Context): Boolean {
    return ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.USE_BIOMETRIC
    ) == PackageManager.PERMISSION_GRANTED
}