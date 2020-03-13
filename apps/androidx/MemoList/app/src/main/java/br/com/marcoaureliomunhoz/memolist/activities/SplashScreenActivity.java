package br.com.marcoaureliomunhoz.memolist.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.com.marcoaureliomunhoz.memolist.R;
import br.com.marcoaureliomunhoz.memolist.helpers.SharedPreferencesHelper;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        execute();
    }

    private void execute() {
        int splashCount = SharedPreferencesHelper.getInt(this, "spash_count", 0);
        if (splashCount == 0) {
            SharedPreferencesHelper.putInt(this, "spash_count", 3);
            ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1);
            poolExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    openFirstActivity();
                }
            }, 2, TimeUnit.SECONDS);
        } else {
            SharedPreferencesHelper.putInt(this, "spash_count", --splashCount);
            openFirstActivity();
        }
    }

    private void openFirstActivity() {
        Intent intent = new Intent(this, MemoListActivity.class);
        startActivity(intent);
        finish();
    }
}
