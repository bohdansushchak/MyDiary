package com.example.bohdansushchak.mydiary.helpers

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.CancellationSignal
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat

@RequiresApi(Build.VERSION_CODES.M)
class FingerprintHelper (private val context: Context) : FingerprintManager.AuthenticationCallback() {
    private lateinit var authenticationCallback: AuthenticationCallback

    internal fun startAuth(
        manager: FingerprintManager,
        cryptoObject: FingerprintManager.CryptoObject,
        authenticationCallback: AuthenticationCallback) {

        val cancellationSignal = CancellationSignal()
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        this. authenticationCallback = authenticationCallback

        // Check for Authentication
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null)
    }

    override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
        this.update(errorMessage = "Fingerprint Authentication error\n" + errString)
    }

    override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence) {
        this.update(errorMessage = "Fingerprint Authentication help\n" + helpString)
    }

    override fun onAuthenticationFailed() {
        this.update(errorMessage = "Fingerprint Authentication failed.")
    }

    override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult) {
        authenticationCallback.onAuthenticationSucceeded()
    }

    private fun update(errorMessage: String) {
        authenticationCallback.onAuthenticationFailed(errorMessage)
    }

    interface AuthenticationCallback {
        fun onAuthenticationSucceeded()
        fun onAuthenticationFailed(errorMessage: String)
    }
}