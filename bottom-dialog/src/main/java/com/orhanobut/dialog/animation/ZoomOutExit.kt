package com.orhanobut.dialog.animation

import android.view.View
import android.animation.ObjectAnimator

class ZoomOutExit : BaseAnimatorSet() {
    override fun setAnimation(view: View?) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 0f),  //
            ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.3f, 0f),  //
            ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.3f, 0f)
        ) //
    }
}