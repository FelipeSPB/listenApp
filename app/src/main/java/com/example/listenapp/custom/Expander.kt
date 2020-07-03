package com.example.listenapp.custom

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator

fun View.animateExpand(
        expand: Boolean = true,
        duration: Long = 500,
        vertical: Boolean = true
) =
        AnimatorSet().run {
            interpolator = AccelerateDecelerateInterpolator()
            play(
                    ValueAnimator.ofInt(
                            if (vertical) height else width,
                            newSizeValue(expand, vertical)
                    ).apply {
                        this.duration = duration
                        addUpdateListener(this, vertical)
                    })
            start()
        }

private fun View.newSizeValue(expand: Boolean, vertical: Boolean) = if (!expand) 0 else {
    measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    if (vertical) measuredHeight else measuredWidth
}

fun View.addUpdateListener(valueAnimator: ValueAnimator, vertical: Boolean) =
        valueAnimator.addUpdateListener { animator ->
            (animator.animatedValue as Int).let {
                if (vertical) layoutParams.height = it else layoutParams.width = it
            }
            requestLayout()
        }

fun View.animateExpandAbout(
        expand: Boolean = true,
        duration: Long = 500,
        vertical: Boolean = true
) =
        AnimatorSet().run {
            interpolator = AccelerateDecelerateInterpolator()
            play(
                    ValueAnimator.ofInt(
                            if (vertical) height else width,
                            newSizeValueAbout(expand, vertical)
                    ).apply {
                        this.duration = duration
                        addUpdateListener(this, vertical)
                    })
            start()
        }

private fun View.newSizeValueAbout(expand: Boolean, vertical: Boolean) = if (!expand) 0 else {
    measure(ViewGroup.LayoutParams.WRAP_CONTENT, 300)
    if (vertical) measuredHeight else measuredWidth
}
