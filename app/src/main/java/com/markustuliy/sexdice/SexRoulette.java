package com.markustuliy.sexdice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SexRoulette extends AppCompatActivity {
    private TextView ResultRoulette;

    private final Random random = new Random();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_roulette);
        Button rollButton = findViewById(R.id.BTNRoulette);
        ResultRoulette = findViewById(R.id.ResultRoulette);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Генерируем случайное число от 1 до 10
                int diceRoulette = random.nextInt(7) + 1;

                // Отображаем результат на TextView
                String diceBodyPartResult = getResultDiceBodyPart(diceRoulette);
                ResultRoulette.setText(diceBodyPartResult);

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
}