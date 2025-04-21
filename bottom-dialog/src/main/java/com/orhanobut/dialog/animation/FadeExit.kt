package com.orhanobut.dialog.animation

import android.animation.ObjectAnimator
import android.view.View

class FadeExit : BaseAnimatorSet() {
    override fun setAnimation(view: View?) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 1f, 0f).setDuration(duration)
        )
    }
}