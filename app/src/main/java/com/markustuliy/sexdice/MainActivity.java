package com.markustuliy.sexdice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdSize;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.common.MobileAds;

public class MainActivity extends AppCompatActivity {
    private String ETName1String;
    private String ETName2String;
    private BannerAdView mBannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, () -> {
            // Инициализация Yandex Mobile Ads
        });
        ImageButton goToSexDiceActivity = findViewById(R.id.SexDiceButton);
        ImageButton goToSexRouletteActivity = findViewById(R.id.SexRouletteButton);
        ImageButton goToSettingsActivity = findViewById(R.id.SettingButton);
        MobileAds.initialize(this, () -> {
            // now you can use ads
        });


        // Получение текста из EditText
        EditText ETNameMale = findViewById(R.id.ETName3); // Замените R.id.editText на ваш ID EditText
        EditText ETNameFamale = findViewById(R.id.ETName4);

        goToSexDiceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SexDiceActivity.class);
                intent.putExtra("Name1received", ETName1String);
                intent.putExtra("Name2received", ETName2String);
                startActivity(intent);
            }
        });
        goToSexRouletteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SexRoulette.class);
                intent.putExtra("Name1received", ETName1String);
                intent.putExtra("Name2received", ETName2String);
                startActivity(intent);
            }
        });
        goToSettingsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        ETNameMale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Вызывается перед изменением текста
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Вызывается во время изменения текста
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Вызывается после изменения текста
                ETName1String = ETNameMale.getText().toString();

                // В этом месте можно сохранить текст или выполнять другие действия с ним
                // Например, сохранить текст в SharedPreferences, базу данных или переменную
            }
        });
        ETNameFamale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Вызывается перед изменением текста
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Вызывается во время изменения текста
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Вызывается после изменения текста
                ETName2String = ETNameFamale.getText().toString();

                // В этом месте можно сохранить текст или выполнять другие действия с ним
                // Например, сохранить текст в SharedPreferences, базу данных или переменную
            }
        });
        mBannerAd = loadBannerAd(getAdSize());
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