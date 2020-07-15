package com.parkho.sample;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class PhMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_vibrate_oneshot).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View a_view) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // 1초 진동
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(1000);
                }
            }
        });

        findViewById(R.id.btn_vibrate_pattern).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View a_view) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // 0.5초 대기 -> 1초 진동 -> 0.5초 대기 -> 1초 진동
                final long[] vibratePattern = new long[]{500, 1000, 500, 1000};
                // 반복 없음
                final int repeat = -1;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createWaveform(vibratePattern, repeat));
                } else {
                    vibrator.vibrate(vibratePattern, repeat);
                }
            }
        });
    }

}