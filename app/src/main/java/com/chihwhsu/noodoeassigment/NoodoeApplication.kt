package com.chihwhsu.noodoeassigment

import android.app.Application
import kotlin.properties.Delegates

class NoodoeApplication : Application() {

    companion object {
        var instance: NoodoeApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}