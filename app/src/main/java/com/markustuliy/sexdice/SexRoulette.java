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
    private TextView textTimer;
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
    private static final float FACTOR = 22.5f;
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
        textTimer = findViewById(R.id.TimerRoulette);
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
            tasksVeryLiteListMale.add("Подойди к девушке, обними её за талию и прижми к себе. Погладь её нежно от шеи к груди");
            tasksVeryLiteListMale.add("Нежно целуй ее шею, лаская руками тело партнера.");
            tasksVeryLiteListMale.add("Нежно пощекочи девушку");
            tasksVeryLiteListMale.add("Измерь грудь девушки любым предметом, если ничего нет, используй для этого руки");
            tasksVeryLiteListMale.add("Сейчас ты должен оголить свой торс. Девушка должна осторожно его трогать");
            tasksVeryLiteListMale.add("Измерь бедра партрнера любыми подручными средствами, если их нет, используй для измерения руки");
            tasksVeryLiteListMale.add("Сделай 10 отжиманий над партнером");
            tasksVeryLiteListMale.add("Приобними девушку за шею и нежно поцелуй.");
            tasksVeryLiteListMale.add("Вы должны быть в одежде. Просунь руку между ее ножек и потрогай попку, в это время она будет щупать твой член через одежду");
            tasksVeryLiteListMale.add("Джентельмен! Поцелуй руку даме. Смотри ей в глаза.");
            tasksVeryLiteListMale.add("Целуй руку девушке пока идет таймер.");
            tasksVeryLiteListMale.add("Завяжи девушке глаза на следующие 4 хода.");
            tasksVeryLiteListMale.add("Помоги девушке сесть на стол. Нежно помассируй, поглаживая ее ножки, выше колен.");
            tasksVeryLiteListMale.add("Целуй, ласкай губами и языком её шею.");
            tasksVeryLiteListMale.add("Девушка должна открыть ротик и высунуть язык, после этого ты языком дотрагиваешься до ее нижней губы и она должна \"погладить\" твой язык своим. Далее следует страстный поцелуй.");
            tasksVeryLiteListMale.add("Засунь руки ей под одежду и потрогай её попку");
            tasksVeryLiteListMale.add("Поцелуй сосок партнера. Девушка, не стесняйся, приподними одежду");
            tasksVeryLiteListMale.add("Схвати одной рукой ее за попку, а после этого твоя рука должна \"соскользнуть\" между ее булочек к киске.");
            tasksVeryLiteListMale.add("Пока она ещё в одежде, возьми вибратор, если вибратора нет, используй свою руку, и прижми его к её киске. Интересно, какие будут у неё ощущения через одежду?");
            tasksVeryLiteListMale.add("Начни страстно целовать девушку, можешь трогать ее грудь, бедра, попку.");
            tasksVeryLiteListMale.add("Массируй её бедра и попку в течение минуты");
            tasksVeryLiteListMale.add("Стоя раздень ее попку до трусиков, после этого немного помни.");
            tasksVeryLiteListMale.add("Девушка садится на кровать и широко раздвигает ножки. Мужчина в течение 30 секунд гладит её бёдра и ласкает \"зону бикини\". Киску трогать нельзя!");
            tasksVeryLiteListMale.add("Целуй, ласкай губами и языком её шею.");
            tasksVeryLiteListMale.add("Сделай партнеру возбуждающий массаж любой части тела, о которой она попросит.");
            tasksVeryLiteListMale.add("Она ляжет на бок, начни с нежных поцелуев ее шеи, после этого, проводя губами по ее телу, спустись ниже и нежно целуй ее талию и верх попки.");
            tasksVeryLiteListMale.add("Покажи девушке свой член");
            tasksVeryLiteListMale.add("Парень остается в одних трусах. Парень проводит рукой по своему прессу (или животу) .");
            tasksVeryLiteListMale.add("Встань сзади своей девушки и ласкай ее тело, можешь попробовать \"проникнуть\" в ее лифчик и потрогать за грудь. В процессе целуй ее шею и говори приятные слова.");


            if (TasksOral){
                tasksVeryLiteListMale.add("Подойди к ней со спины. Одной рукой нежно возьми за шею, а два пальца второй засунь ей в рот. Сделай 10 движений туда-назад.");
                tasksVeryLiteListMale.add("Найди предмет, похожий на женские половые органы, и покажи, как бы ты ласкал их.");
                //tasksVeryLiteListMale.add("Сверх Легкое оральное Задание Мужчине 3: ...");
            }
            if (TasksAnal){
                //tasksVeryLiteListMale.add("Сверх Легкое анальное Задание Мужчине 1: ...");
                //tasksVeryLiteListMale.add("Сверх Легкое анальное Задание Мужчине 2: ...");
                //tasksVeryLiteListMale.add("Сверх Легкое анальное Задание Мужчине 3: ...");
            }
            // Сверх Легкие задания Женщине
            tasksVeryLiteListWoman.add("Сядь напротив него, повернувшись к нему лицом. Положи ногу на ногу, сексуально смотря ему в глаза");
            tasksVeryLiteListWoman.add("Пройдись по комнате как королева красоты по подиумуму. Не забывай вилять бёдрами.");
            tasksVeryLiteListWoman.add("Если на тебе надеты шортики или юбочка, то ты должна их сейчас эротично снять с себя. Если шортиков нет и на тебе любая другая одежда, то просто пропускаешь это задание.");
            tasksVeryLiteListWoman.add("Покажи свою лучшую походку. Сделай три круга по комнате, сексуально виляя бедрами.");
            tasksVeryLiteListWoman.add("Если на дворе по календарю сейчас зима, то девушка снимает с себя 1 предмет одежды. Если весна, то девушка снимает с себя 2 предмета одежды. Если сейчас лето, то парень снимает с себя 2 предмета одежды и если осень то парень снимает с себя 1 предмет одежды.");
            tasksVeryLiteListWoman.add("Надень каблучки и не сгибая колен упрись руками на стул. Делай прогиб и выгиб поясницы. Накачаете пресс или нет не так важно, главное чтобы парень смотрел и наслаждался процессом.");
            tasksVeryLiteListWoman.add("Нежно массируй ему спину, действуя интенсивнее в районе крестца.");
            tasksVeryLiteListWoman.add("Подвигай попкой перед ним. Можешь станцевать тверк или медленный эротический танец. Возбуди его.");
            tasksVeryLiteListWoman.add("Тебе предстоит надеть своё самое красивое бельё и чулки. Мужчина должен выйти из комнаты, чтобы для него был небольшой сюрприз.");
            tasksVeryLiteListWoman.add("Сядьте парню на коленки до своего следующего фанта");
            tasksVeryLiteListWoman.add("Эротично сними платье если оно на тебе есть. Если нет - просто пропускай задание.");
            tasksVeryLiteListWoman.add("Покажи своему мужчине, как ты ласкаешь себя. Если ты ещё в белье, можешь не снимать его.");
            tasksVeryLiteListWoman.add("Посасывая пальчик и эротично глядя в глаза парню другой рукой водите через одежду по своей промежности.");
            tasksVeryLiteListWoman.add("Сядь попкой ему на колени и медленно шевели тазом, он должен гладить твои бедра.");
            tasksVeryLiteListWoman.add("Прикоснись к нему грудью. Куда пожелаешь.");
            tasksVeryLiteListWoman.add("Мужчина сидит на кресле. Ты должна подойти к нему и начать целовать в губы. Всё это время он может трогать и гладить тебя за любые места.");
            tasksVeryLiteListWoman.add("Медленно и эротично в течение минуты гладь себя между ног так, чтобы он получал удовольствие от созерцания.");
            tasksVeryLiteListWoman.add("Во время чтения следующих заданий ты должна принимать сексуальные позы, можешь оголять разные части тела, гладить себя, приспускать трусики и засовывать туда шальные пальчики. Тебе нужно возбуждать своего мужчину.");
            tasksVeryLiteListWoman.add("Завяжи друг другу глаза и поцелуйтесь. Далее сними с себя повязку, а парень еще 5 ходов играет с завязанными глазами.");
            tasksVeryLiteListWoman.add("Сядь к нему на колени в одних трусиках и потрись киской об него через одежду");
            tasksVeryLiteListWoman.add("Сядь спиной к нему в трусиках на колени и потрись попкой об его член");
            tasksVeryLiteListWoman.add("Ляг кверху попкой, он в течение 1 минуты будет массировать её.");
            tasksVeryLiteListWoman.add("Нежно кусай и целуй его за ушко.");
            tasksVeryLiteListWoman.add("Станцуй для него эротический танец. Он не должен дотрагиваться до тебя в это время.");
            tasksVeryLiteListWoman.add("Потрогай парня за член через брюки или джинсы");
            tasksVeryLiteListWoman.add("Подними кофточку и дай ему насладится твоей грудью. Можешь потрогать себя за сосочки, чтобы они затвердели, но ему трогать тебя запрещено.");
            tasksVeryLiteListWoman.add("Расстегни его рубашку и оближи его соски, можешь нежно их покусать, но не делай ему больно.");
            tasksVeryLiteListWoman.add("Ляг кверху попкой, он в течение 1 минуты будет массировать её.");
            tasksVeryLiteListWoman.add("Ласкай свою грудь руками в течении минуты. если мешает одежда, то ее нужно снять");





            if (TasksOral){
                tasksVeryLiteListWoman.add("Возьми бутылку и соси ее, словно член партнера.");
                tasksVeryLiteListWoman.add("Найди предмет, похожий на мужской член, и сымитируй, как бы ты ласкала его.");
                tasksVeryLiteListWoman.add("Возьми очищенный банан и покажи, как на нем, как ты умеешь делать минет. Поиграйся с ним язычком, возьми глубоко в рот. В конце задания можешь эротичного его съесть и поделится со своим молодым человеком.");
            }
            if (TasksAnal){
                // tasksVeryLiteListWoman.add("Сверх Легкое анальное Задание Женщине 1: ...");
                //tasksVeryLiteListWoman.add("Сверх Легкое анальное Задание Женщине 2: ...");
                //tasksVeryLiteListWoman.add("Сверх Легкое анальное Задание Женщине 3: ...");
            }
            // Сверх Легкие задания ОБЩИЕ
            tasksVeryLiteListAssignment.add("Включи медленную и романтичную музыку");
            tasksVeryLiteListAssignment.add("Целуй партнера в губы пока не сработает таймер, в процессе можете ласкать руками шею и волосы.");
            tasksVeryLiteListAssignment.add("Сделай партнеру возбуждающий массаж любой части тела, о которой она/он попросит.");
            tasksVeryLiteListAssignment.add("Поцелуй партнера в носик.");
            tasksVeryLiteListAssignment.add("Тебе нужно посмотреть партнеру прямо в глаза и не отрывать взгляд пока идёт таймер");
            tasksVeryLiteListAssignment.add("Устройте на кровати \"Батут\" вдоволь вдвоем попрыгав. Выполняйте задание только если уверены что не будет печальных последствий ни для вас, ни для кровати");
            tasksVeryLiteListAssignment.add("Партнер пишет на листке какое-нибудь действие, которое он/она хочет, чтобы ты выполнил/а (например, помять плечи или поцеловать в шею). Методом проб и ошибок попробуй своими действиями отгадать, что написано на листке. У тебя пять попыток.");
            tasksVeryLiteListAssignment.add("Покрой поцелуями нижнюю часть живота партнера, при этом не целуя ниже");
            tasksVeryLiteListAssignment.add("Медленно и страстно сними свой верх");
            tasksVeryLiteListAssignment.add("Поцелуй страстно партнера и прижми его к стене");
            tasksVeryLiteListAssignment.add("Если ты знаешь любимый фильм партнера, озвучь его и сними с него штаны, иначе свои");
            tasksVeryLiteListAssignment.add("самый сильный из вас двоих должен схватить другого на 2 минуты и поцеловать у стены");
            tasksVeryLiteListAssignment.add("Первый кто найдет предмет цветом похожий на цвета приложения, может делать с ним всё что захочет");
            tasksVeryLiteListAssignment.add("Пусть партнер тебя отшлепает, если тебе это нравится.");
            tasksVeryLiteListAssignment.add("Сделай нежный массаж шеи и плеч, если у Вас есть масло, то используй его.");
            tasksVeryLiteListAssignment.add("Ты целуешь партнера в 5 мест по своему выбору, партнер ответит тебе поцелуями в те же места.");
            tasksVeryLiteListAssignment.add("Целуй партнера очень страстно с языком, не прерывай поцелуй целую минуту.");
            tasksVeryLiteListAssignment.add("Повали партнера на спину и страстно поцелуй в губы.");
            tasksVeryLiteListAssignment.add("У тебя есть 1 минута, чтобы пощупать партнера там, где ты захочешь.");
            tasksVeryLiteListAssignment.add("Сделай то, о чем тебя попросит партнер.");
            tasksVeryLiteListAssignment.add("Партнер завяжет тебе глаза и медленно покроет поцелуями твое тело, прикасаясь к нему только губами. Разрешено приподнимать одежду");
            tasksVeryLiteListAssignment.add("Загадай число партнеру от 1 до 5, если он угадает, ты снимаешь с себя полностью верх, если не угадает, он оголяет верх");
            tasksVeryLiteListAssignment.add("Эротично сними с себя штаны");
            tasksVeryLiteListAssignment.add("Потрогайте интимные зоны друг друга через одежду");

            if (TasksOral){
                //tasksVeryLiteListAssignment.add("Сверх Легкое оральное Общее Задание 1: ...");
                //tasksVeryLiteListAssignment.add("Сверх Легкое оральное Общее Задание 2: ...");
                //tasksVeryLiteListAssignment.add("Сверх Легкое оральное Общее Задание 3: ...");
            }
            if (TasksAnal){
                //tasksVeryLiteListAssignment.add("Сверх Легкое анальное Общее Задание 1: ...");
                //tasksVeryLiteListAssignment.add("Сверх Легкое анальное Общее Задание 2: ...");
                //tasksVeryLiteListAssignment.add("Сверх Легкое анальное Общее Задание 3: ...");
            }
            // Легкие задания Мужчине
            tasksLiteListMale.add("Встань и приспусти трусы (если они еще на тебе). Покажи девушке свой член, будто ты Апалон");
            tasksLiteListMale.add("Она сядет к тебе на колени, повернувшись лицом и будет целовать. В это время ты должен гладить её попку и киску через трусики.");
            tasksLiteListMale.add("Партнер твой врач, она разденет тебя до трусиков и будет сжимать где считает необходимым");
            tasksLiteListMale.add("Повернитесь лицом к партнеру и осторожно сними с неё трусики не прикасаясь к телу");
            tasksLiteListMale.add("Ты садишься на кровать, девушка садится перед тобой, обнимая за шею и поставив свои ножки сзади тебя. В течение минуты вы ласкаете тела друг друга и целуетесь.");
            tasksLiteListMale.add("Наслюнявь пальчик и води им вокруг ее сосочков, периодически трогай и сжимай их, но нежно.");
            tasksLiteListMale.add("Намажь ее груди чем-нибудь сладким и вкусным, после чего слижи все.");
            tasksLiteListMale.add("С закрытыми глазами и раздвинутыми ногами у тебя есть 2 минуты чтобы доставить себе удовольствие");
            tasksLiteListMale.add("Сделай короткое видео, на котором партнер нежно и медленно ласкает себя пальцами");
            tasksLiteListMale.add("Девушка задирает юбочку или спускает штаны, чтобы её киску от тебя отделяли только трусики. Возьми ВЫКЛЮЧЕННЫЙ вибратор и в течение 30 секунд ласкай им её интимную зону. Если вибратора нет, используй руки.");
            tasksLiteListMale.add("Включи порно. Поглаживай внутреннюю часть её бедра. Когда станет невтерпёж - переходите к следующему заданию.");
            tasksLiteListMale.add("В течение выпавшего времени можешь трогать киску девушки через одежду.");
            tasksLiteListMale.add("Назови 3 эрогенные зоны партнёра. Если точно не знаешь, то можешь предположить. Пусть партнёр считает те, что угадаешь. Потом сними с партнёра столько вещей, сколько эрогенных зон ты угадаешь. Если в процессе снятия вещей с партнёра оголяется какая либо из эрогенных зон партнёра, то ты её нежно целуешь.");
            tasksLiteListMale.add("Поставь ее на четвереньки, встань сзади. После этого возьми ее волосы в руку и слегка потяни за них, второй рукой ласкай ее попку. После таймера шлепни ее и включай следующий фант.");
            tasksLiteListMale.add("Пока она ещё в трусиках, повали её на кровать и помассируй её бедра и \"зону бикини\"");
            tasksLiteListMale.add("У тебя есть время заданное таймером, чтобы пощупать её там, где ты захочешь.");
            tasksLiteListMale.add("Девушка должна лечь на животик. Начни целовать ее шею, спускайся вниз, оставляя дорожку поцелуев. Удели время поцелуям поясницы, после этого можешь целовать и покусывать ее попку через трусики.");
            tasksLiteListMale.add("Время необычных фетишей. На этот раз японский. В течение 30 секунд облизывайте языки друг другу. Можете попробовать \"захватить\" язык партнера ртом. Не бойтесь, если будут слюни.");
            tasksLiteListMale.add("Ты называешь любую букву, партнер называет число от 1 до 10, затем вы заходите на любой порно сайт, открываете категорию порно на нужную букву и включаете видео на странице под номером который назвал партнер перематываете на минуту, которую назвал парень и повторяете сцену.");
            tasksLiteListMale.add("Ты должен раздеться, если на тебе ещё осталась одежда. Далее ложись на кровать животом и делай ритмичные движения имитируя половой акт и играя булочками. После этого можешь обратно надеть, то что снял на этом задании. Задача девушки за этим всем наблюдать");
            tasksLiteListMale.add("Девушка ляжет на живот. Большими пальцами рук сделай ей массаж попки.");
            tasksLiteListMale.add("Она встанет на четвереньки, ты в течение 30 секунд можешь трогать и нежно шлепать её по попке.");
            tasksLiteListMale.add("Повали свою девушку на кровать, после чего следует время долгих и страстных поцелуев.");
            tasksLiteListMale.add("Погладь ее киску через трусики, если они влажные, Вы на правильном пути");
            tasksLiteListMale.add("Поцелуй её внизу живота. Ласкай языком её пупок 30 секунд.");
            tasksLiteListMale.add("Запусти пальчики ей в трусики и потрогай, влажная ли её киска?");
            tasksLiteListMale.add("Если она ещё в трусиках, поставь один пальчик на её клитор, а вторым погладь её киску по всей длине, чтобы они намокли");
            tasksLiteListMale.add("Запусти пальчики ей в трусики и потрогай, влажная ли её киска?");
            tasksLiteListMale.add("Проведи пальчиками от её коленки до трусиков по внутренней поверхности бедра. После этого повтори тоже самое на другой ножке.");
            tasksLiteListMale.add("Девушка в трусиках должна встать на четвереньки или нагнуться. Нежно, одним пальчиком, проведи несколько раз по ее киске.");
            tasksLiteListMale.add("Помоги девушке надеть чулки и снять трусики. Если чулков у вас нет, то их нужно сейчас же заказать например на озоне,WB или алиэкспресс. В этом случае ждете заказ и снимаешь с девушки трусики.");
            tasksLiteListMale.add("Девушка лежит со слегка раздвинутыми ножками. Мужчина берет вибратор с небольшой мощностью и через трусики прижимает его к её киске и медленно водит им. Если вибратора нет, делай руками");
            tasksLiteListMale.add("Обними ее за талию, после этого твои руки должны спуститься ниже и проникнуть ей в трусики. Ты можешь гладишь и сжимать ее попку, пока не сработает таймер!");
            tasksLiteListMale.add("Она раздвинет ножки. Потрогай её дырочку через трусики.");
            tasksLiteListMale.add("Если на тебе есть трусы, снимай их, немедленно!");
            tasksLiteListMale.add("Встань и сними трусы (если они еще на тебе). Покажи девушке свой член поближе");
            tasksLiteListMale.add("Она встанет лицом к стене, слегка раздвинув ноги, вытянутыми руками упираясь в стену. Ты играешь роль полицейского - обыщи её, проникая под одежду и исследуя самые сокровенные уголки. Во время обыска конфискуй что-то из ее одежды. Если одежды на ней уже не осталось или совсем мало то сделайте небольшой перерыв - пусть оденется немного на задание. Платье, лифчик, трусики вполне будет достаточно.");
            tasksLiteListMale.add("Возьми её за трусики и небольшими движениями тяни их вверх, чтобы они терлись об её киску. Её должно быть приятно, а не больно.");


            if (TasksOral){
                tasksLiteListMale.add("Хочешь увидеть партнера без одежды - придется поработать над этим. Ты должен раздеть ее до белья, не помогая себе руками.");
                tasksLiteListMale.add("Встань в полный рост, положи руки на голову и закрой глаза. Она спустит с тебя трусы на 1 минуту и рассмотрит твой член.");
                tasksLiteListMale.add("Девушка подставляет свою попку в трусиках для поцелуев, в течении таймера ты должен целовать ее булочки.");
                tasksLiteListMale.add("Проведи языком от её коленки до трусиков по внутренней поверхности бедра. После этого повтори тоже самое на другой ножке.");
                tasksMediumListMale.add("Девушка сядет на кровати. Начни целовать ее ножки, постепенно поднимаясь выше. Особое внимание удели ее бедрам. Если девушка еще в трусиках, то она может их слегка отодвинуть, чтобы ты мог поцеловать самое сокровенное.");
            }
            if (TasksAnal){
                //tasksLiteListMale.add("Легкое анальное Задание Мужчине 1: ...");
                //tasksLiteListMale.add("Легкое анальное Задание Мужчине 2: ...");
                //tasksLiteListMale.add("Легкое анальное Задание Мужчине 3: ...");
            }
            // Легкие задания Женщине
            tasksLiteListWoman.add("Партнер твой врач, он разденет тебя до трусиков и будет сжимать где считает необходимым");
            tasksLiteListWoman.add("С закрытыми глазами и раздвинутыми ногами у тебя есть 2 минуты чтобы доставить себе удовольствие");
            tasksLiteListWoman.add("Спусти с себя лифчик и в течение 30 секунд поиграйся со своими грудями.");
            tasksLiteListWoman.add("Прыгай вверх и вниз на месте, пока партнер делает замедленную съемку вашей груди");
            tasksLiteListWoman.add("Сядь на оконную раму и позволь партнеру сделать вам что-нибудь приятное");
            tasksLiteListWoman.add("Он сядет на край кровати, а ты - к нему на колени, максимально близко прижавшись к его лобку низом живота. Он будет в течение минуты ласкать твои соски.");
            tasksLiteListWoman.add("Ложись на него сверху и целуй. Он может ласкать твою попку и киску, если она еще в трусиках.");
            tasksLiteListWoman.add("Совершите страстный и слюнявый поцелуй. Посасывайте языки друг друга и облизывайте их, пока не сработает таймер.");
            tasksLiteListWoman.add("Если у тебя есть масло, увлажни им свои груди и покажи, как эротично ты можешь ласкать их руками. Сделай так, чтобы твои сосочки стали твёрдыми и дай их ему пососать.");
            tasksLiteListWoman.add("Встань на четвереньки и дай ему как следует рассмотреть тебя. Он может трогать и ласкать, где ему захочется.");
            tasksLiteListWoman.add("Ты полностью одеваешься. Как была в начале игры. Надень всё что успеешь пока идет таймер");
            tasksLiteListWoman.add("Сядь и раздвинь ножки, партнер встанет между них и будет ласкать твою грудь. В это время ты можешь трогать его член через трусы.");
            tasksLiteListWoman.add("Ты обмазываешь свои сосочки взбитыми сливками или чем-то сладким, после этого эротично их облизывает. Если у тебя это не получается, то за тебя это делает парень.");
            tasksLiteListWoman.add("Эротично сними с себя лифчик, если он еще на тебе, после этого он должен занять удобное положение сидя. Сядь на него верхом (лицом к лицу) и страстно целуй его в губы, одновременно лаская его плечи и грудь.");
            tasksLiteListWoman.add("Покажи ему киску, спрятанное под одеждой.");
            tasksLiteListWoman.add("Используй ноги, чтобы прикоснуться к члену партнера или коснитесь его рта, если вам это нравится");
            tasksLiteListWoman.add("Оставьте свечу зажженой на 1 минуту, затем вылейте горячий воск ему на грудь. Если нет свечки, пропусти задание");
            tasksLiteListWoman.add("Подними кофточку и потрогай себя за сосочки, чтобы они затвердели. Партнеру запрещено к тебе прикасаться.");
            tasksLiteListWoman.add("Ты провинилась. Вставай на коленки и локти - пусть он пошлепает тебя по попке рукой. Одежду не снимать.");
            tasksLiteListWoman.add("Спусти с себя штанишки и пошлепай сама свою попку.");
            tasksLiteListWoman.add("Задери кофточку или полностью ее сними, чтобы показать ему свою голую грудь.");
            tasksLiteListWoman.add("Сделай ему нежный засос на шее. Если он против, то спустись ниже и зацелуй ему низ живота.");
            tasksLiteListWoman.add("Встань на четвереньки и прижми грудь к кровати, выставив ему свою попку в трусиках для нежных ласк. В течение выпавшего времени, он может гладить и ласкать твои булочки и бедра.");
            tasksLiteListWoman.add("Девушки любят скакать на скакалке и тебе выпал такой шанс. Полностью разденься, одень обувь и попытайся раздобыть себе скакалку. Если нашла молодец - скачи. Если не нашла, то тоже не беда - скачи как будто у тебя есть скакалка. Парень наблюдает и считает до 10. Парню трогать тебя запрещается. После этого можешь обратно надеть то, что сняла на этом задании.");
            tasksLiteListWoman.add("Твой парень - фитнес инструктор и заставляет тебя делать какие-нибудь физические упражнение, при этом нагло лапает тебя за мягкие места, приговаривая, что сделает из сладкой булочки шикарную конфетку.");
            tasksLiteListWoman.add("Сделай небольшую зарядку. Принимай максимально эротичные позы, твоя задача не только размяться перед дальнейшим, но и возбудить его.");
            tasksLiteListWoman.add("Он сядет на диван, залезь на него сверху и страстно целуй его в губы, можешь с языком! Он будет ласкать твое тело и попку, пока ты выполняешь свою часть задания.");
            tasksLiteListWoman.add("Нежными движениями массируй его ноги, внутреннюю часть бедер, но не касаясь члена.");
            tasksLiteListWoman.add("Если на тебе еще остался лифчик, то немедленно снимай его, при этом не показывая сосков. Прячь соски и дальше пока не будут задания, в которых они потрубуются голенькие)");
            tasksLiteListWoman.add("Полностью разденься, надень носочки и покружись 3 раза. Парень просто наблюдает. Трогать тебя ему запрещено. После выполнения можешь обратно одеть то, что сняла на этом задании.");
            tasksLiteListWoman.add("Станцуй для него эротический танец. Он не должен дотрагиваться до тебя в это время.");
            tasksLiteListWoman.add("Встань к парню попкой и слегка раздвинь ножки. Пусть парень потрогает твою киску через трусики, если она еще сухая, он оближет два своих пальца и смочите её");
            tasksLiteListWoman.add("Покажи ему свою попку, приспусти трусики, сжимай свои булочки и потряси ими. Пусть наслаждается тобой.");
            tasksLiteListWoman.add("Если на тебе надеты джинсы , то ты должна их сейчас снять с себя. Если джинсов нет, и на тебе любая другая одежда, то просто пропускаешь это задание.");
            tasksLiteListWoman.add("Ложись на спину, сожми вместе ножки и подними их вверх. После этого приспусти трусики и покажи ему свою киску.");
            tasksLiteListWoman.add("Сними с себя всю одежду которая есть кроме трусиков, чулочков , туфлей если такие одеты и бюсгалтера. Садись на колени парню. Смотрите друг другу в глаза и гладьте тела пока идет таймер.");
            tasksLiteListWoman.add("Повернись к нему попкой и встань на колени, после этого спусти с себя трусики и наклонись, выставив свою киску ему на обозрение. В конце можешь надеть обратно или полностью снять трусики.");
            tasksLiteListWoman.add("Полностью разденься, одень чулки и постарайся выполнить следующее упражнение: сядь на пол или кровать, сведи ноги в ступнях, разводи колени максимально к полу, повтори 3 раза. Далее выпрями ноги, разводи ноги как для шпагата. Максимально сколько можешь, не переусердствуй. Повтори 3 раза. Парень просто наблюдает. Трогать тебя ему запрещено. После выполнения можешь обратно надеть то, что сняла на этом задании.");
            tasksLiteListWoman.add("Если ты в платье или юбке, сними с себя трусики и раздвинь или подними ножки так, чтобы показать мужчине свою киску.");
            tasksLiteListWoman.add("Встань на четвереньки, прогни спинку и покажи ему все свои прелести. Раздвинь попку, губки своей киски, завлекай его!");
            tasksLiteListWoman.add("Подойди к нему вплотную, оголи свою грудь и дай ему возможность целовать, сосать и облизывать её.");
            tasksLiteListWoman.add("Повернись к нему попкой, приспусти трусики и потряси своими аппетитными булочками");
            tasksLiteListWoman.add("Удобно расположись в кресле или на кровати, раздвинь ножки и дай возможность партнеру через трусики потрогать твою киску.");
            tasksMediumListWoman.add("Наступает минутка гимнастики. Полностью разденься.Теперь постарайся раздобыть обруч. Если нашла молодец - упражняйся им 1 минуту. Если не нашла, то тоже не беда - делай движения как будто у тебя есть обруч. Парень просто наблюдает. Трогать тебя ему запрещено. После зарядки можешь обратно одеть то, что сняла на этом задании.");

            if (TasksOral){
                tasksLiteListWoman.add("Встань в полный рост, закрой глаза и положи руки на голову. Он на минуту спустит с тебя трусики и рассмотрит все \"достопримечательности\". Трогать тебя руками ему там нельзя!");
                tasksLiteListWoman.add("Возьми партнера за руку и, не отводя от него взгляд, возьми в ротик его указательный палец. Изображая оральные ласки, заставь партнера мечтать о том, чтобы ты скорее применила свой талант на более подходящем для него органе!");
                tasksLiteListWoman.add("Помогите партнеру согнуться и попробовать достать языком до собственного члена. Если ему это удасться, вы можете доставить ему удовольствие через нижнее белье");
            }
            if (TasksAnal){
                //tasksLiteListWoman.add("Легкое анальное Задание Женщине 1: ...");
                //tasksLiteListWoman.add("Легкое анальное Задание Женщине 2: ...");
                //tasksLiteListWoman.add("Легкое анальное Задание Женщине 3: ...");
            }
            // Легкие Общие задания
            tasksLiteListAssignment.add("Если вы еще не дошли до нижнего белья, снимайте друг с друга одежду до неё по очереди");
            tasksLiteListAssignment.add("Помести палец на гениталии партнера, если он угадал какой это палец, положи второй и так далее для каждого правильного ответа");
            tasksLiteListAssignment.add("Танцуй стриптиз под музыку, что выберет партнер");
            tasksLiteListAssignment.add("Для этого фанта Вам понадобится повязка на глаза. Тот, кто вытянул данный фант, завязывает глаза своему партнёру и нежно обследует его тело губами и языком. Тот, у кого завязаны глаза, лежит в максимально расслабленной и удобной позе, наслаждаясь своими ощущениями.");
            tasksLiteListAssignment.add("Положи лед на место, куда укажет партнер");
            tasksLiteListAssignment.add("Завяжи себе глаза и угадайте 3 запаха, которые партнер вам покажет, если угадаете хотя бы два, партнер будет вашим рабом в течении 3х минут");
            tasksLiteListAssignment.add("Потрогай интимные зоны друг друга сунув руки друг другу в трусы");
            tasksLiteListAssignment.add("Сыграйте в ролевую игру, партнер что то украл в магазине а тебе нужно провести досмотр.");
            tasksLiteListAssignment.add("Подойди к партнеру сзади и в течение двух минут ласкай. Разрешено проникать под одежду, но снимать пока ничего нельзя.");


            if (TasksOral){
                tasksLiteListAssignment.add("Займите позу 69 и покусывайте друг друга за внутреннюю часть бедер");
                tasksLiteListAssignment.add("Сними штаны с партнера, если они еще есть и лизни внутреннюю часть бедер");
                tasksLiteListAssignment.add("Облизывай партнера от уха до ягодиц, одновременно снимая с него штаны, шепните ему на ухо что хотите сделать с ним сегодня, поглаживая гениталии партнера через нижнее бельё");
                //tasksLiteListAssignment.add("Легкое оральное Общее Задание 3: ...");
            }
            if (TasksAnal){
                //tasksLiteListAssignment.add("Легкое анальное Общее Задание 1: ...");
                //tasksLiteListAssignment.add("Легкое анальное Общее Задание 2: ...");
                //tasksLiteListAssignment.add("Легкое анальное Общее Задание 3: ...");
            }

            // Средние задания Мужчине
            tasksMediumListMale.add("Она встанет перед тобой в трусиках, води членом по ее попке одну минуту, можешь засунуть его под трусики, но не в нее!");
            tasksMediumListMale.add("Встань перед девушкой, которая лежит на спине. Она упирает свои ступни тебе в грудь, после чего членом ласкай ее киску, при этом не вводи член во внутрь.");
            tasksMediumListMale.add("Целуй ее попку прямо через трусики, можешь слегка покусывать, но не сделай ей больно.");
            tasksMediumListMale.add("Она встанет на четвереньки. Подойди к ней сзади и членом ласкай ее клитор. Можешь прижимать член к ее киске, но не вводить во внутрь. Это должно у нее вызвать дикое желание почувствовать в себе что-то \"твердое\".");
            tasksMediumListMale.add("Девушка садится к тебе на колени, при этом начинай ласкать руками ее грудь, живот и клитор, а губами целуй шею и спину. Девушка прижимается своими ягодицами к твоему пенису, и нежно трется об него, не пуская при этом тебя внутрь.");
            tasksMediumListMale.add("Сядь на диван, девушка должна лечь животом к тебе на колени. Вставь в ее киску пальчики и поиграйся с ней.");
            tasksMediumListMale.add("Она ляжет на кровать, подложив под бедра подушку. Ласкай двумя пальцами её киску.");
            tasksMediumListMale.add("Девушка должна лечь на спину, раздвинуть ножки и прижать их к груди. Используй слюну, чтобы ввести в ее киску свой большой палец, после этого второй рукой ласкай ее клитор.");
            tasksMediumListMale.add("Она ляжет на спинку и поднимет ножки. В течение 30 секунд нежными движениями пальцев води по её бедрами, попке и около киски.");
            tasksMediumListMale.add("Полностью разденься, если не разделся ранее. Девушка делает полуразжатый кулак (буква \"о\" Как будто держит виртуально член). Вставь свой член в полуразжатый кулак девушки и двигается в нём в течении времени на таймере.");
            tasksMediumListMale.add("Занимай удобное положение сидя, твоя девушка сядет на тебя сверху, одной рукой ласкай её грудь, второй играй с её киской. Действуй две минуты.");
            tasksMediumListMale.add("Она встаёт на четвереньки, хорошенько увлажни свои пальчики и ей киску, после этого введи один пальчик в её киску и начни им её мастурбировать. Когда она расслабится, добавь ещё один палец и продолжи.");
            tasksMediumListMale.add("Сдвинь в сторону её трусики, если они ещё на ней, оближи свой пальчик и медленно введи ей в киску. Можешь повторить это ещё пару раз.");
            tasksMediumListMale.add("Вы должны привязать свою девушку к стулу, завязать глаза и делать с ней все, что захочется пока идет время");
            tasksMediumListMale.add("Девушка встанет на четвереньки, расположись сбоку от нее и целуй ее шею, а круговыми движениями своей руки мастурбируй ее киску.");
            tasksMediumListMale.add("Прижми включенный вибратор к её киске. Если у вас есть специальная игрушка, то вставь её в киску и наслаждайся.");
            tasksMediumListMale.add("Слегка пошлепай ее по киске, для атмосферы можешь связать ей руки, чтобы она почувствовала твою власть над ней.");
            tasksMediumListMale.add("Она ляжет на спину, закинь её ножки назад и прижми мощный вибратор к её киске. Она должна продержаться 30 секунд.");
            tasksMediumListMale.add("Обнаженная дама стоит, руки привязаны вверх или вперед, глаза завязаны. Дама не знает, чего и в какой момент ожидать от тебя: или мягкого прикосновения перышка к разным частям тела, или прикосновения языком, или жгучего шлепка по попке.");
            tasksMediumListMale.add("Возьми вибратор и сядь на кресло, она сядет к тебе на колени и будет целовать, а ты должен вибратором массировать ее киску.");
            tasksMediumListMale.add("Девушка должна надеть трусики или колготки на голое тело. После этого ей следует сесть с раздвинутыми ножками на кресло и расслабиться. Возьми вибратор и будет ласкать им ее киску через одежду в течение двух минут.");
            tasksMediumListMale.add("Следующий фант твоя девушка должна выполнять с завязанными глазами, завяжи ей глаза и переключи фант. Постарайся прочитать текст её задания максимально возбуждающе.");
            tasksMediumListMale.add("Садись на диван, девушка ляжет боком к тебе на колени. Начни с поцелуев ее губ, шеи. После чего увлажни свои пальчики и введи их ей в киску. Ласкай ее киску, одновременно целуя ее груди и соски.");
            tasksMediumListMale.add("Девушка ляжет на кровать и расслабится, в это время ты смажешь один или два своих пальца и будешь мастурбировать ее киску.");
            tasksMediumListMale.add("В течение минуты мастурбируйте друг другу, делайте это медленно, не спешите закончить игру. Еще рано.");
            tasksMediumListMale.add("Девушка ложится на спину, смажь свои два пальца и введи их в ее киску. После этого совершай поступательные движения, а если умеешь, то и воздействует на ее точку \"G\".");
            tasksMediumListMale.add("Включи вибратор и ласкай им клитор девушки, при этом можешь ласкать ее киску своими пальцами.");
            tasksMediumListMale.add("Свяжи ей руки и положи девушку на животик. После этого можешь поиграть с ее дырочками пару минут.");
            tasksMediumListMale.add("Она ляжет на животик и приподнимет попу. Включи вибратор на маленькую мощность и в течение минуты води им по её половым губам, иногда останавливаясь на клиторе.");
            tasksMediumListMale.add("Свяжи её и делай с ней все, что захочеш, пока идет время.");
            tasksMediumListMale.add("Используя один из своих пальцев для демонстрации, покажи свои навыки внизу");

            tasksMediumListMale.add("Девушка полностью разденется и ляжет на спину. После этого возьми массажное масло и ласкай киску. Ладонями совершай круговые движения вокруг ее половых губ и клитора.");
            tasksMediumListMale.add("Поставь ее на колени и свяжи руки за спиной и завяжи глаза, идеально будет, если ты ее привяжешь. После этого сядь перед ней и начни ласкать ее киску.");
            tasksMediumListMale.add("Она встанет на четвереньки на край кровати, встань перед ее попкой на четвереньки и введи в неё большой палец. При этом твои проникновения должны длиться несколько секунд, после этого вытащи руку и через мгновение повтори еще 4 раза");
            tasksMediumListMale.add("Девушка ложится на животик. Парень смазывает свои пальчики и погружает их в горячую и возбужденную киску. В течение минуты он должен мастурбировать ее нежными движениями.");
            tasksMediumListMale.add("Она без трусиков встанет на четвереньки и подставит свои дырочки для ласк. В течение таймера ты можешь их гладить и массировать, но ничего внутрь не вводи.");
            tasksMediumListMale.add("Она примет позу, которую ты попросишь. После чего закроет глаза. В течение таймера ты можешь ласкать её тело как захочешь, используй пальчики и игрушки");

            if (TasksOral){
                tasksMediumListMale.add("Девушка должна лечь на спину, согнуть ножки в коленях и слегка их раздвинуть. Целуй и гладь ее бедра, уделяя особое внимание внутренней поверхности.");
                tasksMediumListMale.add("На какую часть тела ты хотел бы кончить партнеру? Пусть партнер поцелует твой пенис, если он согласен это сделать сегодня. Пусть поцелуют твою грудь, если позволит тебе когда-нибудь");
                tasksMediumListMale.add("Скажи своей девушке, какую позу принять и в течение таймера ты можешь делать с ней всё, что захочешь. Ласкай её киску, попку или грудь, используй член, пальцы, игрушки или язык.");
                tasksMediumListMale.add("Она ляжет на живот, подложив под бедра подушку. Ласкай её промежность языком и губами.");
                tasksMediumListMale.add("Помастурбируй себе в течение минуты так, чтобы твой член был возле её губ. Тебя трогать запрещено, можно только смотреть.");
                tasksMediumListMale.add("Она примет позу, которую ты попросишь. После чего закроет глаза. В течение 2 минут ты можешь ласкать её тело как захочешь, используй пальчики, язык и рот.");
                tasksMediumListMale.add("Нежно оближи ее киску, после этого обхвати ее клитор губами и слегка пососи его несколько секунд.");
                tasksMediumListMale.add("Она ляжет на живот, подложив под бедра подушку. Ласкай её промежность языком и губами.");
                tasksMediumListMale.add("Она ляжет на живот, подложив под бедра подушку. Ласкай её промежность языком и губами.");
                tasksMediumListMale.add("Погрузи свой член в сметану (йогурт), после этого она должна слизать все своим язычком.");
                tasksMediumListMale.add("Она ляжет на кровать, подложив под бедра подушку. Ласкай языком её киску.");
                tasksMediumListMale.add("Страстно целуй её начиная с груди, опускайся всё ниже, и остановись на долгих ласках клитора.");
                tasksMediumListMale.add("Помастурбируй себе в течение минуты так, чтобы твой член был возле её губ. Тебя трогать запрещено, можно только смотреть.");
                //tasksMediumListMale.add("Время для позы \"69\" - девушка сверху. Она медленно делает мужчине минет, а он \"лижущими\" движениями, проводя языком по всей её киске, ласкает её.");
                tasksMediumListMale.add("Она встанет на четвереньки на край кровати, встань перед ее попкой на четвереньки и постарайся \"проникнуть\" в ее киску языком. При этом твои попытки должны длиться несколько секунд, после этого отодвинься назад и через мгновение повтори.");
                tasksMediumListMale.add("Встань и раздвинь ноги, пусть она поласкает твои яички языком.");
                tasksMediumListMale.add("Девушка ляжет на край кровати, мужчина сядет перед ее раздвинутыми ножками на колени. Прижмется ртом к её киске и в течение 30 секунд будет нежно \"посасывать\" её.");
                tasksMediumListMale.add("Води членом по ее губам, она должна стараться удержать свой язычок на его кончике.");
                tasksMediumListMale.add("Девушка ложится на спину, а ты опускаешься головой между её ног, начинай целовать ей живот, постепенно переходя к её половому органу. Облизываё её большие половые губы, постепенно переходя к малым и входу влагалища. Последним стимулируется клитор.");
                tasksMediumListMale.add("Положи девушку на живот и вылижи её киску. Для большей пикантности девушка может одеть каблуки.");
                tasksMediumListMale.add("Девушка лежит обнаженная на кровати, широко расставив ноги. Ласкай поцелуями ее бедра, плавно опускаясь к клитору, касайся его языком, но без натиска, а плавно, возбуждая, таким образом, девушку.");
                tasksMediumListMale.add("Девушка садиться на кровать, а ты становишься перед ней на колени. Девушка очень страстно целует тебя в губы, а ты в свою очередь должен повторить горячий поцелуй у девушки между ножек. Попыток бесконечное число, целуетесь до тех пор пока мужчина не сможешь повторить поцелуй в идеале!");
                tasksMediumListMale.add("Сдвинь в сторону её трусики, если они на ней еще остались, и нежно оближи её киску. Не увлекайся, еще вся игра впереди!");
                tasksMediumListMale.add("Она раздвинет ножки. Целуй её животик и всё, что находится рядом с её киской");
                tasksMediumListMale.add("Выпусти из нижнего белья только яица и позволь партнеру их облизать.");
                tasksMediumListMale.add("Если она уже совсем голенькая, возьми молоко или сок и полей на её грудь. После этого ты должен облизать её, даже если вкусняшка стекла ниже ей животика, в киску.");
                tasksMediumListMale.add("Если её киска мокрая, вылижи всё. Если нет, смочи своим языком, схватив её крепко за ягодицы");
                tasksMediumListMale.add("Она встанет на четвереньки. Отодвинь ее трусики в сторону, после этого она проведет рукой по всем своим дырочкам, если там мокро, то даст тебе облизать пальчик.");
                tasksMediumListMale.add("Если на девушке еще остались трусики - сними их при помощи рта и зубов. Если трусиков уже нет, то просто расцелуй ее аппетитные булочки.");
                tasksMediumListMale.add("Партнер разденется до белья, а тебе нужно лишить ее оставшихся элементов одежды. Но сделать это нужно без помощи рук – только зубами.");
            }
            if (TasksAnal){
                tasksMediumListMale.add("Она встанет на четвереньки. Хорошенько увлажни ее попку с помощью смазки и нанеси ее на свой большой палец. После этого положи руку на ее попку таким образом, чтобы твой большой палец оказался на ее анальном отверстии. Аккуратно и нежно надави на ее анус, чтобы твой палец медленно вошел в него. После этого достань его и повтори.");
                tasksMediumListMale.add("Девушка должна лечь на спину, раздвинуть ножки и прижать их к груди. Используй смазку, чтобы ввести в ее попку свой большой палец, после этого второй рукой ласкай ее киску и клитор.");
                tasksMediumListMale.add("Дай ей облизать свой указательный палец, затем засунь ей его в попку. Проделай несколько фрикций.");
                tasksMediumListMale.add("Возьми смазку и нанеси ей на попку, также смажь свой палец. После этого медленно введи его ей в анус, если ей не будет больно, то можешь аккуратно совершать движения вперед-назад.");
                tasksMediumListMale.add("Сядь на диван, девушка должна лечь животом тебе на колени. Вставь в ее попку пробочку (если пробочки нет, используй большой палец второй руки) и пальчиками поиграйся с ее киской.");
                tasksMediumListMale.add("Девушка ляжет на спину и прижмет ноги к груди. Увлажни ее попку у аккуратно введи туда один или два пальца.");
                tasksMediumListMale.add("Мастурбируй попку своей девушки анальной пробкой (если пробки нет, используй большой палец) в течение минуты. Она может ласкать свою киску сама.");
                tasksMediumListMale.add("Она ляжет на живот и слегка раздвинет ножки, приподняв попку вверх. Введи в ней пальчик и немного помастурбируй им, после этого добавь ещё один и повтори. Если ей понравилось, можешь добавить ещё один.");
                tasksMediumListMale.add("Она встаёт на четвереньки, хорошенько увлажни свои пальчики и ей попку, после этого введи один пальчик в ей попку и начни им её мастурбировать. Когда она расслабится, добавь ещё один палец и продолжи.");
                tasksMediumListMale.add("Она встанет на четвереньки, увлажни свой пальчик и введи её в попку, когда она расслабится, аккуратно помастурбируй её анус несколько секунд.");
                tasksMediumListMale.add("Трахни ее попку анальной пробкой (или большим пальцем), но действуй очень аккуратно, не сделай ей больно!");
                tasksMediumListMale.add("Она ляжет на живот, а ты одной рукой будешь мастурбировать её киску, а второй попку.");
                tasksMediumListMale.add("Вставь в её попку пробочку или пальчик, а второй рукой ласкай её киску.");
                tasksMediumListMale.add("Возьми массажное масло и сделай ей массаж попки и бедер. Если захочешь, то в конце задания можешь попробовать вставить пальчик в ее попку, предварительно нанеся туда смазку.");
                tasksMediumListMale.add("Найди порно с анальным сексом и не забудь взять смазку, после этого включи таймер. Во время просмотра пальчиком ласкай анальное отверстие своей девушки, подготавливая его для следующих заданий.");
            }
            if (TasksAnal & TasksOral){
                tasksMediumListMale.add("Поставь ее на колени так, чтобы ты имел доступ к ее попке и киске. Проведи несколько раз языком от киски до анального отверстия.");
                tasksMediumListMale.add("Она стоит, уперевшись руками на стол. Сядь сзади неё и ласкай в течение 30 секунд язычком и губами её попку и киску.");
                tasksMediumListMale.add("Девушка ляжет на живот и ненадолго будет поднимать и опускать попку, в этот момент ты должен ртом прижаться к ее попке и язычком ласкать ей анус");
                tasksMediumListMale.add("Вставь пальчик ей в попку и вылижи ее киску.");
                tasksMediumListMale.add("Она ляжет на животик и подставит тебе свою попку. Медленно и нежно ласкай ее губами и ртом, рукой можешь гладить ее киску, предварительно увлажнив свои пальцы.");
                tasksMediumListMale.add("Нанеси ей на анус взбитые сливки или что-то сладкое, после этого слижи все. Если ей понравится и захочется еще, то повторите.");
                tasksMediumListMale.add("Сядь перед ее попкой, после чего несколько раз проведи языком по ее промежности и анальному отверстию.");
                tasksMediumListMale.add("Девушка встает на четвереньки, после этого парень проводит язычком по ее киске и анальному отверстию пока идет время.");
                tasksMediumListMale.add("Девушка встает в полный рост или на колени, выставив свою киску для оральных ласк. Пристройся к ней сзади и вылизывает её дырочки, пока не сработает таймер.");
                tasksMediumListMale.add("Девушка встает на четвереньки и прогибает спинку, после этого ты должен 5 раз провести языком по ее киски, двигаясь снизу вверх, доходя язычком до анального отверстия. Если захочешь, то может также попробовать на язычок и вторую дырочку.");
                tasksMediumListMale.add("Девушка встает рачком, молодой человек присаживается сзади, раздвигает ее булочки в стороны и язычком ласкает ее анус. При этом ласки должны сводится к тому, что сперва он несколько секунд \"острым\" язычком точечно воздействует на дырочку, после чего совершает несколько движений вокруг самого ануса.");
            }
            // Средние задания Женщине
            tasksMediumListWoman.add("Возьми рукой член парня, а другой рукой ноготочками поиграй с его головкой. Головку лучше чем-либо смазать. Будь аккуратна и не поцарапай его");
            tasksMediumListWoman.add("Мужчина подносит свой член к твоей киске, возьми его рукой и в течение 30 секунд ласкает им свой клитор. Не вводи внутрь влагалища!");
            tasksMediumListWoman.add("Надень трусики, если на тебе их уже нет и уложи партнера на спину. Затем сядь на него сверху, просунув член к себе в трусики и прижав его к киске. В таком положении нужно тереться о него в течение минуты.");
            tasksMediumListWoman.add("Встань на четвереньки, прогни спинку и опустись грудью на кровать. После этого парень подойдет к тебе сзади и прижмется и начнет ласкать твоё тело руками, расположив свой член таким образом, чтобы он прикасался к твоим эрогенным зонам, но не проникал в них. В процессе ты должна сексуально извиваться и рукой может ласкать свой клитор.");
            tasksMediumListWoman.add("Парень садится на кровать, откинув руки назад. Ты ложишься между его ног, таким образом, чтобы твоя киска была у его члена. После этого обхвати член своими бедрами и нежно извивайся, стимулируя свой клитор и киску его половым органом.");
            tasksMediumListWoman.add("Он встанет сзади тебя. Зажми его член между ножек, чтобы он прижимался к твоей киске. Он будет совершать фрикции пока идет время.");
            tasksMediumListWoman.add("Сядь на него сверху и трись об его член своей киской.");
            tasksMediumListWoman.add("Закинь на него ножку, подставив свою киску для ласк. Он в течение таймера будет ласкать тебя пальчиком, но пока через трусики.");
            tasksMediumListWoman.add("Вставь его член между ваших грудей и очень медленно двигай ими. Пусть парень наслаждется твоими изгибами, трущимися о него");
            tasksMediumListWoman.add("Пришло время немного подурачиться. Встань на четвереньки и притворись собачкой, которой жарко. Высунь язычок и оближи свои лапки, повиляй хвостиком. А если тебе по душе другое животное, например, кошечка или зайчик, изобрази его.");
            tasksMediumListWoman.add("Ты должна встать, сексуально выставив попку в трусиках. Мужчина в течение минуты ласкает ее, шлепает, гладит и щупает, иногда засовывая \"любопытные\" пальчики тебе в трусики.");
            tasksMediumListWoman.add("Ляг на спину и раздвинь ножки. Он засунет руку тебе в трусики и потрогает твою кисуню.");
            tasksMediumListWoman.add("Играйся с яичками партнера до своего следующего фанта.");
            tasksMediumListWoman.add("Он должен лечь на край кровати, сядь возле него и вытянутой рукой мастурбируй ему член, в это время он может целовать тебя, твою шею, губы...");
            tasksMediumListWoman.add("Девушка садится на бедра к парню и в течение таймера мастурбирует его член руками.");
            tasksMediumListWoman.add("Отложенное задание. За просмотром фильма мужчина помещает включенный вибратор в твои трусики, а ты не должна подавать вида, что что-то случилось и продолжить просмотр кино.");
            tasksMediumListWoman.add("Ляг на спину, обхвати свои ножки, прижав их к груди. Он в течение минуты будет ласкать твою киску двумя пальчиками.");
            tasksMediumListWoman.add("Возьми ленточку или шнурок, обвяжи ему яички у самого основания мошонки. Не затягивать узел слишком сильно. После этого она играет с яичками перышком или кисточкой для макияжа");
            tasksMediumListWoman.add("Он сядет на край кровати, поставив ноги на пол. Встань между его ног и танцуй эротический танец, пока он медленно мастурбирует свой член. Тебе не обязательно уметь танцевать, достаточно просто двигаться эротично.");
            tasksMediumListWoman.add("Нанеси немного смазки на свой пальчик, после этого поводи им по головки его члена. Совершай им круговые движения. Можешь обхватить его член пальцами так, чтобы его головка уперлась в твою ладонь и нежно помастурбировать ему.");
            tasksMediumListWoman.add("Сядь напротив него в соблазнительной позе и, в течение таймера, ласкай себя так, чтобы заставить его сердце забиться чаще. Хорошо если при этом ты не забудешь издавать страстные звуки.");
            tasksMediumListWoman.add("Сядь и раздвинь свои ножки, он положит руку на твою киску, а ты будешь ласкать себя с помощью его руки.");
            tasksMediumListWoman.add("Он ляжет на спину. Сядь ему на живот, повернувшись попкой к его лицу и медленно ласкай его член. Он будет наслаждаться видом твоей попкой и приятными ощущениями.");
            tasksMediumListWoman.add("Время \"ахегао\". Сядь перед своим мужчиной и начни ласкать свои груди и тело, после этого высунь язычок и начни стонать.");
            tasksMediumListWoman.add("Ложись на спину и закинь ножки к голове. Мужчина встаёт перед тобой и медленно начинает мастурбировать пальчиками одной руки твою киску, а второй рукой свой член.");
            tasksMediumListWoman.add("Ляг на спинку, твой парень должен встать так, чтобы ты могла дотянуться до его члена рукой. Начни сексуально ласкать свою киску, когда ты достаточно возбудишься, - свободной рукой начни мастурбировать его член, при это не останавливай ласки своей киски.");
            tasksMediumListWoman.add("Парень должен только наблюдать. Возьми вибратор, встань на четвереньки и начни ласкать им свою киску. При этом ты должна издавать стоны, от которых он должен возбуждаться.");
            tasksMediumListWoman.add("Ты должна нагнуться над столом, он подойдет сзади и прижмет тебя к нему. Теперь ты в его власти. Он будет ласкать твою попку, гладить киску, периодически хорошенько тебя шлепая.");
            tasksMediumListWoman.add("Прими откровенную позу, и в течение таймера ласкай свою киску так, как тебе нравится. После этого парень должен повторить.");
            tasksMediumListWoman.add("Ляг на живот. Он возьмёт вибратор и будет прижимать его к твоей киске на 5-10 секунд, после чего убирать. И снова прижимать. Так 5 раз.");
            tasksMediumListWoman.add("Встань на колени и упрись руками на стену. Он сядет сзади тебя и в течение таймера будет ласкать твою киску фаллосом или своим членом");
            tasksMediumListWoman.add("Возьми в руку член мужчины и плавными движениями массируй его вдоль ствола. Хорошо – если есть смазка. Не надо мастурбировать – для этого есть отдельный фант – нужно именно массировать: плавно и медленно водить по члену рукой, сжимая и отпуская его. Можно помять его в обеих руках.");
            tasksMediumListWoman.add("Некоторые считают, что вибратор - это чисто женская игрушка. Пришло время проверить, в течение таймера ласкай вибратором яички парня. Его задача - попробовать не кончить от этого.");
            tasksMediumListWoman.add("Сядь на стол или край кровати так, чтобы твоя киска была \"открыта\". Он увлажнит свои пальчики и в течение 30 секунд будет ласкать ими твою дырочку.");
            tasksMediumListWoman.add("Сделай ему массаж уздечки. Эта часть мужского члена соединяющая головку и крайнюю плоть - самая чувствительная зона. Плавными движениями пальца массируй это место. Можно нанести туда немного смазки или слюны. Другой рукой в это время можно гладить мужчине яички.");
            tasksMediumListWoman.add("Ляг на край кровати на животик и загни ножки назад. Он в течение таймера будет ласкать твою кисулю своими пальчиками.");
            tasksMediumListWoman.add("Ляг парню на колени, чтобы твоя попка была в его руках. В течение минуты он может делать с твоей киской все, что захочет.");
            tasksMediumListWoman.add("Он ляжет на спину. Устройся рядом с ним так, чтобы твоя грудь была прижата к его животу. После чего начни нежно мастурбировать его член, одновременно целуя его грудь.");


            if (TasksOral){
                tasksMediumListWoman.add("Медленно введи два пальца в свою влажную киску и дай их облизать своему парню. Чем влажнее будут твои пальчики - тем лучше!");
                tasksMediumListWoman.add("Делай минет парню как захочешь и громко стони при этом. Парню также запрещено молчать.");
                tasksMediumListWoman.add("Встань с широко раздвинутыми ногами и раскрой свою киску для ласк. Мужчина сядет между твоих ног и будет язычком доставлять тебе удовольствие в течение таймера.");
                tasksMediumListWoman.add("Сядь перед мужчиной на колени, возьми его член в руку и самым кончиком своего язычка \"поиграй\" с его членом в течение таймера.");
                tasksMediumListWoman.add("Сделай ему необычный минет. Необычный тем, что ты должна следовать указаниям, которые он будет тебе давать.");
                tasksMediumListWoman.add("Пощекочи языком головку его члена, глядя ему в глаза.");
                tasksMediumListWoman.add("Возьми в рот его яички, а потом проведи языком по его члену от основания до кончика.");
                tasksMediumListWoman.add("Он стоит на ногах, сядь перед ним и несколько раз оближи головку его члена.");
                tasksMediumListWoman.add("Ложись на край кровати и закинь ножки назад, он садится около твоей киски и в течение таймера нежно вылизывает её, иногда переключаясь на клитор.");
                tasksMediumListWoman.add("Оближи круговыми движениями языка головку его члена.");
                tasksMediumListWoman.add("Сядь на стол и раздвинь ножки. Он сядет около тебя и будет язычком ласкать твою киску, пока ты гладишь его по голове.");
                tasksMediumListWoman.add("Он лежит на спине. Сядь так, чтобы твой клитор был над его высунутым язычком, а ты свой вес удерживала на руках, откинувшись назад. Пока идет время, он будет доставлять удовольствие тебе своим языком.");
                tasksMediumListWoman.add("Представь, что ты порно звезда, которая проходит кастинг. Покажи продюсеру на сколько глубокий ты умеешь делать минет.");
                tasksMediumListWoman.add("Соси его член стоя на коленях и смотря ему в глаза в течение одной минуты.");
                tasksMediumListWoman.add("Парень встает на колени на кровати. Ляг на живот перед ним и помастурбируй его член перед своим лицом в течении таймера. Иногда бери его в рот. После выполнения задания крепко поцелуй его в засос.");
                tasksMediumListWoman.add("Открой ротик и высунь язык. Он положит на него свой член. Теперь ты должна облизать его со всех сторон.");
                tasksMediumListWoman.add("Завяжи глаза партнеру, толкни его на кровать и соси ему член в течении таймера");
                tasksMediumListWoman.add("Прижмись щекой к его животу, подними его член к своему ротику и язычком поиграй с его кончиком.");
                tasksMediumListWoman.add("Парень лежит на спине. Встань на колени по обе стороны от его головы и дай ему возможность оттягивать твои половые губки целуя их взасос. Продолжайте это в течении таймера");
                tasksMediumListWoman.add("Он ляжет на спину. Расположись удобно над его лицом, твоя киска должна быть на уровне его рта. Он высунет язык и будет стараться вылизать её, пока ты сексуально извиваешься над ним.");
                tasksMediumListWoman.add("Ты должна лечь на спину, после чего парень встанет на колени перед твоим лицом и начинает водить своим членом перед твоими губами, как будто дразня. Ты должна пытаться ухватить его член своим ротиком без помощи рук и не отрывать спину от кровати");
                tasksMediumListWoman.add("Лижи, соси и бери в рот его яички, ему должно понравится.");
                tasksMediumListWoman.add("Проведи несколько кругов язычком по головке члена, после этого возьми его член полностью в рот, медленно вынимай и повторяй заново. 3 раза");
                tasksMediumListWoman.add("Мужчина сидит или лежит. Опустись грудью к его возбуждённому половому органу и, помогая руками, трись сосками то одной, то другой груди о головку его члена. При этом можно иногда касаться головки члена языком, чтобы смочить его и соски лучше по нему скользили.");
                tasksMediumListWoman.add("Сядь перед ним на колени, возьми его член в руку и оближи его полностью! Пока не бери в рот, только язычком!");
                tasksMediumListWoman.add("Страстно поцелуй своего парня в губы, используй язык и ласкай его тело руками. После этого он должен выполнить такой же поцелуй, но уже с твоей киской.");
                tasksMediumListWoman.add("Поцелуй член своего парня, опускайся к яичкам и нежно ласкай их и промежность языком, затем проведи языком вдоль ствола, обведи вокруг головки и заканчивай страстным поцелуем в губы.");
                tasksMediumListWoman.add("Парень удобно располагается лежа на спине. А ты медленно и нежно проводи своей грудью по его телу, спускаясь к члену, после чего возьми его в рот и оближи.");
                tasksMediumListWoman.add("Ласкай его член обеими руками, губами и языком.");
                tasksMediumListWoman.add("Парень сидит или лежит. Твоя задача сделать как минимум 3 приседания над его лицом. Если ты всё ещё в трусиках, то срочно надо убрать эту ненужную преграду. Прикасаться к девушке в этом задании не разрешается - спортсменам мешать не надо - пусть тренируется");
                tasksMediumListWoman.add("Пососи головку его члена как \"чупа-чупс\".");
                tasksMediumListWoman.add("Возьми член парня в руку и нежно посасывай его головку, после этого язычком ласкай его отверстие.");
                tasksMediumListWoman.add("Помассируй его задницу и бедра, чтобы он напрягся и завелся. Взгляните на его растущий член, ты можешь его потрогать или даже лизнуть");
                tasksMediumListWoman.add("Парень ложится на спину, а ты язычком ласкай его член, медленно поднимаясь от основания к кончику. Дойдя до верху снова спускайся и начинает сначала. Повтори 3 раза");

            }
            if (TasksAnal) {
                tasksMediumListWoman.add("Встань на пол и поставь одну ногу на любую возвышенность. Он сядет сзади тебя и введет тебе в киску секс игрушку. Если у Вас их нет, то он может использовать свои пальчики. Но тогда один из них по его желанию проникнет в твою попку");
                tasksMediumListWoman.add("Ляг спиной к нему, он вставит пальчик тебе в попку на 30 секунд. В это время ты должна ласкать сама свою киску и клитор.");
                tasksMediumListWoman.add("Медленно и нежно вставь пальчик себе в попку. Он только наблюдает.");
                tasksMediumListWoman.add("Парень должен вставить в твою попку пробочку на следующие 3 фанта. Либо все следующие три фанта ты выполняешь с его большим пальцем в твоей попке.");
                tasksMediumListWoman.add("Встань на четвереньки. Он в течение таймера будет ласкать твою киску и попку пальчиками.");
                tasksMediumListWoman.add("Вставь себе анальную пробку и продолжить игру с ней, пока твоя попка не понадобится для других заданий. Если пробки нет, каждый фант позволяй ему вставлять тебе большой пальчик в попку.");
                tasksMediumListWoman.add("Встань на колени и прижмись грудью к кровати. Парень увлажняет твоё анальное отверстие, смазывает свой большой палец и вводит его тебе в попку. После этого совершает нежные и плавные движения, вставляя его на всю глубину.");
                tasksMediumListWoman.add("Вставь в свою попку хвостик, после этого подойди к парню, раздвинувшему ноги, и пощекочи хвостиком его член и яички в течение таймера.");
                tasksMediumListWoman.add("Парень ложится на живот и раздвигает ноги. А ты мастурбируешь пальчиком его попку.");
                tasksMediumListWoman.add("Сядь на корточки, он вставит тебе в попку один или два пальца, после чего ты должна начать двигаться вверх-вниз, не выпуская их из себя.");
                tasksMediumListWoman.add("Ложись на живот и расслабь попку. Мужчина руками раздвигает тебе булочки и язычком ласкает дырочку.");
            }
                if (TasksAnal & TasksOral){
                    tasksMediumListWoman.add("Ложись на спину и прижми ножки к груди. Мужчина язычком ласкает твою киску, делая движения по всей киске. Если он захочет, то может начинать \"двигаться\" от попки.");

            }
            // Средние задания Общие
            tasksMediumListAssignment.add("В следующих 4 заданиях вы меняетесь ролями. Всё что для девушки выполняет парень, а то что для парня выполняет девушка. (Пример: \"ласкай её грудь\" - то наоборот девушка ласкает грудь парня. Пример 2: \"возьми его за член\" - то парень трогает половые органы девушки и т. Д.) Чтобы там не было, постарайтесь полностью поменяться.");
            tasksMediumListAssignment.add("Поцелуи это не только прелюдия. В течение минуты делайте страстный поцелуй, это может быть \"французский\" или делайте так, как вам больше нравится. В процессе можете ласкать эрогенные зоны друг друга.");
            tasksMediumListAssignment.add("Снять всё, если на тебе еще есть одежда");
            tasksMediumListAssignment.add("привяжите партнера к стулу, вы так будете играть до конца. Партнер должен этим пользоваться");
            tasksMediumListAssignment.add("Свяжите руки партнеру за спиной, он будет таким до коцна игры");
            tasksMediumListAssignment.add("Руками изучите половые органы друг друга, а затем мастурбируйте друг другу пока идет таймер.");
            tasksMediumListAssignment.add("Парень становится на четвереньки, девушка становится сзади и имитирует секс, при этом можно мастурбировать его член");
            tasksMediumListAssignment.add("В течение таймера ласкайте интимные места друг друга!");
            tasksMediumListAssignment.add("Тому, кто вытянул данный фант должен замереть и не шевелиться в течении таймера, а партнер в свою очередь может делать все что угодно, нежно трогать где пожелает, целовать куда заблагорассудиться, помните - сопротивляться и шевелиться нельзя. Как только ваш партнер вдоволь вами наиграется, можете тянуть следующий фант.");

            if (TasksOral){
                tasksMediumListAssignment.add("Время для позы \"69\" - девушка сверху. Она медленно делает мужчине минет, а он \"лижущими\" движениями, проводя языком по всей её киске, ласкает её.");
                tasksMediumListAssignment.add("Вылижите, а затем подуйте на верхнюю часть полового органа партнера. Повторите 10 раз и посмотрите как ноги начнут дрожать");
                tasksMediumListAssignment.add("Поза 69, побалуйте друг друга");
            }
            if (TasksAnal){
                // tasksMediumListAssignment.add("Среднее анальное Общее Задание 1: ...");
               // tasksMediumListAssignment.add("Среднее анальное Общее Задание 2: ...");
              //  tasksMediumListAssignment.add("Среднее анальное Общее Задание 3: ...");
            }

            // Экстрим задания Мужчине
            tasksExtremeListMale.add("Прижми напарницу к стене, которая ведет к вашим соседям и заставь её кричать от удовольствия, входя в неё");
            tasksExtremeListMale.add("Подразни свою девушку. Нежно войди в ее киску на всю глубину, после чего достань член и повтори это еще несколько раз. В любой позе которой захочешь");
            tasksExtremeListMale.add("Девушка смазывает внутреннюю поверхность бедер смазкой, после чего встает в полный рост и сильно сжимает свои бедра. Твоя задача - проникнуть своим членом между ее бедер \"под киской\". При этом обеспечен плотный контакт половых органов.");
            tasksExtremeListMale.add("Сядь на край кровати или кресла, девушка садится киской на твой член сверху и медленно двигается на нем в течение таймера.");
            tasksExtremeListMale.add("Завяжи ей глаза. После этого подойди к ней сзади, возьми за шею и стоя мастурбируй ее киску, пока ее ножки не начнут подкашиваться от удовольствия или не сработает таймер.");
            tasksExtremeListMale.add("Ложитесь на бок, ты находишься сзади девушки. После этого войди в киску девушки и двигайся пока идет таймер. Положи руку на ее лобок и пальчиками ласкай ей клитор, это сделает ее ощущения незабываемыми. Также можешь ласкать ее шею губами и шептать ей что-то приятное на ушко.");
            tasksExtremeListMale.add("Девушка должна встать в дверной проем и прижаться к двери, уперевшись одной ногой об противоположную сторону двери. В этой позе войди снизу в ее киску и двигайся пока идет время");
            tasksExtremeListMale.add("Она ляжет на спину, повернувшись немного боком. Одна её нога будет выпрямлена, вторая прижата к груди. В этой позе её киска будет открыта для приема твоего члена. Войди в неё и люби, пока не сработает таймер.");
            tasksExtremeListMale.add("Время страстного секса! Поставь ее на четвереньки, возьми за волосы и трахай 2 минуты.");
            tasksExtremeListMale.add("Привяжи ее руки к ногам или используй специальные оковы, чтобы обездвижить ее. Теперь у тебя отличная возможность воплотить все свои сексуальные фантазии в реальность.");
            tasksExtremeListMale.add("У тебя есть таймер, чтобы довести её до оргазма. Возьми вибратор и другие игрушки и в течение всего времени мастурбируй ей. Она должна кончить на последних секундах задания.");
            tasksExtremeListMale.add("Она должна лечь на край кровати и раздвинуть ножки, засунь пальчик ей в ротик и люби ее в киску одну минуту.");
            tasksExtremeListMale.add("Встань в полный рост, подойди к ней сзади и возьми ее за живот, после этого слегка подсядь, чтобы твой член смог снизу войти в ее киску и двигайся две минуты, также второй рукой можешь поиграть с ее клитором.");
            tasksExtremeListMale.add("Смажь ее тело маслом, после этого она раздвинет ножки и пустит тебя в свою киску. Люби ее пока идет таймер. Красивый блеск ее тела будет приятным дополнением к твоим ощущениям от секса.");
            tasksExtremeListMale.add("Она ляжет на бок, войди в ее киску сзади и двигайся так в течении таймера. Руками можешь ласкать ее клитор и грудь.");
            tasksExtremeListMale.add("Положи ее на животик и свяжи руки за спиной. После этого медленно трахай ее мокрую киску в течении таймера.");
            tasksExtremeListMale.add("Свяжи ей руки за спиной, после этого ложись. Она должна сесть на твой член, а ты любить ее в течении таймера.");
            tasksExtremeListMale.add("Ложись на спину, она сядет на тебя в позу обратной наездницы и полностью откинется назад. Таким образом она будет лежать на тебе. В этой позе тебе легко довести ее до оргазма, лаская ее клитор.");
            tasksExtremeListMale.add("Девушка раздвигает ножки, в этой позе войди в нее своим членом на всю глубину. После этого прижми к вашим половым органам вибратор таким образом, чтобы он воздействовал и на член, и на киску. Сами движения в сексе должны быть короткими, член должен оставаться в киске максимально глубоко.");
            tasksExtremeListMale.add("Поставь ее раком, возьми за волосы и люби в киску, она должна сексуально прогнуть спинку и издавать стоны.");
            tasksExtremeListMale.add("Посади девушку на стул или кресло, подложив ей под киску включенный вибратор. Она не должна вставать с него, пока не сработает таймер(даже если испытает оргазм).");
            tasksExtremeListMale.add("Сядь на колени, она введет член в свою киску и облокотится назад. Возьми ее за талию и люби в течение минуты, наслаждаясь красивым видом на ее груди.");
            tasksExtremeListMale.add("Посади девушку на пол, ее спина должна быть прислонена к кровати. Теперь свяжи ей руки, чтобы она не могла сопротивляться, после этого возьми вибратор и страстно ласкай им киску в течение таймера.");
            tasksExtremeListMale.add("Девушка встанет на одно колено и две руки, выставив вторую ногу вперед. Войди в неё сзади в этой позе и страстно двигайся в течении таймера.");
            tasksExtremeListMale.add("Расположись на край кровати, женщина садится на твой член, повернувшись к тебе спиной. При этом она должна удерживать весь свой вес на своих ногах и руках, чтобы не сковывать твои движения. Люби её время пока идет таймер.");
            tasksExtremeListMale.add("Твоя девушка лежит на спине и не сопротивляется. Возьми вибратор и в течение таймера ласкай им её киску. Можешь помогать себе своими пальчиками.");
            tasksExtremeListMale.add("Займи полулежачее положение на диване. Девушка должна оседлать тебя, упершись на спинку дивана, встав на колени. Возьми её за попку и помогай двигаться, при этом должна быть максимальная амплитуда движений и медленный темп.");
            tasksExtremeListMale.add("Ты выбираешь позу, которую должна принять твоя девушка. После этого в течение таймера мастурбируй её киску вибратором. Если она кончит до окончания времени, можешь снизить мощность вибратора и продолжить.");
            tasksExtremeListMale.add("Покажи партнеру что такое настоящее проникновение! Ты садишься, вытянув вперед ноги, а партнер располагается сверху, обхватывая тебя ногами за талию. Совершай движения бедрами, одновременно ласкай губами и языком грудь партнера.");
            tasksExtremeListMale.add("Она ляжет на спину и закинет ноги на тебя. Люби её так глубоко и сильно, как можешь.");
            tasksExtremeListMale.add("Она упрется коленями на край кровати. Войди в неё в этой позе сзади и люби, пока не закончится отведенное время.");
            tasksExtremeListMale.add("Девушка ложится животом на край кровати, поставив колени на пол. Мужчина берет вибратор и в течение таймера ласкает им киску своей девушки. Она не должна кончить за это время, только максимальное возбуждение.");
            tasksExtremeListMale.add("Твоя девушка в коленно-локтевой позе ложиться грудью на простынь, ты медленно двигаешься в её киске в течении таймера.");
            tasksExtremeListMale.add("Девушка сядет на стол, расположив ножки вместе сбоку. Войди в неё, в этой позе её киска будет максимально узенькой.");
            tasksExtremeListMale.add("Ложись удобно на спину, она сядет на твой член спиной к тебе и будет двигаться пока не закончится время");
            tasksExtremeListMale.add("Свяжи ее так, чтобы ее ножки были широки раздвинуты и у нее не было возможности мешать тебе. Возьми мощный вибратор и води им по ее киске. Действуй по таймеру или пока она не кончит!");
            tasksExtremeListMale.add("Девушка встанет коленями на стол или стиральную машину(подложите что-то мягкое, чтобы не было больно) и упрется руками об стену. Парень войдет в нее сзади и страстно возьмет за волосы. Двигайся пока идет время.");
            tasksExtremeListMale.add("Посади её на кухонный стол лицом к себе, она раздвинет ножки, после чего войди в её киску и люби пока не истечет время.");
            tasksExtremeListMale.add("Девушка встает раком, возьми вибратор и плотно прижми его киске, стараясь воздействовать не только на клитор, но и на остальную область ее генеталий.");
            tasksExtremeListMale.add("Девушка ляжет на край кровати и поднимет ножки вверх. Войди в ее киску стоя перед ней, она может положить ножки к тебе на плечи или поставить на грудь.");
            tasksExtremeListMale.add("Девушка ложится на спину, широко раздвигает ножки и ласкает свою грудь. Мужчина в этой позе берет ее за бедра и вводит свой член в нее. Занимайтесь сексом в этой позе пока не закончится время или ты не кончишь.");
            tasksExtremeListMale.add("Она встанет перед зеркалом, ты входишь в неё сзади и начинаешь \"любить\" в этой позе, наслаждаясь видом на Ваше отражение.");
            tasksExtremeListMale.add("Девушка раздвигает ножки. Задача мужчины с помощь любых средств, кроме члена, довести её до оргазма. Время ограничено таймером. Или нет...");
            tasksExtremeListMale.add("У тебя есть ограниченное время, чтобы довести ей до оргазма с помощью своих рук. Если ты этого не сможешь сделать, то играем дальше.");
            tasksExtremeListMale.add("После того, как ты кончишь в киску девушки, ты должен ещё в течение какого-то времени медленно входить в неё членом на небольшую глубину.");
            tasksExtremeListMale.add("Задача девушки наклониться на стол открыв взору парня свои прелести. Парень мастурбирует пока не кончит на девушку.");
            tasksExtremeListMale.add("Она встанет к стенке на время указанное таймером. Постарайся довести её до оргазма своими руками.");
            tasksExtremeListMale.add("Девушка должна удобно лечь и раздвинуть ножки. Если она еще недостаточно возбуждена, натри ее массажным маслом. После этого пальчиками доведи ее до оргазма, воздействуй на ее точку \"G\", если ты знаешь, где она находится, второй рукой ласкай ее клитор.");
            tasksExtremeListMale.add("Свяжи свою девушку и прижми к ее киске включенный вибратор. Смотри на её беспомощное состояние, пока она не кончит!");
            tasksExtremeListMale.add("В течение таймера ты можешь ласкать её тело вибратором. Сам выбирай мощность и силу нажатия, можешь просто \"дразнить\" её или довести до оргазма. А после него можешь не останавливаться и продолжать. Выбирай сам.");
            tasksExtremeListMale.add("Девушка встанет на четвереньки, подложив под живот одеяло или подушку. Войди в нее сзади и нежно люби в этой позе, пока не случится оргазм!");

            if (TasksOral){
                tasksExtremeListMale.add("Положи девушку на край кровати чтобы с кровати свисали ноги. Далее за ноги аккуратно приподними ее, положив ноги себе на плечи так, чтобы твоя голова была между её ног. Придерживай девушку за талию. На кровати должна остаться только её голова. Девушка при этом теребит свои сосочки. Доставляй ей удовольствие, пока девушка не кончит.");

                tasksExtremeListMale.add("Посади свою девушку на стол и сядь перед ее раздвинутыми ножками, просунь руки под ее ноги, чтобы они оказались на твоих плечах и начни делать ей кунилингус. Действуй пока не закончится время.");
                tasksExtremeListMale.add("Девушка встает на четвереньки, после чего включи вибратор и прижми его к киске, а она зажимает его ступнями, чтобы он никуда не убежал. После этого подойди к девушке спереди и вставь свой член ей в рот, а она в течение всего оставшегося времени должна делать тебе минет.");
                tasksExtremeListMale.add("Сядь за компьютер, она заберется под стол и будет делать тебе минет. В это время ты можешь читать новости, проверять почту или просто наслаждаться ощущениями.");
                tasksExtremeListMale.add("Девушка ложится на спину и сгибает ножки в коленях. Делай ей качественный кунилингус, доводя ее до оргазма. В процессе она может подсказывать тебе, когда ускориться или действовать сильнее, держа тебя за голову.");
                tasksExtremeListMale.add("Облокотись на спинку дивана, она встанет над твоим лицом своей киской и будет слегка шевелиться. Ты должен мастурбировать свой член, одновременно посасывая ее киску.");
                tasksExtremeListMale.add("Сядь на пол и облокотись спиной на кровать. Теперь Вы должны занять позу \"69\", при которой она будет сверху, поставив колени на кровать и руки на пол.");
                tasksExtremeListMale.add("Парень берет в рот ее указательный пальчик. Все, что он будет проделывать с ее пальцем, в течение времени, она будет в это же время, проделывать с его членом.");
                tasksExtremeListMale.add("Сядь на пол, оперевшись спиной на кровать. Она подойдет к тебе, перекинет одну ногу за твое плече и поставит на кровать. После этого делай ей кунилингус.");
                tasksExtremeListMale.add("Она сядет перед тобой и будет ласкать свою грудь, в это время ты должен мастурбировать на нее, пока не кончишь. Когда будешь кончать, постарайся забрызгать всю ее грудь и шею.");
            }
            if (TasksAnal){
                tasksExtremeListMale.add("Она ляжет на спину и широко разведет ноги. Люби её в попку минуту и наблюдай за тем, как она ласкает свою грудь.");
                tasksExtremeListMale.add("Люби свою девушку в попку в течение таймера. Если ты будешь готов кончить, то делай это во внутрь!");
                tasksExtremeListMale.add("Девушка ляжет на бок и согнет ноги в коленях и обхватит одну ножку рукой. Воспользуйся смазкой, чтобы аккуратно войти в ее попку, после чего люби ее в этой позе 60 секунд.");
                tasksExtremeListMale.add("Она ляжет на живот. Хорошенько увлажни её попку и свой член. После чего войди в её узенькую попку. Двигайся медленно и в процессе сжимай её попку.");
                tasksExtremeListMale.add("Она встанет раком на краю кровати, займись сексом с ее попкой!");
                tasksExtremeListMale.add("В течение таймера девушка стоит на четвереньках, а ты вводишь в её попку член на глубину головки.");
                tasksExtremeListMale.add("Она ляжет на живот, свяжи ей руки и введи свой член в ее попку. Двигайся в ней одну минуту.");
                tasksExtremeListMale.add("Девушка садится на стул \"задом наперёд\" и приподнимает попку, уперевшись руками на спинку стула. Трахни её сзади в попу.");
                tasksExtremeListMale.add("Она ляжет на живот, увлажни ее попку и войди в нее сверху. Двигайся активно пока идет время.");
                tasksExtremeListMale.add("Девушка встает на четвереньки, войди в ее киску в этой позе, вставив ей в попку свой большой палец. Ты должен любить свою девушку пока не закончится время.");
                tasksExtremeListMale.add("Она стоит на четвереньках спиной к тебе, держи ее за волосы и люби в попку в течение указанного времени.");
                tasksExtremeListMale.add("Она ляжет на спину и раздвинет ножки. Войди в её попку в этой позе и люби 1 минуту.");
                tasksExtremeListMale.add("Девушка встает раком, прижав грудь к кровати. Люби ее в киску, при этом ты должен погрузить один пальчик ей в попку.");
                tasksExtremeListMale.add("Девушка ляжет на спину и раздвинет ножки. Люби ее в попку две минуты. Во время секса прижми включенный вибратор к ее клитору, иногда можешь прижимать его и к своему члену, не отрывая от ее киски");
                tasksExtremeListMale.add("Займи удобное положение сидя, она сядет на твой член попкой. Подними ее ноги к верху и имей ее в этой позе пока не выйдет время. В это время она должна ласкать свой клитор.");
                tasksExtremeListMale.add("Войди в её попку сверху и в течение 30 секунд не двигайся, вместо этого нежно целуй ей тело.");
                tasksExtremeListMale.add("Она ляжет на бок, используя много смазки, войди в ее попку и люби, пока не кончишь. В это время она прижмет ко своей киске мощный вибратор, который будет не только ласкать ее клитор, но и передавать вибрации и на твой член, что сделает ощущения еще сильнее.");
                tasksExtremeListMale.add("Девушка встанет на четвереньки, смажь ее попку и свой член, после чего медленно и нежно войди в сокровенную дырочку.");
                tasksExtremeListMale.add("Вставь в ее попку пробку или свой палец, поставь на четвереньки и в этой позе люби в киску пока не выйдет время.");
                tasksExtremeListMale.add("Девушка встанет на четвереньки, прижмет грудь к кровати и положит руки к себе на попку. Войди в ее анальную дырочку, используя много смазки. После чего аккуратно двигайся в течении указанного времени.");
                tasksExtremeListMale.add("Садись на кресло, она сядет на твой член лицом к тебе. Вставь пальчик в ее анальное отверстие и двигайся пока идет время");
                tasksExtremeListMale.add("Девушка становится на четвереньки. Смажб руки массажным маслом. Одной рукой  массируй клитор девушки. Другой рукой осторожно вводи сразу 3 пальца в анус девушки. При этом целует девушку в шею.");
                tasksExtremeListMale.add("Девушка должна лечь на живот, зафиксируй ее руки и ноги за спиной (рука к ноге). После этого делай с ней все что захочешь: займись с ней сексом, поиграй с ее киской руками или игрушками, вставь пальчик или пробку ей в попу. Не торопись, у тебя целый фант пока идет время, после того как ты ее отпустишь!");
            }
            if (TasksAnal & TasksOral){
                tasksExtremeListMale.add("Девушка должна встать на четвереньки лицом к тебе, после чего возьми одной рукой её за волосы и заставь сосать свой член, а другой рукой играйся с дырочкой в её попке.");
                tasksExtremeListMale.add("Девушка встает на четвереньки, войди в её киску сзади и неглубоко вставь свой палец её в попку. Действуй, пока не сработает таймер.");
                tasksExtremeListMale.add("Садись на пол, а девушка встаёт повернувшись попой к твоему лицу. Далее девушка мастурбирует себе, насаживаясь попой на твой язычок. Задача парня работать язычком в тёплой попке девушки пока идёт таймер.");
                tasksExtremeListMale.add("Садись на кровать, девушка ложится сбоку на живот и начинает делать тебе минет. В это время увлажнb свой пальчик и вводи его в женскую попку. Девушка должна делать минет с такой же скоростью, с какой ты ласкаешь ее попку.");
                tasksExtremeListMale.add("Ложись на спину, девушка располагается у твоего лица таким образом, чтобы язык доставал до ее попки и начинает мастурбировать. В это время ты должен лизать ее попку, одновременно мастурбируя свой член.");
                tasksExtremeListMale.add("девушка ложится на спину. Подними руками попу девушки так, чтобы она была выше головы. Далее рукой ласкай клитор девушки, а языком анус партнерши. выполняем пока не закончится время.");
                tasksExtremeListMale.add("Девушка ложиться на спину и поднимает ножки. Вводи один пальчик ей во влагалище, а второй в попу и двигай ими туда-обратно, не забывай про ласки клитора языком");
                tasksExtremeListMale.add("Сейчас играете в ролевую игру. Парень на приеме у девушки уролога. Парень садится в кресло для обследования. Девушка если есть надевает белый халатик или рубашку и говорит : \"мне нужно обследовать вашу простату на предмет простатита. пожалуйста пошире расставьте ноги и предоставьте врачу вашу попу для обследования\" . Далее девушка вводит пальцы в анус парня и нащупывает простату. Далее врач продолжает :\"Чтобы простата набухла и стала как грецкий орех я помогу вам возбудиться\". Далее девушка нащупыват простату делая миньет парню.");
                tasksExtremeListMale.add("Люби ее языком в попу в течении 2 минут. Вводи свой язычок как можно глубже.");
                tasksExtremeListMale.add("Сядь на пол, оперевшись спиной на кровать. Она подойдет к тебе, перекинет одну ногу за твое плече и поставит на кровать. После этого делай ей кунилингус 2 минуты. В процессе ласкай ее попку, если она позволит");
            }
            // Экстрим задания Женщине
            tasksExtremeListWoman.add("Говори грязно с партнером, пока он глубоко входит в вас в течении времени по таймеру");
            tasksExtremeListWoman.add("Твоя киска уже мокрая? Сядь на него так, чтобы прижать член к его животу и начни \"скользить\" своей влажной дырочкой по его члену. Вконце можешь впустить его в себя");
            tasksExtremeListWoman.add("Ложись на живот. Он будет любить тебя сзади, лаская пальцами клитор, пока не сработает таймер.");
            tasksExtremeListWoman.add("Вы должны встать у стены, обхвати мужчину за шею и закинь одну ножку ему на руки, тем самым открыв доступ к своей киске. Он войдет в неё и в течение таймера будет нежно двигаться в неё.");
            tasksExtremeListWoman.add("Встань над парнем так, чтобы твои ножки были широко раздвинуты. Возьми его член в руку и поводи им по своей киске и клитору. Потом введи его в себя и вытащи. Повтори 5 раз");
            tasksExtremeListWoman.add("Напарник станет твоим рабом. У вас есть время по таймеру, чтобы схватить любые предметы и артефакты пыток: кнут, ремень, веревку, поводок, колышек. Делай с ним всё что захочешь, пока не достигнешь оргазма. Только тогда ты сможешь его освободить, или нет...");
            tasksExtremeListWoman.add("Нагнись вперед, мужчина подстраивается сзади тебя и начинает любить в киску. Для удобства ты можешь упереться руками в стену или пол.");
            tasksExtremeListWoman.add("Встань рачком на стол и упрись на локти, отведи ягодицы назад к пяткам, чтобы они слегка выходили за пределы стола. Он должен войти в тебя и двигаться пока не закончится время");
            tasksExtremeListWoman.add("Партнер садится спиной к спинке кровати и разводит ноги. Сядь к нему лицом максимально близко в такую же позу, обхватив его талию ногами, а руками держась за спинку кровати. Дальше вы сразу поймете что делать");
            tasksExtremeListWoman.add("Выставь свою попку назад, оперевшись на стол руками. Мужчина сзади входит в твою киску и любит в этой позе пока не закончится время.");
            tasksExtremeListWoman.add("Встань раком, он войдет в твою киску и не будет двигаться. Двигаться должна ты!");
            tasksExtremeListWoman.add("Он должен удобно расположиться сидя, садись на его член, повернувшись спиной к нему. Двигайся так пока не выйдет время, выбирая скорость и глубину проникновения сама.");
            tasksExtremeListWoman.add("Встань на пол, закинь свою ногу на стол. Мужчина входит в тебя стоя и медленно двигается с максимальной амплитудой. В процессе девушка может ласкать себя пальчиком.");
            tasksExtremeListWoman.add("Сядь на его член сверху и двигайся на нем. Он прижмет свою руку к твоему животу, а большим пальцем будет ласкать твой клитор.");
            tasksExtremeListWoman.add("Он ляжет на спину. Сядь на его член киской таким образом, чтобы твои ножки были у его лица. В этой позе он будет тебя любить пока идет время.");
            tasksExtremeListWoman.add("Ляг на живот и раздвинь ножки, слегка приподняв таз. Он войдет в твою киску, но не будет двигаться. Все движения должна совершать ты!");
            tasksExtremeListWoman.add("Встань на четвереньки, мужчина войдет в тебя сзади и будет с максимальной амплитудой и силой любить в киску пока не кончится время.");
            tasksExtremeListWoman.add("Оседлай его член сверху, двигай своим тазом, чтобы достичь максимальной амплитуды.");
            tasksExtremeListWoman.add("Ляг животом на край кровати и прогни спинку, чтобы ноги стояли на полу. Он войдет в твою киску сзади и будет двигаться одну минуту. Для удобства можешь согнуть колени и подложить подушку под живот.");
            tasksExtremeListWoman.add("Он сядет на край кровати, поставив ноги на пол. Сядь на его член, повернувшись к нему спиной и в течение таймера прыгай на нем. Начни медленно и постепенно ускоряйся.");
            tasksExtremeListWoman.add("Ляг на спину и раздвинь ножки, он будет страстно любить тебя в киску, одновременно лаская твои груди и тело.");
            tasksExtremeListWoman.add("Ляг на спину, раздвинь и подними ножки, чтобы твоя киска была раскрыта. Он встанет перед тобой на колени, войдет в тебя и будет совершать движения, проникая на всю глубину члена в течение отведенного времени.");
            tasksExtremeListWoman.add("Сядь на стол или любой другой подходящий предмет мебели и раздвинь ножки, он войдет в твою киску и будет совершать движения пока не закончится время.");
            tasksExtremeListWoman.add("Пришло время необычной позы наездницы. Парень садится на кровать, оперевшись руками сзади себя, девушка встает на колени лицом к нему таким образом, чтобы член вошел в ее киску. После этого влюбленные могут начинать двигаться, при этом у парня появляется отличная возможность ласкать ее грудь с помощью рта.");
            tasksExtremeListWoman.add("Парень занимает удобную позу сидя перед монитором, включи порно, которое понравится парню и перематай на горячий момент, после чего сядь киской на его член и начинай ритмично двигаться.");
            tasksExtremeListWoman.add("Молодой человек ляжет на спину. Сядь над ним на корточки, чтобы член вошел в твою киску. После этого \"вприсядку\" прыгай на нем, удерживая свой вес на себе.");
            tasksExtremeListWoman.add("Нагнись животом на стол(или что-то подходящие по высоте), он подойдет сзади и будет любить твою киску пока не закончится время.");
            tasksExtremeListWoman.add("Сядь сверху на его член, после чего, вприсядку, с максимально возможной амплитудой(чтобы член входил в тебя на всю глубину и выходил практически полностью), медленно двигайся.");
            tasksExtremeListWoman.add("У большинства мужчин есть фантазия, в которой они рвут колготки на девушке, стягивают трусики и страстно имеют ее в позе \"раком\". Исполни это для своего любимого. Надень ненужные колготки (можешь без трусиков) и позволь ему сделать это.");
            tasksExtremeListWoman.add("Твой парень сядет на кресло, ты сядешь на его член к нему лицом. Двигайся минуту.");
            tasksExtremeListWoman.add("Твой парень должен сесть на диван и облокотиться на спинку. Сядь на корточки над его членом и аккуратно введи его в свою киску, после этого медленно \"приседай\", чтобы член входил в тебя поглубже. Чтобы тебе легче было держать равновесие возьми своего парня за шею.");
            tasksExtremeListWoman.add("Встань на четвереньки, он войдет в твою киску и не будет двигаться. Двигаться должна ты. Он будет гладить твое анальное отверстие большими пальцами, но не будет в него проникать.");
            tasksExtremeListWoman.add("Ложись на стол и раздвинь ножки. Он встанет между них и войдет в тебя. После этого возьмёт за бедра и будет активно двигаться.");
            tasksExtremeListWoman.add("Мужчина удобно сидит на кровати или кресле. Подойди к нему, повернись к нему спиной и сядь киской на его член. После этого в течение отведенного времени ублажай его, прыгая на члене.");
            tasksExtremeListWoman.add("Ляг на край кровати и раздвинь ножки, поставив их на пол. Он встанет между них и будет любить тебя в этой позе указанное время");
            tasksExtremeListWoman.add("Ложись на живот. Он будет любить тебя сзади, лаская пальцами клитор, пока не сработает таймер.");
            tasksExtremeListWoman.add("Он сядет на край кровати. Ты садишься сверху на его член, широко раздвинув ножки. В этой позе, ты скачаешь на его члене, пока не закончится время.");
            tasksExtremeListWoman.add("Необычная поза наездницы. Он садится и упирается руками назад. Ты садишься на его член под этим углом и тоже удерживаешь часть веса на своих руках, откинувшись назад.");
            tasksExtremeListWoman.add("Встань перед стенкой, облокотись руками на стену и прогни спинку, подставив ему свою киску. Он подойдет сзади и будет любить тебя сколько пожелает");
            tasksExtremeListWoman.add("Поставь ножку на любую возвышенность, он войдет сзади в твою киску и будет трахать одну минуту.");
            tasksExtremeListWoman.add("Парень садится на край кровати широко расставив свои ноги. Встань на пол на четвереньки и начни сама двигаться взад и вперед перед ним. При этом парень в тебя входит и больше не двигается.");
            tasksExtremeListWoman.add("Он ляжет спиной на край кровати, чтобы его ноги, согнутые в коленях, стояли на полу. Встань над его членом, чтобы он оказался под твоей киской и присядь на него. Теперь ты должна доставить ему и себе удовольствие, при этом полностью не садись на него, а удерживай свой вес на ногах. Для удобства можешь упереться коленками в кровать.");
            tasksExtremeListWoman.add("Ложись на спину и раздвинь ножки, он встанет на колени между твоих ног и войдет в твою киску. Теперь он возьмет тебя за руки и будет любить пока не закончится время. Держание за руки сделает секс еще более интимным и романтичным занятием.");
            tasksExtremeListWoman.add("Ты не должна сопротивляться. Он свяжет тебя с раздвинутыми ногами и будет шлепать твою киску, единственное, что ты можешь - это просить уменьшить или увеличить силу шлепков.");
            tasksExtremeListWoman.add("Прижми к разгоряченной головке его члена вибратор, чтобы доставить ему удовольствие.");
            tasksExtremeListWoman.add("Он ляжет на спину. Сядь на его член, повернувшись к нему спиной. После чего откинься назад, поставив руки ему на грудь. Он подопрет тебя под попу руками и будет подкидывать вверх на своем члене.");
            tasksExtremeListWoman.add("Сядь сзади своего парня и обхвати его руками и ногами. Твои ступни должны оказаться у его яичек. После этого мастурбируй его член, пока он не кончит!");
            tasksExtremeListWoman.add("Ляг и раздвинь ножки, парень будет любить тебя в киску. Когда он будет близок к оргазму, он выйдет из тебя, продолжи мастурбировать его член, пока он не кончит тебе на животик. Если Вы не хотите окончания на этом задание, то занимайтесь любовью, пока не сработает таймер и переходите к следующему заданию.");
            tasksExtremeListWoman.add("Он встанет, возьми включенный вибратор и прижми его к члену своего мужчины снизу. Можешь довести его до оргазма или \"подразнить\" и продолжить игру.");
            tasksExtremeListWoman.add("Раздвинь ножки. Он возьмет вибратор и в течение отведенного времени будет ласкать им твою киску. Твоя задача - продержаться и не кончить. Если это случится, он может не останавливаться и продолжать!");
            tasksExtremeListWoman.add("Сядь на него в простой позе наездницы и двигайся, пока не кончишь на его члене.");
            tasksExtremeListWoman.add("Сядь верхом на его член и играй со своим клитором. Вы не должны двигаться.");
            tasksExtremeListWoman.add("Он ляжет на спину, сядь на его член сверху, повернувшись к его лицу спиной. После этого откинься назад, чтобы полностью лечь на него. Он возьмет тебя за шею и будет мастурбировать твой клитор, не вынимая член из киски, пока ты не кончишь.");

            if (TasksOral){
                tasksExtremeListWoman.add("Делай ему минет, когда он будет близок к тому, чтобы кончить, он достанет член, после этого ты только язычком должна довести его до оргазма.");
                tasksExtremeListWoman.add("Мастурбируй его член, пока он не кончит. После этого оближи его головку язычком.");
                tasksExtremeListWoman.add("Доведи его до оргазма своим ртом! Когда он будет готов кончить, ласкай его член только своими губами и язычком");
                tasksExtremeListWoman.add("Ложись вдоль кровати, парень подойдет к тебе на уровне груди, после этого возьми член в руку и мастурбируй, пока он не кончит или не сработает таймер. Можешь \"помочь\" себе ртом.");
                tasksExtremeListWoman.add("Встань перед парнем на колени, пусть он мастурбирует на твою грудь и кончит на них, ты должна размазать сперму по своему телу.");
                tasksExtremeListWoman.add("Пусть он свяжет твои руки за спиной и прижмет к стене(спинке дивана) и трахнет в рот");
                tasksExtremeListWoman.add("Пришло время показать, насколько глубоко ты можешь взять его член в ротик. Делай минет так глубоко, как только можешь.");
                tasksExtremeListWoman.add("Ложись на спине делай миньет парню. При этом, твоя задача довести себя до оргазма путем мастурбации клитора. В момент ТВОЕГО оргазма его член должен находиться у тебя во рту.");
                tasksExtremeListWoman.add("Делай партнеру минет, играясь с его яичками, до тех пор, пока он не кончит тебе в рот.");
                tasksExtremeListWoman.add("Возьми в рот его яички, посасывай и облизывай их. При этом мастурбируй его член, чтобы доставить ему максимальное удовольствие.");
                tasksExtremeListWoman.add("Это задание позволит подкачать твою попку! Парень должен лечь на пол. Лучше всего будет место у кровати. Присядь над его лицом так, чтобы его язык доставал твоей киски. Теперь ты должна привставать и приседать с небольшой амплитудой, чтобы в верхней точке его язык не доставал до твоей киски, а в нижней плотно прижимался к ней. Чтобы случайно не упасть на него, упрись руками об кровать.");
                tasksExtremeListWoman.add("Ложись на спину, широко раздвигая ножки и начинай ласкать себе клитор, в это же время мужчина ложится на живот лицом к твоей промежности и наблюдает за происходящим, как только ты начинаешь кончать, убери свои руки и парень накрывает разгоряченную поверхность вульвы своим влажным языком. Парень не должен набрасываться на клитор, потому как в это время он очень чувствителен!");
                tasksExtremeListWoman.add("Мастурбируй парню таким образом, чтобы он кончил на твою грудь. После этого размажь сперму по своей груди.");
                tasksExtremeListWoman.add("Делай одну минуту очень страстный минет, а потом минуту выбирай спокойный темп и лижи его яички, чередуя так несколько раз, пока парень не кончит тебе в ротик или на лицо");
                tasksExtremeListWoman.add("Парень ложится на спину. Сядь киской на его лицо и приложи включенный вибратор к своему клитору.");
                tasksExtremeListWoman.add("Включи порно на телефоне и ляг на спину, он будет делать тебе куниилингус пока не закончится время. Постарайся выбрать такое видео, где будут громкие и возбуждающие стоны.");
                tasksExtremeListWoman.add("Он свяжет тебе руки за спиной и поставит на колени. После этого возьмет за голову и вставит тебе в рот член. Ты должна полностью подчиниться ему, Он может кончить прямо тебе в рот.");
                tasksExtremeListWoman.add("Мастурбируй его член своей грудью, пока он не кончит или не сработает таймер. Можешь помогать себе руками или ртом, но основную работу делай грудью!");
                tasksExtremeListWoman.add("Партнер твой раб, возьмите его за волосы и прикажите вылезать всё что захочешь.");
                tasksExtremeListWoman.add("Соси член партнера пока не почувствуешь каплю спермы во рту. Парень, только не всё сразу!");

            }
            if (TasksAnal){
                tasksExtremeListWoman.add("Возьми вибратор и сядь попкой на его член. Пока он любит тебя в попку, играй вибратором со своей киской и не забывай про клитор.");
                tasksExtremeListWoman.add("Он ляжет на спину. Садись на его член попкой, спиной к нему. Если хочешь, можешь двигаться вверх-вниз. Он не должен шевелиться.");
                tasksExtremeListWoman.add("Он ляжет на спину, сядь сверху попкой на его член. Двигайтесь так пока не закончится время.");
                tasksExtremeListWoman.add("Он лежят на спину. Ты сверху ложишься на него боком, согнув ножки в коленях. В этой позе он входит в твою попку и, двигаясь только тазом, доставляет вам удовольствие.");
                tasksExtremeListWoman.add("Стань похотливой кошечкой или лисичкой. Вставь себе в попку хвостик, встань на четвереньки и впусти его в себя. Когда его член будет в тебе, он не будет двигаться. Двигайся сама. Можешь слегка покрутить попкой, не выпуская член из себя.");
                tasksExtremeListWoman.add("Встань на четвереньки, прижми грудь к простыне, он будет иметь тебя в попку пока не выйдет время");
                tasksExtremeListWoman.add("Подогни ноги под себя и подставь ему свою попку. Свободной рукой играйся со своим клитором.");
                tasksExtremeListWoman.add("Вставь себе в попку анальную пробку и оседлай его член сверху. Пробка сделает твою киску более узкой и это усилит ваши ощущения.");
                tasksExtremeListWoman.add("Ляг на животик, он нежно войдет в твою попку и будет двигаться минуту. Можешь повернуть голову, чтобы он смог тебя целовать.");
                tasksExtremeListWoman.add("Садись попкой на член лежащего на спине парня, повернувшись к нему спиной(в позу обратной наездницы). После этого откинься назад на руки парня и начинай двигать тазом.");
                tasksExtremeListWoman.add("Вставай на колени, опираясь локтями на кровать, парень встает одной ногой на диван и входит в твою попку. В этой позе он будет двигаться пока не кончится время.");
                tasksExtremeListWoman.add("Ляг на живот, повернувшись немного на бок. Он одной рукой будет ласкать твой анус, а пальчиками второй войдет в твою киску. У него есть 2 минуты, чтобы ты испытала оргазм.");
                tasksExtremeListWoman.add("Сядь на его член в позе обратной наездницы. Когда ты войдешь в раж начни ласкать круговыми движениями свой анус или введи в него пальчик.");
                tasksExtremeListWoman.add("А ты знаешь, где у него простата? Попробуй найти. Не забывайте о маникюре и, если надо, используйте смазку");
            }
            if (TasksAnal & TasksOral){
                tasksExtremeListWoman.add("Он ляжет на спину и прижмет ноги к груди. Одной рукой мастурбируй ему член, а 2 пальчика вставь ему в анальное отверстие и совершай характерные движения.");
                tasksExtremeListWoman.add("Твой парень ляжет на спину, встань попкой над его лицом. Он будет ласкать твою попку  пока идет время");
                tasksExtremeListWoman.add("Парень садится на пол, а ты встаёшь повернувшись попой к, его лицу. Далее ты мастурбируешь себе, насаживаясь попой на язычок парня. Задача парня работать язычком в тёплой попке пока ты не кончишь. Если тебе понравилось, то подари ему страстный поцелуй.");
                tasksExtremeListWoman.add("Парень встает на четвереньки или принимает другую позу, в которой тебе будет доступна его попка и член. После этого начинай делать минет и одновременно ласкать его анус, предварительно нанеся туда смазку.");
                tasksExtremeListWoman.add("Он лежит на спине, садись попкой к его лицу, он будет вылизывать твои дырочки пока не кончится время. Свободной рукой можешь ему нежно мастурбировать,");
                tasksExtremeListWoman.add("Повернись к лицу парня попой и нагнувшись разведи булочки попки руками. Парень языком входит в твою попку пока идёт таймер.");
                tasksExtremeListWoman.add("ДЛожись на живот и слегка, сексуально приподними свою попку. В это время парень нежно ласкает тебя руками и одновременно проникает в твой анус своим языком.");
                tasksExtremeListWoman.add("Сядь попкой над его лицом, он должен доставить тебе удовольствие, вылизав обе твои дырочки.");
                tasksExtremeListWoman.add("Парень садится, поднимает одну ногу и удерживает ее руками. Ты смазываешь руки маслом и делаешь миньет парню, одной рукой массируя промежность и зону ануса парня пока парень не кончит тебе в рот.");
                tasksExtremeListWoman.add("Встань своей попкой над его лицом. Он должен тщательно вылизать твои дырочки. Не забывай двигать тазом, чтобы сделать себе еще приятней.");
                tasksExtremeListWoman.add("Сперва облизывай попку мужчины, после чего вводи ему смазанный пальчик в попу и делай минет.");
                tasksExtremeListWoman.add("Он нагнется, чтобы у тебя был доступ к его попе и члену. Подойди взади и язычком води по его анусу, а руками дрочи его член.");
                tasksExtremeListWoman.add("Парень должен лечь на спину и прижать ноги к груди. Сядь перед его попой и вылижи ее. Попробуй чередовать введение кончика языка в анус и облизывание его всем языком.");
                tasksExtremeListWoman.add("Парень ложится на спину и поднимает ноги к груди, чтобы его таз был оторвал от кровати. Ложись на живот перед его попой и начинай лизать его анальное отверстие, одновременно мастурбируя член рукой.");
                tasksExtremeListWoman.add("Парень ложится на спину. Смажь пальцы маслом и осторожно вводи сначала 1, потом 2, а потом 3 пальца в анус парня при этом делая ему миньет.");
            }
            // Экстрим задание Общее
            tasksExtremeListAssignment.add("Погуглите порно начинающееся на первые 3 буквы вашего имени. Воспроизведите его и повторите любую сцену");
            tasksExtremeListAssignment.add("Встаньте, посмотрите друг на друга. Партнер поднимет девушку, а она обнимет его за шею, закинув ноги вокруг его талии. Парень прижмет девушку к стене и трахнет её");
            tasksExtremeListAssignment.add("Сядьте друг напротив друга раздвинув ноги и поиграйте своими гениталиями");
            tasksExtremeListAssignment.add("Найди что-нибудь, чтобы связать своего партнера, как захочешь. И делай со своим заложником всё что захочешь впока идет время");
            tasksExtremeListAssignment.add("Парень ложится на спину, девушка садится попкой ему на бедра, чтобы ее киска была около его члена и откидывается назад. После этого каждый мастурбирует себе пока идет таймер");
            tasksExtremeListAssignment.add("Займитесь сексом у зеркала.");
            tasksExtremeListAssignment.add("Попробуйте позу наездницы, при которой вы опираетесь руками назад, это позволит вам обоим двигаться в одном ритме.");


            if (TasksOral){

            }
            if (TasksAnal){
                tasksExtremeListAssignment.add("В ее попке должна быть пробка или твой палец. Занимайтесь классическим сексом в течение 2 минут.");
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
                            textName.setText(Name1);
                        } else {
                            textName.setText(Name2);
                        }
                        textTimer.setText(getResult(360 - (degree % 360)));
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

        int factor_x = 0;
        int factor_y = 2;
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