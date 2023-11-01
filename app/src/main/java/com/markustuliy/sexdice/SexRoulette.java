package com.markustuliy.sexdice;

import static com.markustuliy.sexdice.SettingsActivity.APP_PREFERENCES;
import static com.markustuliy.sexdice.SettingsActivity.APP_SETTINGS_ANAL;
import static com.markustuliy.sexdice.SettingsActivity.APP_SETTINGS_GREEN;
import static com.markustuliy.sexdice.SettingsActivity.APP_SETTINGS_ORAL;
import static com.markustuliy.sexdice.SettingsActivity.APP_SETTINGS_ORANGE;
import static com.markustuliy.sexdice.SettingsActivity.APP_SETTINGS_RED;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class SexRoulette extends AppCompatActivity {
    private final Random random = new Random();
    private ArrayList<String> tasksVeryLiteListMale = new ArrayList<>();
    private ArrayList<String> tasksVeryLiteListWoman = new ArrayList<>();
    private ArrayList<String> tasksVeryLiteListAssignment = new ArrayList<>();
    private ArrayList<String> tasksLiteListMale = new ArrayList<>();
    private ArrayList<String> tasksLiteListWoman = new ArrayList<>();
    private ArrayList<String> tasksLiteListAssignment = new ArrayList<>();
    private ArrayList<String> tasksMediumListMale = new ArrayList<>();
    private ArrayList<String> tasksMediumListWoman = new ArrayList<>();
    private ArrayList<String> tasksMediumListAssignment = new ArrayList<>();
    private ArrayList<String> tasksExtremeListMale = new ArrayList<>();
    private ArrayList<String> tasksExtremeListWoman = new ArrayList<>();
    private ArrayList<String> tasksExtremeListAssignment = new ArrayList<>();
    private TextView ResultRoulette;
    private ImageButton rollButton;
    private TextView textName;
    private String Name1;
    private String Name2;
    private boolean isEvent1 = true;
    private boolean isMale;
    private int counterL = 0;
    private int counterM = 0;
    private int counterE = 0;
    private int counterLite = 0;
    private int counterMedium = 0;
    private int counterExtreme = 0;
    private int degree = 0;
    private int old_degree = 0;
    private static final float FACTOR = 4.83f;
    private int coin = 1;
    private String[] numbers = {"120","45","60","20", "80","30","90","50"};


    private static final String KEY_TASKS_LIST_VERY_LITE_MALE = "tasksVeryLiteListMale";
    private static final String KEY_TASKS_LIST_VERY_LITE_WOMAN = "tasksVeryLiteListWoman";
    private static final String KEY_TASKS_LIST_VERY_LITE_ASSIGMENT = "tasksVeryLiteListAssignment";
    private static final String KEY_TASKS_LIST_LITE_MALE = "tasksLiteListMale";
    private static final String KEY_TASKS_LIST_LITE_WOMAN = "tasksLiteListWoman";
    private static final String KEY_TASKS_LIST_LITE_ASSIGMENT = "tasksLiteListAssignment";
    private static final String KEY_TASKS_LIST_MEDIUM_MALE = "tasksMediumListMale";
    private static final String KEY_TASKS_LIST_MEDIUM_WOMAN = "tasksMediumListWoman";
    private static final String KEY_TASKS_LIST_MEDIUM_ASSIGMENT = "tasksMediumListAssignment";
    private static final String KEY_TASKS_LIST_EXTRIME_MALE = "tasksExtrimeListMale";
    private static final String KEY_TASKS_LIST_EXTRIME_WOMAN = "tasksExtrimeListWoman";
    private static final String KEY_TASKS_LIST_EXTRIME_ASSIGMENT = "tasksExtrimeListAssignment";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_roulette);
        rollButton = findViewById(R.id.rouletteButton);
        ResultRoulette = findViewById(R.id.ResultRoulette);
        textName = findViewById(R.id.textname);
        ImageView imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        SharedPreferences mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        int VeryLiteSettingsCounter = mSettings.getInt(APP_SETTINGS_GREEN, 2)/2;
        int LiteSettingsCounter = mSettings.getInt(APP_SETTINGS_GREEN, 2);
        int MediumSettingsCounter = mSettings.getInt(APP_SETTINGS_ORANGE, 2);
        int ExtremeSettingsCounter = mSettings.getInt(APP_SETTINGS_RED, 2);
        boolean TasksOral = mSettings.getBoolean(APP_SETTINGS_ORAL, false);
        boolean TasksAnal = mSettings.getBoolean(APP_SETTINGS_ANAL, false);

        if (intent != null && intent.hasExtra("Name1received")) {
            Name1 = intent.getStringExtra("Name1received");
        }
        if (intent != null && intent.hasExtra("Name2received")) {
            Name2 = intent.getStringExtra("Name2received");
        }
        if (savedInstanceState != null) {
            tasksVeryLiteListMale = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_VERY_LITE_MALE);
            tasksVeryLiteListWoman = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_VERY_LITE_WOMAN);
            tasksVeryLiteListAssignment = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_VERY_LITE_ASSIGMENT);
            tasksLiteListMale = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_LITE_MALE);
            tasksLiteListWoman = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_LITE_WOMAN);
            tasksLiteListAssignment = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_LITE_ASSIGMENT);
            tasksMediumListMale = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_MEDIUM_MALE);
            tasksMediumListWoman = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_MEDIUM_WOMAN);
            tasksMediumListAssignment = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_MEDIUM_ASSIGMENT);
            tasksExtremeListMale = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_EXTRIME_MALE);
            tasksExtremeListWoman = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_EXTRIME_WOMAN);
            tasksExtremeListAssignment = savedInstanceState.getStringArrayList(KEY_TASKS_LIST_EXTRIME_ASSIGMENT);
            isMale = savedInstanceState.getBoolean("isMale");
        } else {
            // Сверх Легкие задания Мужчине
            tasksVeryLiteListMale.add("Сверх Легкое Задание Мужчине 1: ...");
            tasksVeryLiteListMale.add("Сверх Легкое Задание Мужчине 2: ...");
            tasksVeryLiteListMale.add("Сверх Легкое Задание Мужчине 3: ...");
            tasksVeryLiteListMale.add("Сверх Легкое Задание Мужчине 4: ...");
            tasksVeryLiteListMale.add("Сверх Легкое Задание Мужчине 5: ...");
            tasksVeryLiteListMale.add("Сверх Легкое Задание Мужчине 6: ...");
            if (TasksOral){
                tasksVeryLiteListMale.add("Сверх Легкое оральное Задание Мужчине 1: ...");
                tasksVeryLiteListMale.add("Сверх Легкое оральное Задание Мужчине 2: ...");
                tasksVeryLiteListMale.add("Сверх Легкое оральное Задание Мужчине 3: ...");
            }
            if (TasksAnal){
                tasksVeryLiteListMale.add("Сверх Легкое анальное Задание Мужчине 1: ...");
                tasksVeryLiteListMale.add("Сверх Легкое анальное Задание Мужчине 2: ...");
                tasksVeryLiteListMale.add("Сверх Легкое анальное Задание Мужчине 3: ...");
            }
            // Сверх Легкие задания Женщине
            tasksVeryLiteListWoman.add("Сверх Легкое Задание Женщине 1: ...");
            tasksVeryLiteListWoman.add("Сверх Легкое Задание Женщине 2: ...");
            tasksVeryLiteListWoman.add("Сверх Легкое Задание Женщине 3: ...");
            tasksVeryLiteListWoman.add("Сверх Легкое Задание Женщине 4: ...");
            tasksVeryLiteListWoman.add("Сверх Легкое Задание Женщине 5: ...");
            tasksVeryLiteListWoman.add("Сверх Легкое Задание Женщине 6: ...");
            if (TasksOral){
                tasksVeryLiteListWoman.add("Сверх Легкое оральное Задание Женщине 1: ...");
                tasksVeryLiteListWoman.add("Сверх Легкое оральное Задание Женщине 2: ...");
                tasksVeryLiteListWoman.add("Сверх Легкое оральное Задание Женщине 3: ...");
            }
            if (TasksAnal){
                tasksVeryLiteListWoman.add("Сверх Легкое анальное Задание Женщине 1: ...");
                tasksVeryLiteListWoman.add("Сверх Легкое анальное Задание Женщине 2: ...");
                tasksVeryLiteListWoman.add("Сверх Легкое анальное Задание Женщине 3: ...");
            }
            // Сверх Легкие задания ОБЩИЕ
            tasksVeryLiteListAssignment.add("Сверх Легкое Общее Задание 1: ...");
            tasksVeryLiteListAssignment.add("Сверх Легкое Общее Задание 2: ...");
            tasksVeryLiteListAssignment.add("Сверх Легкое Общее Задание 3: ...");
            tasksVeryLiteListAssignment.add("Сверх Легкое Общее Задание 4: ...");
            tasksVeryLiteListAssignment.add("Сверх Легкое Общее Задание 5: ...");
            tasksVeryLiteListAssignment.add("Сверх Легкое Общее Задание 6: ...");
            if (TasksOral){
                tasksVeryLiteListAssignment.add("Сверх Легкое оральное Общее Задание 1: ...");
                tasksVeryLiteListAssignment.add("Сверх Легкое оральное Общее Задание 2: ...");
                tasksVeryLiteListAssignment.add("Сверх Легкое оральное Общее Задание 3: ...");
            }
            if (TasksAnal){
                tasksVeryLiteListAssignment.add("Сверх Легкое анальное Общее Задание 1: ...");
                tasksVeryLiteListAssignment.add("Сверх Легкое анальное Общее Задание 2: ...");
                tasksVeryLiteListAssignment.add("Сверх Легкое анальное Общее Задание 3: ...");
            }
            // Легкие задания Мужчине
            tasksLiteListMale.add("Легкое Задание Мужчине 1: ...");
            tasksLiteListMale.add("Легкое Задание Мужчине 2: ...");
            tasksLiteListMale.add("Легкое Задание Мужчине 3: ...");
            tasksLiteListMale.add("Легкое Задание Мужчине 4: ...");
            tasksLiteListMale.add("Легкое Задание Мужчине 5: ...");
            tasksLiteListMale.add("Легкое Задание Мужчине 6: ...");
            if (TasksOral){
                tasksLiteListMale.add("Легкое оральное Задание Мужчине 1: ...");
                tasksLiteListMale.add("Легкое оральное Задание Мужчине 2: ...");
                tasksLiteListMale.add("Легкое оральное Задание Мужчине 3: ...");
            }
            if (TasksAnal){
                tasksLiteListMale.add("Легкое анальное Задание Мужчине 1: ...");
                tasksLiteListMale.add("Легкое анальное Задание Мужчине 2: ...");
                tasksLiteListMale.add("Легкое анальное Задание Мужчине 3: ...");
            }
            // Легкие задания Женщине
            tasksLiteListWoman.add("Легкое Задание Женщине 1: ...");
            tasksLiteListWoman.add("Легкое Задание Женщине 2: ...");
            tasksLiteListWoman.add("Легкое Задание Женщине 3: ...");
            tasksLiteListWoman.add("Легкое Задание Женщине 4: ...");
            tasksLiteListWoman.add("Легкое Задание Женщине 5: ...");
            tasksLiteListWoman.add("Легкое Задание Женщине 6: ...");
            if (TasksOral){
                tasksLiteListWoman.add("Легкое оральное Задание Женщине 1: ...");
                tasksLiteListWoman.add("Легкое оральное Задание Женщине 2: ...");
                tasksLiteListWoman.add("Легкое оральное Задание Женщине 3: ...");
            }
            if (TasksAnal){
                tasksLiteListWoman.add("Легкое анальное Задание Женщине 1: ...");
                tasksLiteListWoman.add("Легкое анальное Задание Женщине 2: ...");
                tasksLiteListWoman.add("Легкое анальное Задание Женщине 3: ...");
            }
            // Легкие Общие задания
            tasksLiteListAssignment.add("Легкое Общее Задание 1: ...");
            tasksLiteListAssignment.add("Легкое Общее Задание 2: ...");
            tasksLiteListAssignment.add("Легкое Общее Задание 3: ...");
            tasksLiteListAssignment.add("Легкое Общее Задание 4: ...");
            tasksLiteListAssignment.add("Легкое Общее Задание 5: ...");
            tasksLiteListAssignment.add("Легкое Общее Задание 6: ...");
            if (TasksOral){
                tasksLiteListAssignment.add("Легкое оральное Общее Задание 1: ...");
                tasksLiteListAssignment.add("Легкое оральное Общее Задание 2: ...");
                tasksLiteListAssignment.add("Легкое оральное Общее Задание 3: ...");
            }
            if (TasksAnal){
                tasksLiteListAssignment.add("Легкое анальное Общее Задание 1: ...");
                tasksLiteListAssignment.add("Легкое анальное Общее Задание 2: ...");
                tasksLiteListAssignment.add("Легкое анальное Общее Задание 3: ...");
            }

            // Средние задания Мужчине
            tasksMediumListMale.add("Среднее Задание Мужчине 1: ...");
            tasksMediumListMale.add("Среднее Задание Мужчине 2: ...");
            tasksMediumListMale.add("Среднее Задание Мужчине 3: ...");
            tasksMediumListMale.add("Среднее Задание Мужчине 4: ...");
            tasksMediumListMale.add("Среднее Задание Мужчине 5: ...");
            tasksMediumListMale.add("Среднее Задание Мужчине 6: ...");
            if (TasksOral){
                tasksMediumListMale.add("Среднее оральное Задание Мужчине 1: ...");
                tasksMediumListMale.add("Среднее оральное Задание Мужчине 2: ...");
                tasksMediumListMale.add("Среднее оральное Задание Мужчине 3: ...");
            }
            if (TasksAnal){
                tasksMediumListMale.add("Среднее анальное Задание Мужчине 1: ...");
                tasksMediumListMale.add("Среднее анальное Задание Мужчине 2: ...");
                tasksMediumListMale.add("Среднее анальное Задание Мужчине 3: ...");
            }
            // Средние задания Женщине
            tasksMediumListWoman.add("Среднее Задание Женщине 1: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 2: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 3: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 4: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 5: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 6: ...");
            if (TasksOral){
                tasksMediumListWoman.add("Среднее оральное Задание Женщине 1: ...");
                tasksMediumListWoman.add("Среднее оральное Задание Женщине 2: ...");
                tasksMediumListWoman.add("Среднее оральное Задание Женщине 3: ...");
            }
            if (TasksAnal){
                tasksMediumListWoman.add("Среднее анальное Задание Женщине 1: ...");
                tasksMediumListWoman.add("Среднее анальное Задание Женщине 2: ...");
                tasksMediumListWoman.add("Среднее анальное Задание Женщине 3: ...");
            }
            // Средние задания Общие
            tasksMediumListAssignment.add("Среднее Общее Задание 1: ...");
            tasksMediumListAssignment.add("Среднее Общее Задание 2: ...");
            tasksMediumListAssignment.add("Среднее Общее Задание 3: ...");
            tasksMediumListAssignment.add("Среднее Общее Задание 4: ...");
            tasksMediumListAssignment.add("Среднее Общее Задание 5: ...");
            tasksMediumListAssignment.add("Среднее Общее Задание 6: ...");
            if (TasksOral){
                tasksMediumListAssignment.add("Среднее оральное Общее Задание 1: ...");
                tasksMediumListAssignment.add("Среднее оральное Общее Задание 2: ...");
                tasksMediumListAssignment.add("Среднее оральное Общее Задание 3: ...");
            }
            if (TasksAnal){
                tasksMediumListAssignment.add("Среднее анальное Общее Задание 1: ...");
                tasksMediumListAssignment.add("Среднее анальное Общее Задание 2: ...");
                tasksMediumListAssignment.add("Среднее анальное Общее Задание 3: ...");
            }

            // Экстрим задания Мужчине
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 1: ...");
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 2: ...");
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 3: ...");
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 4: ...");
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 5: ...");
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 6: ...");
            if (TasksOral){
                tasksExtremeListMale.add("Тяжелое оральное Задание Мужчине 1: ...");
                tasksExtremeListMale.add("Тяжелое оральное Задание Мужчине 2: ...");
                tasksExtremeListMale.add("Тяжелое оральное Задание Мужчине 3: ...");
            }
            if (TasksAnal){
                tasksExtremeListMale.add("Тяжелое анальное Задание Мужчине 1: ...");
                tasksExtremeListMale.add("Тяжелое анальное Задание Мужчине 2: ...");
                tasksExtremeListMale.add("Тяжелое анальное Задание Мужчине 3: ...");
            }
            // Экстрим задания Женщине
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 1: ...");
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 2: ...");
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 3: ...");
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 4: ...");
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 5: ...");
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 6: ...");
            if (TasksOral){
                tasksExtremeListWoman.add("Тяжелое оральное Задание Женщине 1: ...");
                tasksExtremeListWoman.add("Тяжелое оральное Задание Женщине 2: ...");
                tasksExtremeListWoman.add("Тяжелое оральное Задание Женщине 3: ...");
            }
            if (TasksAnal){
                tasksExtremeListWoman.add("Тяжелое анальное Задание Женщине 1: ...");
                tasksExtremeListWoman.add("Тяжелое анальное Задание Женщине 2: ...");
                tasksExtremeListWoman.add("Тяжелое анальное Задание Женщине 3: ...");
            }
            // Экстрим задание Общее
            tasksExtremeListAssignment.add("Тяжелое Общее Задание 1: ...");
            tasksExtremeListAssignment.add("Тяжелое Общее Задание 2: ...");
            tasksExtremeListAssignment.add("Тяжелое Общее Задание 3: ...");
            tasksExtremeListAssignment.add("Тяжелое Общее Задание 4: ...");
            tasksExtremeListAssignment.add("Тяжелое Общее Задание 5: ...");
            tasksExtremeListAssignment.add("Тяжелое Общее Задание 6: ...");
            if (TasksOral){
                tasksExtremeListAssignment.add("Тяжелое оральное Общее Задание 1: ...");
                tasksExtremeListAssignment.add("Тяжелое оральное Общее Задание 2: ...");
                tasksExtremeListAssignment.add("Тяжелое оральное Общее Задание 3: ...");
            }
            if (TasksAnal){
                tasksExtremeListAssignment.add("Тяжелое анальное Общее Задание 1: ...");
                tasksExtremeListAssignment.add("Тяжелое анальное Общее Задание 2: ...");
                tasksExtremeListAssignment.add("Тяжелое анальное Общее Задание 3: ...");
            }
        }
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                old_degree = degree % 360;
                degree = random.nextInt(360 ) + 180;
                RotateAnimation rotate = new RotateAnimation(old_degree, degree,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
                rotate.setDuration(1800);
                rotate.setFillAfter(true);
                rotate.setInterpolator(new DecelerateInterpolator());


                rotate.setAnimationListener(new Animation.AnimationListener() {
                    int randomIndex;
                    String task;
                    CounterLiteExample exampleLite = new CounterLiteExample();
                    CounterMediumExample exampleMedium = new CounterMediumExample();
                    CounterExtremeExample exampleExtreme = new CounterExtremeExample();
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (isEvent1) {
                            textName.setText(Name1 + getResult(360 - (degree % 360)));
                        } else {
                            textName.setText(Name2 + getResult(360 - (degree % 360)));
                        }
                        isEvent1 = !isEvent1;
                        isMale = !isMale;
                        coin = random.nextInt(2);
                        if (isMale){
                            if (!tasksVeryLiteListMale.isEmpty() & counterLite < VeryLiteSettingsCounter) {
                                if (coin == 1 & !tasksVeryLiteListAssignment.isEmpty()){
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksVeryLiteListAssignment.size());
                                    task = tasksVeryLiteListAssignment.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksVeryLiteListAssignment.remove(randomIndex);
                                    } else {
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksVeryLiteListMale.size());
                                    task = tasksVeryLiteListMale.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksVeryLiteListMale.remove(randomIndex);
                                    }
                            } else if(!tasksLiteListMale.isEmpty() & counterLite < LiteSettingsCounter){
                                if (coin == 1 & !tasksLiteListAssignment.isEmpty()){
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksLiteListAssignment.size());
                                    task = tasksLiteListAssignment.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksLiteListAssignment.remove(randomIndex);
                                } else {
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksLiteListMale.size());
                                    task = tasksLiteListMale.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksLiteListMale.remove(randomIndex);
                                }
                            }
                            else if (!tasksMediumListMale.isEmpty() & counterMedium < MediumSettingsCounter){
                                if (coin == 1 & !tasksMediumListAssignment.isEmpty()){
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksMediumListAssignment.size());
                                    task = tasksMediumListAssignment.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksMediumListAssignment.remove(randomIndex);
                                } else {
                                    randomIndex = random.nextInt(tasksMediumListMale.size());
                                    task = tasksMediumListMale.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksMediumListMale.remove(randomIndex);
                                    counterMedium = exampleMedium.incrementCounter();
                                }
                            } else if (!tasksExtremeListMale.isEmpty() & counterExtreme < ExtremeSettingsCounter) {
                                if (coin == 1 & !tasksExtremeListAssignment.isEmpty()){
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksExtremeListAssignment.size());
                                    task = tasksExtremeListAssignment.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksExtremeListAssignment.remove(randomIndex);
                                } else {
                                    randomIndex = random.nextInt(tasksExtremeListMale.size());
                                    task = tasksExtremeListMale.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksExtremeListMale.remove(randomIndex);
                                    counterExtreme = exampleExtreme.incrementCounter();
                                }
                            } else {
                                ResultRoulette.setText("Все задания выполнены!");
                            }
                        } else {
                            if (!tasksVeryLiteListWoman.isEmpty() & counterLite < VeryLiteSettingsCounter) {
                                if (coin == 1 & !tasksVeryLiteListAssignment.isEmpty()){
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksVeryLiteListAssignment.size());
                                    task = tasksVeryLiteListAssignment.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksVeryLiteListAssignment.remove(randomIndex);
                                } else {
                                    randomIndex = random.nextInt(tasksVeryLiteListWoman.size());
                                    task = tasksVeryLiteListWoman.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksVeryLiteListWoman.remove(randomIndex);
                                    counterLite = exampleLite.incrementCounter();
                                }
                            } else if (!tasksLiteListWoman.isEmpty() & counterLite < LiteSettingsCounter) {
                                if (coin == 1 & !tasksLiteListAssignment.isEmpty()){
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksLiteListAssignment.size());
                                    task = tasksLiteListAssignment.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksLiteListAssignment.remove(randomIndex);
                                } else {
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksLiteListWoman.size());
                                    task = tasksLiteListWoman.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksLiteListWoman.remove(randomIndex);
                                }
                            }
                            else if (!tasksMediumListWoman.isEmpty() & counterMedium < MediumSettingsCounter){
                                if (coin == 1 & !tasksMediumListAssignment.isEmpty()){
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksMediumListAssignment.size());
                                    task = tasksMediumListAssignment.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksMediumListAssignment.remove(randomIndex);
                                } else {
                                    randomIndex = random.nextInt(tasksMediumListWoman.size());
                                    task = tasksMediumListWoman.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksMediumListWoman.remove(randomIndex);
                                    counterMedium = exampleMedium.incrementCounter();
                                }
                            } else if (!tasksExtremeListWoman.isEmpty() & counterExtreme < ExtremeSettingsCounter) {
                                if (coin == 1 & !tasksExtremeListAssignment.isEmpty()){
                                    counterLite = exampleLite.incrementCounter();
                                    randomIndex = random.nextInt(tasksExtremeListAssignment.size());
                                    task = tasksExtremeListAssignment.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksExtremeListAssignment.remove(randomIndex);
                                } else {
                                    randomIndex = random.nextInt(tasksExtremeListWoman.size());
                                    task = tasksExtremeListWoman.get(randomIndex);
                                    ResultRoulette.setText(task);
                                    tasksExtremeListWoman.remove(randomIndex);
                                    counterExtreme = exampleExtreme.incrementCounter();
                                }
                            }
                            else {
                                ResultRoulette.setText("Все задания выполнены!");
                            }
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                });
                imageView.startAnimation(rotate);
            }
        });
    }
    public class CounterLiteExample {
        public int incrementCounter() {
            // Увеличиваем значение счетчика на 1
            counterL++;
            // Возвращаем значение счетчика
            return counterL;
        }
    }
    public class CounterMediumExample {
        public int incrementCounter() {
            counterM++;
            return counterM;
        }
    }
    public class CounterExtremeExample {
        public int incrementCounter() {
            counterE++;
            return counterE;
        }
    }
    private String getResult(int degree)
    {
        String text = "";

        int factor_x = 1;
        int factor_y = 3;
        for(int i = 0;i < 8; i++){
            if(degree >= (FACTOR * factor_x) && degree < (FACTOR * factor_y))
            {
                text = numbers[i];
            }
            factor_x += 2;
            factor_y += 2;
        }
        if(degree >= (FACTOR * 16) && degree < 360 || degree >= 0 && degree < (FACTOR * 1))
            text = numbers[numbers.length - 1];

        return text;
    }
}