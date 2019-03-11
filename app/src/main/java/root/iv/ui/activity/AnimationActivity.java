package root.iv.ui.activity;

import android.animation.Animator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.R;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.animationLove)
    public void clickLove(LottieAnimationView view) {
        view.playAnimation();
        view.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setProgress(0.0f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @OnClick(R.id.animationLoading)
    public void clickLoading(LottieAnimationView view) {
        view.playAnimation();
    }

    @OnClick(R.id.buttonPlay)
    public void clickPlay(Button button) {
        move(button);
    }

    @OnClick(R.id.buttonParam)
    public void clickParam(Button button) {
//        alpha(button);
        scaleX(button);
        scaleY(button);
    }

    public void scaleX(View view) {
        SpringAnimation anim = new SpringAnimation(view, DynamicAnimation.SCALE_X);
        float scale = view.getScaleX();
        anim.animateToFinalPosition((Math.round(scale) == 2) ? 1 : 2);
    }

    public void scaleY(View view) {
        SpringAnimation anim = new SpringAnimation(view, DynamicAnimation.SCALE_Y);
        float scale = view.getScaleY();
        anim.animateToFinalPosition((Math.round(scale) == 2) ? 1 : 2);
    }

    public void alpha(View view) {
        SpringAnimation anim = new SpringAnimation(view, DynamicAnimation.ALPHA);
        float alpha = view.getAlpha();
        anim.animateToFinalPosition((Math.round(alpha) == 0) ? 1.0f : 0.0f);
    }

    private void move(View view) {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        int width = point.x;
        SpringAnimation animation = new SpringAnimation(view, DynamicAnimation.TRANSLATION_X);
        SpringAnimation rotateAnimation = new SpringAnimation(view, DynamicAnimation.ROTATION);
        if (viewIsAlignEnd(view, width)) {
            animation.animateToFinalPosition(0);
            rotateAnimation.animateToFinalPosition(view.getRotation() + 90f);

        }
        if (viewIsAlignStart(view)){
            animation.animateToFinalPosition(width - view.getWidth());
            rotateAnimation.animateToFinalPosition(view.getRotation() + 90f);
        }
    }

    private boolean viewIsAlignEnd(View view, int width) {
        return Math.round(view.getX() + view.getWidth()) == width;
    }

    private boolean viewIsAlignStart(View view) {
        return Math.round(view.getX()) == 0;
    }
}
