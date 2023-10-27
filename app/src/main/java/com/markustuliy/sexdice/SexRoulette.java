package com.markustuliy.sexdice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    private ArrayList<String> tasksLiteListMale = new ArrayList<>();
    private ArrayList<String> tasksLiteListWoman = new ArrayList<>();
    private ArrayList<String> tasksMediumListMale = new ArrayList<>();
    private ArrayList<String> tasksMediumListWoman = new ArrayList<>();
    private ArrayList<String> tasksExtremeListMale = new ArrayList<>();
    private ArrayList<String> tasksExtremeListWoman = new ArrayList<>();
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
    private int deegre = 0;
    private int old_deegre = 0;
    private static final float FACTOR = 4.83f;


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
        ImageView imageView = findViewById(R.id.imageView);
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
            tasksExtremeListMale = savedInstanceState.getStringArrayList(KEY_TASKS_EXTRIME_LITE_MALE);
            tasksExtremeListWoman = savedInstanceState.getStringArrayList(KEY_TASKS_EXTRIME_LITE_WOMAN);
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
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 1: ...");
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 2: ...");
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 3: ...");
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 4: ...");
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 5: ...");
            tasksExtremeListMale.add("Тяжелое Задание Мужчине 6: ...");
            // Экстрим задания Женщине
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 1: ...");
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 2: ...");
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 3: ...");
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 4: ...");
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 5: ...");
            tasksExtremeListWoman.add("Тяжелое Задание Женщине 6: ...");
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

                old_deegre = deegre % 360;
                deegre = random.nextInt(3600 ) + 720;
                RotateAnimation rotate = new RotateAnimation(old_deegre, deegre,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
                rotate.setDuration(3600);
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
                        if (isMale & counterLite < 4){
                            if (!tasksLiteListMale.isEmpty()) {
                                counterLite = exampleLite.incrementCounter();
                                randomIndex = random.nextInt(tasksLiteListMale.size());
                                task = tasksLiteListMale.get(randomIndex);
                                ResultRoulette.setText(task);
                                tasksLiteListMale.remove(randomIndex);
                            } else if (!tasksMediumListMale.isEmpty() & counterMedium < 4){
                                randomIndex = random.nextInt(tasksMediumListMale.size());
                                task = tasksMediumListMale.get(randomIndex);
                                ResultRoulette.setText(task);
                                tasksMediumListMale.remove(randomIndex);
                                counterMedium = exampleMedium.incrementCounter();
                            } else if (!tasksExtremeListMale.isEmpty() & counterExtreme < 4) {
                                randomIndex = random.nextInt(tasksExtremeListMale.size());
                                task = tasksExtremeListMale.get(randomIndex);
                                ResultRoulette.setText(task);
                                tasksExtremeListMale.remove(randomIndex);
                                counterExtreme = exampleExtreme.incrementCounter();
                            } else {
                                ResultRoulette.setText("Все задания выполнены!");
                            }
                        } else {
                            if (!tasksLiteListWoman.isEmpty() & counterLite < 4) {
                                randomIndex = random.nextInt(tasksLiteListWoman.size());
                                task = tasksLiteListWoman.get(randomIndex);
                                ResultRoulette.setText(task);
                                tasksLiteListWoman.remove(randomIndex);
                                counterLite = exampleLite.incrementCounter();
                            } else if (!tasksMediumListWoman.isEmpty() & counterMedium < 4){
                                randomIndex = random.nextInt(tasksMediumListWoman.size());
                                task = tasksMediumListWoman.get(randomIndex);
                                ResultRoulette.setText(task);
                                tasksMediumListWoman.remove(randomIndex);
                                counterMedium = exampleMedium.incrementCounter();
                            } else if (!tasksExtremeListWoman.isEmpty() & counterExtreme < 4) {
                                randomIndex = random.nextInt(tasksExtremeListWoman.size());
                                task = tasksExtremeListWoman.get(randomIndex);
                                ResultRoulette.setText(task);
                                tasksExtremeListWoman.remove(randomIndex);
                                counterExtreme = exampleExtreme.incrementCounter();
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
}