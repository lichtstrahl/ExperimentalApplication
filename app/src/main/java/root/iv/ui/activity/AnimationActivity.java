package root.iv.ui.activity;

import android.animation.Animator;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

import androidx.appcompat.app.AppCompatActivity;
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
}
