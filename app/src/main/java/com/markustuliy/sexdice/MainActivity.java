package com.markustuliy.sexdice;

import androidx.appcompat.app.AppCompatActivity;

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

        // Получение текста из EditText
        EditText ETName1 = findViewById(R.id.ETName1); // Замените R.id.editText на ваш ID EditText
        EditText ETName2 = findViewById(R.id.ETName2);

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
        ETName1.addTextChangedListener(new TextWatcher() {
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
                ETName1String = ETName1.getText().toString();

                // В этом месте можно сохранить текст или выполнять другие действия с ним
                // Например, сохранить текст в SharedPreferences, базу данных или переменную
            }
        });
        ETName2.addTextChangedListener(new TextWatcher() {
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
                ETName2String = ETName2.getText().toString();

                // В этом месте можно сохранить текст или выполнять другие действия с ним
                // Например, сохранить текст в SharedPreferences, базу данных или переменную
            }
        });
    }
}