package com.chihwhsu.noodoeassigment.ext

import androidx.fragment.app.Fragment
import com.chihwhsu.noodoeassigment.factory.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory()
}