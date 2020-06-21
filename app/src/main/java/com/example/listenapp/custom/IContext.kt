package custom

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.View
import custom.adapter.ItemViewBuilder


interface IContext {

    val activity
        get() : Activity = when (this) {
            is Fragment -> requireContext() as Activity
            is View                  -> context as Activity
            is ItemViewBuilder<*, *> -> context as Activity
            else                     -> this as Activity
        }
}