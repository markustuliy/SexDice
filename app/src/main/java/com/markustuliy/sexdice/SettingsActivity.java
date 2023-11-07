package com.markustuliy.sexdice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdSize;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.common.MobileAds;

public class SettingsActivity extends AppCompatActivity {

    protected static final String APP_PREFERENCES = "Preferences";
    private SharedPreferences mSettings;

    // Создайте ключи для каждого `RadioGroup` и других параметров
    public static final String APP_SETTINGS_ANAL = "settings_anal";
    public static final String APP_SETTINGS_ORAL = "settings_oral";
    public static final String APP_SETTINGS_DELAY = "settings_delay";
    public static final String APP_SETTINGS_GREEN = "settings_green";
    public static final String APP_SETTINGS_ORANGE = "settings_orange";
    public static final String APP_SETTINGS_RED = "settings_red";

    private Switch SwitchAnal;
    private Switch SwitchOral;
    private RadioGroup RGGreen;
    private RadioGroup RGOrange;
    private RadioGroup RGRed;
    private RadioGroup RGDelay;
    private BannerAdView mBannerAd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        MobileAds.initialize(this, () -> {
            // Инициализация Yandex Mobile Ads
        });
        ImageButton goToMainActivity = findViewById(R.id.save_btn);

        SwitchAnal = findViewById(R.id.switchAnal);
        SwitchOral = findViewById(R.id.switchOral);
        RGGreen = findViewById(R.id.RG1);
        RGOrange = findViewById(R.id.RG2);
        RGRed = findViewById(R.id.RG3);
        RGDelay = findViewById(R.id.RGDelay1);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        // Устанавливаем сохраненные значения
        SwitchAnal.setChecked(mSettings.getBoolean(APP_SETTINGS_ANAL, false));
        SwitchOral.setChecked(mSettings.getBoolean(APP_SETTINGS_ORAL, false));

        int greenValue = mSettings.getInt(APP_SETTINGS_GREEN, 8);
        setRadioButtonSelectedGreen(RGGreen, greenValue);

        int orangeValue = mSettings.getInt(APP_SETTINGS_ORANGE, 8);
        setRadioButtonSelectedOrange(RGOrange, orangeValue);

        int redValue = mSettings.getInt(APP_SETTINGS_RED, 8);
        setRadioButtonSelectedRed(RGRed, redValue);

        int delayValue = mSettings.getInt(APP_SETTINGS_DELAY, 5);
        setRadioButtonDelay(RGDelay, delayValue);
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mBannerAd = loadBannerAd(getAdSize());
    }

    private void setRadioButtonSelectedGreen(RadioGroup radioGroup, int value) {
        RadioButton radioButton;
        switch (value) {
            case 8:
                radioButton = findViewById(R.id.RBGreen8);
                break;
            case 16:
                radioButton = findViewById(R.id.RBGreen16);
                break;
            case 24:
                radioButton = findViewById(R.id.RBGreen24);
                break;
            default:
                radioButton = findViewById(R.id.RBGreen8); // Значение по умолчанию
        }
        radioGroup.check(radioButton.getId());
    }
    private void setRadioButtonSelectedOrange(RadioGroup radioGroup, int value) {
        RadioButton radioButton;
        switch (value) {
            case 8:
                radioButton = findViewById(R.id.RBorange8);
                break;
            case 16:
                radioButton = findViewById(R.id.RBorange16);
                break;
            case 24:
                radioButton = findViewById(R.id.RBorange24);
                break;
            default:
                radioButton = findViewById(R.id.RBorange8); // Значение по умолчанию
        }
        radioGroup.check(radioButton.getId());
    }
    private void setRadioButtonSelectedRed(RadioGroup radioGroup, int value) {
        RadioButton radioButton;
        switch (value) {
            case 8:
                radioButton = findViewById(R.id.RBred8);
                break;
            case 16:
                radioButton = findViewById(R.id.RBred16);
                break;
            case 24:
                radioButton = findViewById(R.id.RBred24);
                break;
            default:
                radioButton = findViewById(R.id.RBred8); // Значение по умолчанию
        }
        radioGroup.check(radioButton.getId());
    }
    private void setRadioButtonDelay(RadioGroup radioGroup, int value) {
        RadioButton radioButton;
        switch (value) {
            case 5:
                radioButton = findViewById(R.id.RB5);
                break;
            case 10:
                radioButton = findViewById(R.id.RB10);
                break;
            case 15:
                radioButton = findViewById(R.id.RB15);
                break;
            default:
                radioButton = findViewById(R.id.RB5); // Значение по умолчанию
        }
        radioGroup.check(radioButton.getId());
    }

    private void saveSettings() {
        // Сохраняем значения в SharedPreferences при изменении настроек
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean(APP_SETTINGS_ANAL, SwitchAnal.isChecked());
        editor.putBoolean(APP_SETTINGS_ORAL, SwitchOral.isChecked());

        int greenValue = getSelectedRadioButtonGreenValue(RGGreen);
        editor.putInt(APP_SETTINGS_GREEN, greenValue);

        int orangeValue = getSelectedRadioButtonOrangeValue(RGOrange);
        editor.putInt(APP_SETTINGS_ORANGE, orangeValue);

        int redValue = getSelectedRadioButtonRedValue(RGRed);
        editor.putInt(APP_SETTINGS_RED, redValue);

        int delayValue = getSelectedRadioButtonDelayValue(RGDelay);
        editor.putInt(APP_SETTINGS_DELAY, delayValue);

        editor.apply();
    }

    @SuppressLint("NonConstantResourceId")
    private int getSelectedRadioButtonGreenValue(RadioGroup radioGroup) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        switch (radioButtonId) {
            case R.id.RBGreen8:
                return 8;
            case R.id.RBGreen16:
                return 16;
            case R.id.RBGreen24:
                return 24;
            default:
                return 8; // Значение по умолчанию
        }
    }
    @SuppressLint("NonConstantResourceId")
    private int getSelectedRadioButtonOrangeValue(RadioGroup radioGroup) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        switch (radioButtonId) {
            case R.id.RBorange8:
                return 8;
            case R.id.RBorange16:
                return 16;
            case R.id.RBorange24:
                return 24;
            default:
                return 8; // Значение по умолчанию
        }
    }
    @SuppressLint("NonConstantResourceId")
    private int getSelectedRadioButtonRedValue(RadioGroup radioGroup) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        switch (radioButtonId) {
            case R.id.RBred8:
                return 8;
            case R.id.RBred16:
                return 16;
            case R.id.RBred24:
                return 24;
            default:
                return 8; // Значение по умолчанию

        }
    }
    @SuppressLint("NonConstantResourceId")
    private int getSelectedRadioButtonDelayValue(RadioGroup radioGroup) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        switch (radioButtonId) {
            case R.id.RB5:
                return 5;
            case R.id.RB10:
                return 10;
            case R.id.RB15:
                return 15;
            default:
                return 5; // Значение по умолчанию
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        saveSettings(); // Вызываем сохранение настроек при завершении активности
    }
    private BannerAdSize getAdSize() {
        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        final int screenHeight = Math.round(displayMetrics.heightPixels / displayMetrics.density);
        int adWidthPixels = getResources().getDisplayMetrics().widthPixels;
        final int adWidth = Math.round(adWidthPixels / displayMetrics.density);
        final int maxAdHeight = screenHeight / 2;

        return BannerAdSize.inlineSize(this, adWidth, maxAdHeight);
    }
    @NonNull
    private BannerAdView loadBannerAd(@NonNull final BannerAdSize adSize) {
        final BannerAdView bannerAd = findViewById(R.id.banner);
        bannerAd.setAdSize(adSize);
        bannerAd.setAdUnitId("R-M-3768661-1");
        bannerAd.setBannerAdEventListener(new BannerAdEventListener() {
            @Override
            public void onAdLoaded() {
                // If this callback occurs after the activity is destroyed, you
                // must call destroy and return or you may get a memory leak.
                // Note `isDestroyed` is a method on Activity.
                if (isDestroyed() && mBannerAd != null) {
                    mBannerAd.destroy();
                }
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                // Ad failed to load with AdRequestError.
                // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
            }

            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
            }

            @Override
            public void onLeftApplication() {
                // Called when user is about to leave application (e.g., to go to the browser), as a result of clicking on the ad.
            }

            @Override
            public void onReturnedToApplication() {
                // Called when user returned to application after click.
            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {
                // Called when an impression is recorded for an ad.
            }
        });
        final AdRequest adRequest = new AdRequest.Builder()
                // Methods in the AdRequest.Builder class can be used here to specify individual options settings.
                .build();
        bannerAd.loadAd(adRequest);
        return bannerAd;
    }
}
