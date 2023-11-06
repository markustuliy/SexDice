package com.markustuliy.sexdice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        ImageButton goToMainActivity = findViewById(R.id.save_btn);

        SwitchAnal = findViewById(R.id.switchAnal);
        SwitchOral = findViewById(R.id.switchOral);
        RGGreen = findViewById(R.id.RG1);
        RGOrange = findViewById(R.id.RG2);
        RGRed = findViewById(R.id.RG3);

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
        setRadioButtonSelectedRed(RGRed, delayValue);
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
            case 8:
                radioButton = findViewById(R.id.RB5);
                break;
            case 16:
                radioButton = findViewById(R.id.RB10);
                break;
            case 24:
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

        int delayValue = getSelectedRadioButtonDelayValue(RGRed);
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
}
