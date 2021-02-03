package root.iv.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter;
import root.iv.R;

public class GUIActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    @BindView(R.id.viewSeek)
    TextView viewSeek;
    @BindView(R.id.seek)
    AppCompatSeekBar seekBar;
    @BindView(R.id.preview)
    GLSurfaceView surfaceView;

    private GPUImage gpuImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui);
        ButterKnife.bind(this);
        gpuImage = new GPUImage(this);
        gpuImage.setGLSurfaceView(surfaceView);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final float min = -1.0f;
                final float max = 1.0f;
                final float step = (max-min)/seekBar.getMax();
                final float value = min + progress*step;
                viewSeek.setText(String.format(Locale.ENGLISH, "%4.2f", value));
                gpuImage.setFilter(new GPUImageBrightnessFilter(value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick(R.id.buttonLoad)
    public void clickLoad() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case PICK_IMAGE:
                if (data != null) {
                    Uri imageUri = data.getData();
                    gpuImage.setImage(imageUri);
                }
                break;
        }
    }
}
