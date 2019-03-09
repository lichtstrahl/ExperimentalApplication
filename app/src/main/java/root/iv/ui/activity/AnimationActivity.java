package root.iv.ui.activity;

import android.animation.Animator;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.R;
import root.iv.app.App;

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
        moveToRight(button);

    }

    private void moveToRight(View view) {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        int width = point.x;
        SpringAnimation animation = new SpringAnimation(view, DynamicAnimation.TRANSLATION_X);
        if (viewIsAlignEnd(view, width)) {
            animation.animateToFinalPosition(0);
        }
        if (viewIsAlignStart(view)){
            animation.animateToFinalPosition(width - view.getWidth());
        }




    }

    private boolean viewIsAlignEnd(View view, int width) {
        return Math.round(view.getX() + view.getWidth()) == width;
    }

    private boolean viewIsAlignStart(View view) {
        return Math.round(view.getX()) == 0;
    }
}
