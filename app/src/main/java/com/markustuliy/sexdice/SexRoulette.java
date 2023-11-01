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
            tasksVeryLiteListMale.add("Целуй, ласкай губами и языком её шею.");
            tasksVeryLiteListMale.add("Целуй, ласкай губами и языком её шею.");
            tasksVeryLiteListMale.add("Целуй, ласкай губами и языком её шею.");



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
            tasksLiteListAssignment.add("Облизывай партнера от уха до ягодиц, одновременно снимая с него штаны, шепните ему на ухо что хотите сделать с ним сегодня, поглаживая гениталии партнера через нижнее бельё");
            tasksLiteListAssignment.add("Сыграйте в ролевую игру, партнер что то украл в магазине а тебе нужно провести досмотр.");
            tasksLiteListAssignment.add("Подойди к партнеру сзади и в течение двух минут ласкай. Разрешено проникать под одежду, но снимать пока ничего нельзя.");


            if (TasksOral){
                tasksLiteListAssignment.add("Займите позу 69 и покусывайте друг друга за внутреннюю часть бедер");
                tasksLiteListAssignment.add("Сними штаны с партнера, если они еще есть и лизни внутреннюю часть бедер");
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
                tasksMediumListMale.add("Время для позы \"69\" - девушка сверху. Она медленно делает мужчине минет, а он \"лижущими\" движениями, проводя языком по всей её киске, ласкает её.\t");
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
            tasksMediumListWoman.add("Среднее Задание Женщине 2: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 3: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 4: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 5: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 6: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 1: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 2: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 3: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 4: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 5: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 6: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 1: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 2: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 3: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 4: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 5: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 6: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 1: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 2: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 3: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 4: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 5: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 6: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 1: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 2: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 3: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 4: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 5: ...");
            tasksMediumListWoman.add("Среднее Задание Женщине 6: ...");
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