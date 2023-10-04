package com.markustuliy.sexdice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class SexRoulette extends AppCompatActivity {
    private final Random random = new Random();

    private ArrayList<String> tasksLiteList = new ArrayList<>();
    private ArrayList<String> tasksMediumList = new ArrayList<>();
    private ArrayList<String> tasksExtrimeList = new ArrayList<>();
    private TextView ResultRoulette;
    private ImageButton rollButton;
    private TextView textname;
    private String Name1;
    private String Name2;
    private boolean isEvent1 = true;
    private boolean isMale;

    private static final String KEY_TASKS_LIST_LITE = "tasksLiteList";
    private static final String KEY_TASKS_MEDIUM_LITE = "tasksMediumList";
    private static final String KEY_TASKS_EXTRIME_LITE = "tasksExtrimeList";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_roulette);
        rollButton = findViewById(R.id.rouletteButton);
        ResultRoulette = findViewById(R.id.ResultRoulette);
        textname = findViewById(R.id.textname);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("Name1received")) {
            Name1 = intent.getStringExtra("Name1received");
        }
        if (intent != null && intent.hasExtra("Name2received")) {
            Name2 = intent.getStringExtra("Name2received");
        }
        if (savedInstanceState != null) {
            tasksLiteList = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_LITE);
            tasksMediumList = savedInstanceState.getStringArrayList(KEY_TASKS_MEDIUM_LITE);
            tasksExtrimeList = savedInstanceState.getStringArrayList(KEY_TASKS_EXTRIME_LITE);
            isMale = savedInstanceState.getBoolean("isMale");
        } else {
            // Легкие задания
            tasksLiteList.add("Легкое Задание 1: ...");
            tasksLiteList.add("Легкое Задание 2: мужчине ...");
            tasksLiteList.add("Легкое Задание 3: мужчине...");
            // Средние задания
            tasksMediumList.add("Среднее Задание 1: ...");
            tasksMediumList.add("Среднее Задание 2: ...");
            tasksMediumList.add("Среднее Задание 3: ...");
            // Экстрим задания задания
            tasksExtrimeList.add("Тяжелое Задание 1: ...");
            tasksExtrimeList.add("Тяжелое Задание 2: ...");
            tasksExtrimeList.add("Тяжелое Задание 3: ...");
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
                isMale = !isMale;

                // Отображаем результат на TextView
                if (!tasksLiteList.isEmpty()) {
                    int randomIndex;
                    String task;

                    // Добавьте проверку пола игрока перед выводом задания
                    do {
                        randomIndex = random.nextInt(tasksLiteList.size());
                        task = tasksLiteList.get(randomIndex);
                        tasksLiteList.remove(randomIndex);
                    } while (isMale && task.contains("мужчине")); // Пропустить задания для мужчин
                    task.replace("мужчине", "");
                    ResultRoulette.setText(task);
                } else {
                    if (!tasksMediumList.isEmpty()) {
                        int randomIndex = random.nextInt(tasksMediumList.size());
                        String task = tasksMediumList.get(randomIndex);
                        ResultRoulette.setText(task);
                        tasksMediumList.remove(randomIndex);
                    }else {
                        if (!tasksExtrimeList.isEmpty()) {
                            int randomIndex = random.nextInt(tasksExtrimeList.size());
                            String task = tasksExtrimeList.get(randomIndex);
                            ResultRoulette.setText(task);
                            tasksExtrimeList.remove(randomIndex);
                        }else{
                            ResultRoulette.setText("Все задания выполнены!");
                        }
                    }
                }

                //resultTextView.setText(String.valueOf(diceBodyPart));
            }
        });
    }
}