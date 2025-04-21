package com.orhanobut.dialog.animation

import android.animation.ObjectAnimator
import android.view.View
import com.ved.framework.utils.StringUtils

class BounceBottomEnter : BaseAnimatorSet() {
    init {
        duration = 600
    }

    override fun setAnimation(view: View?) {
        val dm = view?.context?.resources?.displayMetrics
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f, 1f),  //
            ObjectAnimator.ofFloat(view, "translationY", 250 * StringUtils.parseFloat(dm?.density), -30f, 10f, 0f)
        )
    }
}