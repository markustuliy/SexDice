package com.markustuliy.sexdice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String ETName1String;
    private String ETName2String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goToSexDiceActivity = findViewById(R.id.SexDiceButton);
        Button goToSexRouletteActivity = findViewById(R.id.SexRouletteButton);
        Button goToSettingsActivity = findViewById(R.id.SettingButton);

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
    }
}