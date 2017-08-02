package com.thealeksandr.androidarchitectureexample.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View

import com.thealeksandr.androidarchitectureexample.R
import com.thealeksandr.androidarchitectureexample.fragments.LiveDataFragment
import com.thealeksandr.androidarchitectureexample.fragments.ObserverFragment

class MainActivity : LifecycleAppCompatActivity() {

    private var lifecycleButton: View? = null
    private var livedataButton: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleButton = findViewById(R.id.lifecycle_button)
        livedataButton = findViewById(R.id.livedata_button)

        askForPermission()
    }

    private fun init() {

        livedataButton?.isEnabled = true
        lifecycleButton?.isEnabled = true

        lifecycleButton?.setOnClickListener({
            val fragment = ObserverFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_view, fragment).addToBackStack(null).commit()
        })

        livedataButton?.setOnClickListener({
            val fragment = LiveDataFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_view, fragment).addToBackStack(null).commit()
        })

    }


    private fun askForPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION), 1)

        } else {
            init()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    init()
                }
                return
            }
        }
    }


}
