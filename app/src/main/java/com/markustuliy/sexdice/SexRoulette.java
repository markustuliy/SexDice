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

    private ArrayList<String> tasksLiteListMale = new ArrayList<>();
    private ArrayList<String> tasksLiteListWoman = new ArrayList<>();
    private ArrayList<String> tasksMediumListMale = new ArrayList<>();
    private ArrayList<String> tasksMediumListWoman = new ArrayList<>();
    private ArrayList<String> tasksExtrimeListMale = new ArrayList<>();
    private ArrayList<String> tasksExtrimeListWoman = new ArrayList<>();
    private TextView ResultRoulette;
    private ImageButton rollButton;
    private TextView textName;
    private String Name1;
    private String Name2;
    private boolean isEvent1 = true;
    private boolean isMale;
    private int i = 0;
    private int counter = 0;

    private static final String KEY_TASKS_LIST_LITE_MALE = "tasksLiteListMale";
    private static final String KEY_TASKS_LIST_LITE_WOMAN = "tasksLiteListWoman";
    private static final String KEY_TASKS_MEDIUM_LITE_MALE = "tasksMediumListMale";
    private static final String KEY_TASKS_MEDIUM_LITE_WOMAN = "tasksMediumListWoman";
    private static final String KEY_TASKS_EXTRIME_LITE_MALE = "tasksExtrimeListMale";
    private static final String KEY_TASKS_EXTRIME_LITE_WOMAN = "tasksExtrimeListWoman";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_roulette);
        rollButton = findViewById(R.id.rouletteButton);
        ResultRoulette = findViewById(R.id.ResultRoulette);
        textName = findViewById(R.id.textname);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("Name1received")) {
            Name1 = intent.getStringExtra("Name1received");
        }
        if (intent != null && intent.hasExtra("Name2received")) {
            Name2 = intent.getStringExtra("Name2received");
        }
        if (savedInstanceState != null) {
            tasksLiteListMale = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_LITE_MALE);
            tasksLiteListWoman = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_LITE_WOMAN);
            tasksMediumListMale = savedInstanceState.getStringArrayList(KEY_TASKS_MEDIUM_LITE_MALE);
            tasksMediumListWoman = savedInstanceState.getStringArrayList(KEY_TASKS_MEDIUM_LITE_WOMAN);
            tasksExtrimeListMale = savedInstanceState.getStringArrayList(KEY_TASKS_EXTRIME_LITE_MALE);
            tasksExtrimeListWoman = savedInstanceState.getStringArrayList(KEY_TASKS_EXTRIME_LITE_WOMAN);
            isMale = savedInstanceState.getBoolean("isMale");
        } else {
            // Легкие задания Мужчине
            tasksLiteListMale.add("Легкое Задание Мужчине 1: ...");
            tasksLiteListMale.add("Легкое Задание Мужчине 2: ...");
            tasksLiteListMale.add("Легкое Задание Мужчине 3: ...");
            tasksLiteListMale.add("Легкое Задание Мужчине 4: ...");
            tasksLiteListMale.add("Легкое Задание Мужчине 5: ...");
            tasksLiteListMale.add("Легкое Задание Мужчине 6: ...");
            // Легкие задания Женщине
            tasksLiteListWoman.add("Легкое Задание Женщине 1: ...");
            tasksLiteListWoman.add("Легкое Задание Женщине 2: ...");
            tasksLiteListWoman.add("Легкое Задание Женщине 3: ...");
            tasksLiteListWoman.add("Легкое Задание Женщине 4: ...");
            tasksLiteListWoman.add("Легкое Задание Женщине 5: ...");
            tasksLiteListWoman.add("Легкое Задание Женщине 6: ...");

            // Средние задания Мужчине
            tasksMediumListMale.add("Среднее Задание Мужчине 1: ...");
            tasksMediumListMale.add("Среднее Задание Мужчине 2: ...");
            tasksMediumListMale.add("Среднее Задание Мужчине 3: ...");
            tasksMediumListMale.add("Среднее Задание Мужчине 4: ...");
            tasksMediumListMale.add("Среднее Задание Мужчине 5: ...");
            tasksMediumListMale.add("Среднее Задание Мужчине 6: ...");
            // Средние задания Женщине
            tasksMediumListWoman.add("Среднее Задание Женщине 1: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 2: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 3: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 4: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 5: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 6: ...");

            // Экстрим задания Мужчине
            tasksExtrimeListMale.add("Тяжелое Задание Мужчине 1: ...");
            tasksExtrimeListMale.add("Тяжелое Задание Мужчине 2: ...");
            tasksExtrimeListMale.add("Тяжелое Задание Мужчине 3: ...");
            tasksExtrimeListMale.add("Тяжелое Задание Мужчине 4: ...");
            tasksExtrimeListMale.add("Тяжелое Задание Мужчине 5: ...");
            tasksExtrimeListMale.add("Тяжелое Задание Мужчине 6: ...");
            // Экстрим задания Женщине
            tasksExtrimeListWoman.add("Тяжелое Задание Женщине 1: ...");
            tasksExtrimeListWoman.add("Тяжелое Задание Женщине 2: ...");
            tasksExtrimeListWoman.add("Тяжелое Задание Женщине 3: ...");
            tasksExtrimeListWoman.add("Тяжелое Задание Женщине 4: ...");
            tasksExtrimeListWoman.add("Тяжелое Задание Женщине 5: ...");
            tasksExtrimeListWoman.add("Тяжелое Задание Женщине 6: ...");
        }
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEvent1) {
                    // Выполните событие 1
                    // Например, вывод сообщения или что-то еще
                    textName.setText(Name1);
                } else {
                    // Выполните событие 2
                    // Например, изменение текста или другие действия
                    textName.setText(Name2);
                }
                // Переключите состояние
                isEvent1 = !isEvent1;
                isMale = !isMale;

                int randomIndex;
                String task;
                CounterExample example = new CounterExample();

                int count1 = example.incrementCounter();


                if (isMale && count1 < 4){
                    if (!tasksLiteListMale.isEmpty()) {
                        randomIndex = random.nextInt(tasksLiteListMale.size());
                        task = tasksLiteListMale.get(randomIndex);
                        ResultRoulette.setText(task);
                        tasksLiteListMale.remove(randomIndex);
                    } else if (!tasksMediumListMale.isEmpty()){
                            randomIndex = random.nextInt(tasksMediumListMale.size());
                            task = tasksMediumListMale.get(randomIndex);
                            ResultRoulette.setText(task);
                            tasksMediumListMale.remove(randomIndex);
                    } else if (!tasksExtrimeListMale.isEmpty()) {
                                randomIndex = random.nextInt(tasksExtrimeListMale.size());
                                task = tasksExtrimeListMale.get(randomIndex);
                                ResultRoulette.setText(task);
                                tasksExtrimeListMale.remove(randomIndex);
                    } else {
                        ResultRoulette.setText("Все задания выполнены!");
                    }
                } else {
                    if (!tasksLiteListWoman.isEmpty()) {
                        randomIndex = random.nextInt(tasksLiteListWoman.size());
                        task = tasksLiteListWoman.get(randomIndex);
                        ResultRoulette.setText(task);
                        tasksLiteListWoman.remove(randomIndex);
                    } else if (!tasksMediumListWoman.isEmpty()){
                        randomIndex = random.nextInt(tasksMediumListWoman.size());
                        task = tasksMediumListWoman.get(randomIndex);
                        ResultRoulette.setText(task);
                        tasksMediumListWoman.remove(randomIndex);
                    } else if (!tasksExtrimeListWoman.isEmpty()) {
                        randomIndex = random.nextInt(tasksExtrimeListWoman.size());
                        task = tasksExtrimeListWoman.get(randomIndex);
                        ResultRoulette.setText(task);
                        tasksExtrimeListWoman.remove(randomIndex);
                    }
                    else {
                        ResultRoulette.setText("Все задания выполнены!");
                    }
                }

            }
        });
    }
    public class CounterExample {
        public int incrementCounter() {
            // Увеличиваем значение счетчика на 1
            counter++;
            // Возвращаем значение счетчика
            return counter;
        }
    }
}