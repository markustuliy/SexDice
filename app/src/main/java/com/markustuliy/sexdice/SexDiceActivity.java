package com.markustuliy.sexdice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SexDiceActivity extends AppCompatActivity {
    private TextView resultBodyPart;
    private TextView resultAction;
    private TextView resultStile;
    private TextView textname;
    private String Name1;
    private String Name2;
    private boolean isEvent1 = true;
        private final Random random = new Random();

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sex_dice);
            ImageButton rollButton = findViewById(R.id.rollButton);
            resultBodyPart = findViewById(R.id.resultBodyPart);
            resultAction = findViewById(R.id.resultAction);
            resultStile = findViewById(R.id.resultStile);
            textname = findViewById(R.id.textname);
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("Name1received")) {
                Name1 = intent.getStringExtra("Name1received");
            }
            if (intent != null && intent.hasExtra("Name2received")) {
                Name2 = intent.getStringExtra("Name2received");
            }
            rollButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isEvent1) {
                        // Выполните событие 1
                        // Например, вывод сообщения или что-то еще
                        textname.setText(Name1);
                    } else {
                        // Выполните событие 2
                        // Например, изменение текста или другие действия
                        textname.setText(Name2);
                    }
                    // Переключите состояние
                    isEvent1 = !isEvent1;
                    // Генерируем случайное число от 1 до 10
                    int diceBodyPart = random.nextInt(7) + 1;
                    int diceAction = random.nextInt(6) + 1;
                    int diceStile = random.nextInt(7) + 1;

                    // Отображаем результат на TextView
                    String diceBodyPartResult = getResultDiceBodyPart(diceBodyPart);
                    resultBodyPart.setText(diceBodyPartResult);
                    String diceActionResult = getResultDiceAction(diceAction);
                    resultAction.setText(diceActionResult);
                    String diceStileResult = getResultDiceStile(diceStile);
                    resultStile.setText(diceStileResult);
                    //resultTextView.setText(String.valueOf(diceBodyPart));
                }
            });
        }
    private String getResultDiceBodyPart(int number) {
        String resultText;
        switch (number) {
            case 1:
                resultText = "грудь";
                break;
            case 2:
                resultText = "ягодицы";
                break;
            case 3:
                resultText = "шею";
                break;
            case 4:
                resultText = "губы";
                break;
            case 5:
                resultText = "живот и нижнюю часть живота";
                break;
            case 6:
                resultText = "клитор/пенис";
                break;
            default:
                resultText = "спину";
                break;
        }
        return resultText;
    }
    private String getResultDiceAction(int number) {
        String resultText;
        switch (number) {
            case 1:
                resultText = "массируй";
                break;
            case 2:
                resultText = "покусывай";
                break;
            case 3:
                resultText = "гладь";
                break;
            case 4:
                resultText = "целуй";
                break;
            case 5:
                resultText = "целуй до засоса";
                break;
            default:
                resultText = "облизывай";
                break;
        }
        return resultText;
    }
    private String getResultDiceStile(int number) {
        String resultText;
        switch (number) {
            case 1:
                resultText = "очень ласково";
                break;
            case 2:
                resultText = "страстно";
                break;
            case 3:
                resultText = "медленно";
                break;
            case 4:
                resultText = "быстро";
                break;
            case 5:
                resultText = "глядя прямо в глаза";
                break;
            case 6:
                resultText = "без рук";
                break;
            default:
                resultText = "пенисом/клитором";
                break;
        }
        return resultText;
    }
}