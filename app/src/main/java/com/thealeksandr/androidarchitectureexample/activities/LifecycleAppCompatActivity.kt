package com.thealeksandr.androidarchitectureexample.activities

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity

/**
* Created by Aleksandr Nikiforov on 8/1/17.
*/
open class LifecycleAppCompatActivity: AppCompatActivity(), LifecycleRegistryOwner {

    private val mRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return mRegistry
    }

}