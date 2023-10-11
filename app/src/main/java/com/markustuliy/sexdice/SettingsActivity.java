package com.markustuliy.sexdice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    public static final String SETTINGS = "settings";
    public static final String START_IP_1 = "startIPOne";
    public static final String START_IP_2 = "startIPTwo";
    public static final String START_IP_3 = "startIPThree";
    public static final String START_IP_4 = "startIPFour";
    public static final String END_IP_1 = "endIPOne";
    public static final String END_IP_2 = "endIPTwo";
    public static final String END_IP_3 = "endIPThree";
    public static final String END_IP_4 = "endIPFour";
    public static final String PORT = "port";
    public static final String TIMEOUT = "timeout";
    public static final String COMMAND = "command";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        initUI();
        setDefaultValues();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
    private void saveSettings(){

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        if(isValidateIP(getStartIp(), getEndIp())){
            editor.putInt(START_IP_1, getStartIp().getOne());
            editor.putInt(START_IP_2, getStartIp().getTwo());
            editor.putInt(START_IP_3, getStartIp().getThree());
            editor.putInt(START_IP_4, getStartIp().getFour());

            editor.putInt(END_IP_1, getEndIp().getOne());
            editor.putInt(END_IP_2, getEndIp().getTwo());
            editor.putInt(END_IP_3, getEndIp().getThree());
            editor.putInt(END_IP_4, getEndIp().getFour());

            editor.putInt(PORT, Integer.parseInt(editTextPort.getText().toString()));
            editor.putInt(TIMEOUT, Integer.parseInt(editTextTimeout.getText().toString()));
            editor.putString(COMMAND, editTextCommand.getText().toString());

            editor.apply();

            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}