package root.iv.ui.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
    private Position position;

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
        int width = ((ViewGroup)button.getParent()).getWidth();
        float translationX = button.getTranslationX();

        moveOn(button, (Math.round(translationX) == 0) ? width-button.getWidth() : 0, 0);
        rotate(button, 90.0f);
    }

    @OnClick(R.id.buttonMove)
    public void clickMove(Button button) {
        float dx = button.getTranslationX();
        float dy = button.getTranslationY();

        if (Math.round(dx) == 0 && Math.round(dy) == 0) {
            position = Position.CENTER;
        }

        changePosition(button);
    }

    @OnClick(R.id.buttonScale)
    public void clickScale(Button button) {
        float scaleX = button.getScaleX();
        float scaleY = button.getScaleY();

        scaleX(button,  (Math.round(scaleX) == 1) ? 2 : 1);
        scaleY(button,  (Math.round(scaleY) == 1) ? 2 : 1);

        float translationY = button.getTranslationY();
        moveOn(button, 0, (translationY == 0) ? 250 : 0);

        float alpha = button.getAlpha();
        alpha(button, (Math.round(alpha) == 1) ? 0.15f : 1);
    }

    private void changePosition(View view) {
        RelativeLayout layout = (RelativeLayout) view.getParent();
        int width = layout.getWidth();
        int height = layout.getHeight();

        switch (position) {
            case PLACE_1:
                position = Position.PLACE_2;
                moveTo(view, width-view.getWidth(), 0);
                break;
            case PLACE_2:
                position = Position.PLACE_3;
                moveTo(view, width-view.getWidth(), height-view.getHeight());
                break;
            case PLACE_3:
                position = Position.PLACE_4;
                moveTo(view, 0, height-view.getHeight());
                break;
            default:
                position = Position.PLACE_1;
                moveTo(view, 0, 0);
        }
    }

    private void moveTo(View view, float x0, float y0) {
        SpringAnimation animX = new SpringAnimation(view, DynamicAnimation.X);
        SpringAnimation animY = new SpringAnimation(view, DynamicAnimation.Y);

        animX.animateToFinalPosition(x0);
        animY.animateToFinalPosition(y0);
    }

    private void scaleX(View view, float scale) {
        SpringAnimation anim = new SpringAnimation(view, DynamicAnimation.SCALE_X);
        anim.animateToFinalPosition(scale);
    }

    private void scaleY(View view, float scale) {
        SpringAnimation anim = new SpringAnimation(view, DynamicAnimation.SCALE_Y);
        anim.animateToFinalPosition(scale);
    }

    private void alpha(View view, float alpha) {
        SpringAnimation anim = new SpringAnimation(view, DynamicAnimation.ALPHA);
        anim.animateToFinalPosition(alpha);
    }

    private void moveOn(View view, int dx, int dy) {
        SpringAnimation animX = new SpringAnimation(view, DynamicAnimation.TRANSLATION_X);
        SpringAnimation animY = new SpringAnimation(view, DynamicAnimation.TRANSLATION_Y);

        animX.animateToFinalPosition(dx);
        animY.animateToFinalPosition(dy);
    }

    private void rotate(View view, float alpha) {
        SpringAnimation rotateAnimation = new SpringAnimation(view, DynamicAnimation.ROTATION);
        rotateAnimation.animateToFinalPosition(view.getRotation() + alpha);
    }
}

enum Position {
    CENTER,
    PLACE_1,
    PLACE_2,
    PLACE_3,
    PLACE_4
}
