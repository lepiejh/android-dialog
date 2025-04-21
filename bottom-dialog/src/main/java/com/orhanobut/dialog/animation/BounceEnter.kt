package com.orhanobut.dialog.animation

import android.animation.ObjectAnimator
import android.view.View

class BounceEnter : BaseAnimatorSet() {
    init {
        duration = 700
    }

    override fun setAnimation(view: View?) {
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f, 1f),  //
            ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1.05f, 0.95f, 1f),  //
            ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1.05f, 0.95f, 1f)
        )
        /**
         * <pre>
         * 另一种弹性实现:依据sweet-alert-dialog布局文件实现
         * ObjectAnimator oa_alpha = ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1).setDuration(90);
         *
         * AnimatorSet as1 = new AnimatorSet();
         * as1.playTogether(oa_alpha, ObjectAnimator.ofFloat(view, "scaleX", 0.7f, 1.05f).setDuration(135),//
         * ObjectAnimator.ofFloat(view, "scaleY", 0.7f, 1.05f).setDuration(135));
         *
         * AnimatorSet as2 = new AnimatorSet();
         * as2.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 0.95f).setDuration(105), //
         * ObjectAnimator.ofFloat(view, "scaleY", 1.05f, 0.95f).setDuration(105));
         *
         * AnimatorSet as3 = new AnimatorSet();
         * as3.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 0.95f, 1f).setDuration(60),//
         * ObjectAnimator.ofFloat(view, "scaleY", 0.95f, 1f).setDuration(60));
         *
         * animatorSet.playSequentially(as1, as2, as3);
        </pre> *
         *
         */
    }
}