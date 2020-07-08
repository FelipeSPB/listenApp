package com.example.listenapp.custom

import android.content.Context
import android.content.DialogInterface.OnShowListener
import android.support.v7.app.AppCompatDialog
import android.view.View
import android.view.Window.FEATURE_NO_TITLE
import com.example.listenapp.R

import java.io.InvalidClassException

fun View.newPanel(
        layout: Any = R.layout.panel,
        onShow: OnShowListener? = null,
        init: (Panel.() -> Unit) = {}
) = Panel(context, onShow, init, layout).apply { show() }

class Panel(
  context: Context,
  onShow: OnShowListener? = null,
  init: (Panel.() -> Unit),
  layout: Any
) : AppCompatDialog(context, R.style.Panel) {

  init {
    requestWindowFeature(FEATURE_NO_TITLE)
    when (layout) {
      is Int  -> setContentView(layout)
      is View -> setContentView(layout)
      else    -> throw InvalidClassException("layout must be resourceID or View")
    }
    init(this)
    setOnShowListener(onShow)
  }
}