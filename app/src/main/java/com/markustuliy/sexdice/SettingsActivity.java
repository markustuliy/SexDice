package com.markustuliy.sexdice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    private static final String APP_PREFERENCES = "Preferences";
    private SharedPreferences mSettings;
    public static final String SETTINGS = "settings";
    public static final boolean APP_SETTINGS_ANAL = false; // настройка anal
    public static final boolean APP_SETTINGS_ORAL = false; // настройка oral
    public static int APP_SETTINGS_GREEN = 1; // настройка Green
    public static int APP_SETTINGS_ORANGE = 1; // настройка Orande
    public static int APP_SETTINGS_RED = 1; // настройка Red
    private Switch SwitchAnal;
    private Switch SwitchOral;
    private RadioButton RBG1;
    private RadioButton RBG2;
    private RadioButton RBG3;
    private RadioButton RBO1;
    private RadioButton RBO2;
    private RadioButton RBO3;
    private RadioButton RBR1;
    private RadioButton RBR2;
    private RadioButton RBR3;
    private RadioGroup RGGreen;
    private RadioGroup RGOrange;
    private RadioGroup RGRed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        SwitchAnal = findViewById(R.id.switchAnal);

        SwitchOral = findViewById(R.id.switchOral);

        RGGreen = findViewById(R.id.RG1);
        RBG1 = findViewById(R.id.RBGreen8);
        RBG2 = findViewById(R.id.RBGreen16);
        RBG3 = findViewById(R.id.RBGreen24);

        RGOrange = findViewById(R.id.RG2);
        RBO1 = findViewById(R.id.RBorange8);
        RBO2 = findViewById(R.id.RBorange16);
        RBO3 = findViewById(R.id.RBorange24);

        RGRed = findViewById(R.id.RG3);
        RBR1 = findViewById(R.id.RBred8);
        RBR2 = findViewById(R.id.RBred16);
        RBR3 = findViewById(R.id.RBred24);

    }
    private void setDefaultValues() {
        mSettings = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        if(mSettings != null){
            SwitchAnal.setChecked(APP_SETTINGS_ANAL);
            SwitchOral.setChecked(APP_SETTINGS_ORAL);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
    private void saveSettings(){

        SharedPreferences.Editor editor = mSettings.edit();

        if(mSettings != null){
            if (!SwitchAnal.isChecked()) {
                SwitchAnal.setChecked(APP_SETTINGS_ANAL);
            }
            if (!SwitchOral.isChecked()) {
                SwitchOral.setChecked(APP_SETTINGS_ORAL);
            }
        }
            int checkedRBGreenId = RGGreen.getCheckedRadioButtonId();
            // Найдём переключатель по его id
            RadioButton myRBGreen = findViewById(checkedRBGreenId);
            APP_SETTINGS_GREEN = checkedRBGreenId;

            int checkedRBOrangeId = RGGreen.getCheckedRadioButtonId();
            // Найдём переключатель по его id
            RadioButton myRBOrange = findViewById(checkedRBOrangeId);

            int checkedRBRedId = RGGreen.getCheckedRadioButtonId();
        // Найдём переключатель по его id
            RadioButton myRBRed = findViewById(checkedRBRedId);


        }

            editor.apply();

            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

    }
}