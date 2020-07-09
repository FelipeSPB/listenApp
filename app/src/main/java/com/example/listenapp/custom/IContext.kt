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
            is Fragment              -> requireContext().activity
            is View                  -> context.activity
            is ItemViewBuilder<*, *> -> context.activity
            is Dialog                -> context.activity
            else                     -> this as Activity
        }
}