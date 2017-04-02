package pl.orbitemobile.mvp

import android.app.Activity
import android.view.View


infix inline fun <reified T> View.bind(id: Int) = findViewById(id) as T

infix inline fun <reified T> Activity.bind(id: Int) = findViewById(id) as T