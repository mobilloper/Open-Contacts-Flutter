package com.example.flutter_contacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {

    var lastResult: MethodChannel.Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)

        MethodChannel(flutterView, CHANNEL).setMethodCallHandler { call, result ->
            lastResult = result
            launchContactActivity()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                lastResult?.success("Done!")
            } else {
                lastResult?.error("Error", "Can't launch contacts", "")
            }
        }
    }

    private fun launchContactActivity() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.type = ContactsContract.Contacts.CONTENT_TYPE
        startActivityForResult(intent, REQUEST_CODE)
    }

    companion object {
        private const val CHANNEL = "flutter_contacts/launch_contacts"
        private const val REQUEST_CODE = 42
    }
}
