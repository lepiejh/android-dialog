package com.orhanobut.dialog.animation

import android.animation.Animator
import android.animation.AnimatorSet
import android.view.View
import android.view.animation.Interpolator

abstract class BaseAnimatorSet {
    /** 动画时长,系统默认250  */
    protected var duration: Long = 300
    @JvmField
    protected var animatorSet = AnimatorSet()
    private var interpolator: Interpolator? = null
    private var delay: Long = 0
    private var listener: AnimatorListener? = null
    abstract fun setAnimation(view: View?)
    protected fun start(view: View) {
        reset(view)
        setAnimation(view)
        animatorSet.duration = duration
        if (interpolator != null) {
            animatorSet.interpolator = interpolator
        }
        if (delay > 0) {
            animatorSet.startDelay = delay
        }
        if (listener != null) {
            animatorSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {
                    listener!!.onAnimationStart(animator)
                }

                override fun onAnimationRepeat(animator: Animator) {
                    listener!!.onAnimationRepeat(animator)
                }

                override fun onAnimationEnd(animator: Animator) {
                    listener!!.onAnimationEnd(animator)
                }

                override fun onAnimationCancel(animator: Animator) {
                    listener!!.onAnimationCancel(animator)
                }
            })
        }
        animatorSet.start()
    }

    /** 设置动画时长  */
    fun duration(duration: Long): BaseAnimatorSet {
        this.duration = duration
        return this
    }

    /** 设置动画时长  */
    fun delay(delay: Long): BaseAnimatorSet {
        this.delay = delay
        return this
    }

    /** 设置动画插补器  */
    fun interpolator(interpolator: Interpolator?): BaseAnimatorSet {
        this.interpolator = interpolator
        return this
    }

    /** 动画监听  */
    fun listener(listener: AnimatorListener?): BaseAnimatorSet {
        this.listener = listener
        return this
    }

    /** 在View上执行动画  */
    fun playOn(view: View) {
        start(view)
    }

    interface AnimatorListener {
        fun onAnimationStart(animator: Animator?)
        fun onAnimationRepeat(animator: Animator?)
        fun onAnimationEnd(animator: Animator?)
        fun onAnimationCancel(animator: Animator?)
    }

    companion object {
        fun reset(view: View) {
            view.alpha = 1f
            view.scaleX = 1f
            view.scaleY = 1f
            view.translationX = 0f
            view.translationY = 0f
            view.rotation = 0f
            view.rotationY = 0f
            view.rotationX = 0f
        }
    }
}