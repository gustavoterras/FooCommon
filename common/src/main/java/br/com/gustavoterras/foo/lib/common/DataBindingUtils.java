package br.com.gustavoterras.foo.lib.common;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class DataBindingUtils {

    @BindingAdapter("fadeView")
    public static void fadeView(final View view, final Boolean show) {

        if (show && view.getVisibility() == View.VISIBLE) return;

        Animation anim = AnimationUtils.loadAnimation(view.getContext(), show ? android.R.anim.fade_in : android.R.anim.fade_out);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (show)
                    view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!show)
                    view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(anim);
    }
}
