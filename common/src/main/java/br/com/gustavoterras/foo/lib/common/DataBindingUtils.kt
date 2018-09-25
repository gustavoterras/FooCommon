package br.com.gustavoterras.foo.lib.common

import android.databinding.BindingAdapter
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

object DataBindingUtils {

    @BindingAdapter("fadeView")
    fun fadeView(view: View, show: Boolean?) {

        if (show!! && view.visibility == View.VISIBLE) return

        val anim = AnimationUtils.loadAnimation(view.context, if (show) android.R.anim.fade_in else android.R.anim.fade_out)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                if (show)
                    view.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation) {
                if (!show)
                    view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

        view.startAnimation(anim)
    }
}
