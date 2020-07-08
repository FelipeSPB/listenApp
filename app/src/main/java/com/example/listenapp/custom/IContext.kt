package com.example.listenapp.custom

import android.app.Activity
import android.app.Dialog
import android.content.ContextWrapper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDialog
import android.view.View
import com.example.listenapp.custom.adapter.ItemViewBuilder
import custom.activity


interface IContext {

    val act
        get() : Activity = when (this) {
            is Fragment -> requireContext() as Activity
            is View                  -> context as Activity
            is ItemViewBuilder<*, *> -> context as Activity
            is Dialog                -> context as Activity
            is ContextWrapper        -> baseContext.activity
            else                     -> this as Activity
        }
}