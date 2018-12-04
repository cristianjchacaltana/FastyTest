package com.firebaseapp.fasty.fastytest.activities;

import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebaseapp.fasty.fastytest.IMainActivity;
import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.fragments.ExamsFragment;
import com.firebaseapp.fasty.fastytest.fragments.IndicationsFragment;
import com.firebaseapp.fasty.fastytest.fragments.NotificationsFragment;
import com.firebaseapp.fasty.fastytest.fragments.PositionsFragment;
import com.firebaseapp.fasty.fastytest.fragments.QuestionFragment;
import com.firebaseapp.fasty.fastytest.fragments.ResultsFragment;
import com.firebaseapp.fasty.fastytest.fragments.StatisticsFragment;
import com.firebaseapp.fasty.fastytest.fragments.TestFragment;
import com.firebaseapp.fasty.fastytest.fragments.UniversitiesFragment;
import com.firebaseapp.fasty.fastytest.model.Answer;
import com.firebaseapp.fasty.fastytest.model.Career;
import com.firebaseapp.fasty.fastytest.model.Exam;
import com.firebaseapp.fasty.fastytest.model.FragmentTag;
import com.firebaseapp.fasty.fastytest.model.Position;
import com.firebaseapp.fasty.fastytest.model.Prueba;
import com.firebaseapp.fasty.fastytest.model.Question;
import com.firebaseapp.fasty.fastytest.model.Result;
import com.firebaseapp.fasty.fastytest.model.Score;
import com.firebaseapp.fasty.fastytest.model.University;
import com.google.android.gms.flags.IFlagProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    private static final String TAG = "MainActivity";
    private static final String TAG_UNIVERSITIES = "UniversitiesFragment";
    private static final String TAG_EXAMS = "ExamsFragment";
    private static final String TAG_INDICATIONS = "IndicationsFragment";
    private static final String TAG_TEST = "TestFragment";
    private static final String TAG_QUESTION = "QuestionFragment";
    private static final String TAG_RESULTS = "ResultsFragment";
    private static final String TAG_STATISTICS = "StatisticsFragment";
    private static final String TAG_POSITIONS = "PositionsFragment";
    private static final String TAG_NOTIFICATIONS = "NotificationsFragment";


    public static final String ARGUMENT_EXAM = "ARGUMENT_EXAM";
    public static final String ARGUMENT_UNIVERSITY = "ARGUMENT_UNIVERSITY";
    public static final String ARGUMENT_CAREER = "ARGUMENT_CAREER";
    public static final String ARGUMENT_QUESTION = "ARGUMENT_QUESTION";
    public static final String ARGUMENT_ANSWER = "ARGUMENT_ANSWER";
    public static final String ARGUMENT_RESULT = "ARGUMENT_RESULT";
    public static final String ARGUMENT_PATH_EXAM = "ARGUMENT_PATH_EXAM";

    private Fragment mUniversitiesFragment;
    private Fragment mExamsFragment;
    private Fragment mIndicationsFragment;
    private Fragment mTestFragment;
    private Fragment mQuestionFragment;
    private Fragment mResultsFragment;
    private Fragment mStatisticsFragment;
    private Fragment mPositionsFragment;
    private Fragment mNotificationsFragment;


    private BottomNavigationViewEx mBottomNavigationViewEx;

    private ArrayList<Question> mQuestionsArrayList;
    private ArrayList<Answer> mAnswersArrayList;
    private ArrayList<String> mBackStackArrayList;
    private ArrayList<FragmentTag> mFragmentsArrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationViewEx = findViewById(R.id.bottomnavigationviewex_main);

        mBackStackArrayList = new ArrayList<>();
        mFragmentsArrayList = new ArrayList<>();

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        initBottomNavigationView();
        setFragment(0);
    }




    @Override
    public void onBackPressed() {

        int backStackCount = mBackStackArrayList.size();


        if (backStackCount > 1){

            String topFragment = mBackStackArrayList.get(backStackCount-1);
            String newTopFragment = mBackStackArrayList.get(backStackCount-2);
            setFragmentVisibilites(newTopFragment);
            mBackStackArrayList.remove(topFragment);
        } else {
            super.onBackPressed();
        }

    }





    private void initBottomNavigationView(){
        Log.d(TAG, "initBottomNavigationView: initializing bottom navigation view");

//        eliminamos las animaciones
        mBottomNavigationViewEx.enableAnimation(false);
        mBottomNavigationViewEx.enableShiftingMode(false);
        mBottomNavigationViewEx.enableItemShiftingMode(true);


        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(TAG, "onNavigationItemSelected: selected MenuItem");

                switch (item.getItemId()){

                    case R.id.menu_examenes:
                        setFragment(0);
                        break;

                    case R.id.menu_results:
                        setFragment(1);
                        break;

                    case R.id.menu_notifications:
                        setFragment(2);
                        break;

                    case R.id.menu_profile:
                        setFragment(3);
                        break;

                }

                return true;
            }
        });

    }



    private void showBottomNavigationView(){

        if (mBottomNavigationViewEx != null){
            mBottomNavigationViewEx.setVisibility(View.VISIBLE);
        }

    }

    private void hideBottomNavigationView(){

        if (mBottomNavigationViewEx != null){
            mBottomNavigationViewEx.setVisibility(View.GONE);
        }

    }


    private void setFragment(int position){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        switch (position){

            case 0:
                if (mUniversitiesFragment == null){
                    mUniversitiesFragment = new UniversitiesFragment();
                    fragmentTransaction.add(R.id.framelayout_main, mUniversitiesFragment, TAG_UNIVERSITIES);
                    fragmentTransaction.commit();

                    mBackStackArrayList.add(TAG_UNIVERSITIES);
                    mFragmentsArrayList.add( new FragmentTag(mUniversitiesFragment, TAG_UNIVERSITIES) );
                } else {
                    mBackStackArrayList.remove(TAG_UNIVERSITIES);
                    mBackStackArrayList.add(TAG_UNIVERSITIES);
                }
                setFragmentVisibilites(TAG_UNIVERSITIES);
                break;

            case 1:
                if (mResultsFragment == null){
                    mResultsFragment = new ResultsFragment();
                    fragmentTransaction.add(R.id.framelayout_main, mResultsFragment, TAG_RESULTS);
                    fragmentTransaction.commit();

                    mBackStackArrayList.add(TAG_RESULTS);
                    mFragmentsArrayList.add( new FragmentTag(mResultsFragment, TAG_RESULTS) );
                } else {
                    mBackStackArrayList.remove(TAG_RESULTS);
                    mBackStackArrayList.add(TAG_RESULTS);
                }
                setFragmentVisibilites(TAG_RESULTS);
                break;

            case 2:
                if (mNotificationsFragment == null){
                    mNotificationsFragment = new NotificationsFragment();
                    fragmentTransaction.add(R.id.framelayout_main, mNotificationsFragment, TAG_NOTIFICATIONS);
                    fragmentTransaction.commit();

                    mBackStackArrayList.add(TAG_NOTIFICATIONS);
                    mFragmentsArrayList.add( new FragmentTag(mNotificationsFragment, TAG_NOTIFICATIONS) );
                } else {
                    mBackStackArrayList.remove(TAG_NOTIFICATIONS);
                    mBackStackArrayList.add(TAG_NOTIFICATIONS);
                }
                setFragmentVisibilites(TAG_NOTIFICATIONS);
                break;

        }


    }


    private void setFragmentWithArgument(Fragment fragment, String tag){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.framelayout_main, fragment, tag);
        fragmentTransaction.commit();

        mBackStackArrayList.add(tag);
        mFragmentsArrayList.add(new FragmentTag(fragment, tag));
        setFragmentVisibilites(tag);
        Toast.makeText(this, "tamaño:" + mFragmentsArrayList.size(), Toast.LENGTH_SHORT).show();
    }



    private void setFragmentVisibilites(String tag){


        switch (tag){
            case TAG_UNIVERSITIES:
                showBottomNavigationView();
                break;
            case TAG_RESULTS:
                showBottomNavigationView();
                break;
            case TAG_NOTIFICATIONS:
                showBottomNavigationView();
                break;
            default:
                hideBottomNavigationView();
        }


        for (FragmentTag fragmentTag : mFragmentsArrayList){

            if (fragmentTag.getTag().equals(tag)){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.show(fragmentTag.getFragment());
                fragmentTransaction.commit();
            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(fragmentTag.getFragment());
                fragmentTransaction.commit();
            }

        }

    }




    @Override
    public void openExamsFragment(University university) {

        if (mExamsFragment != null){
            getSupportFragmentManager().beginTransaction().remove(mExamsFragment).commitAllowingStateLoss();

            mBackStackArrayList.remove(TAG_EXAMS);
        }

        mExamsFragment = new ExamsFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARGUMENT_UNIVERSITY, university);
        mExamsFragment.setArguments(args);

        setFragmentWithArgument(mExamsFragment, TAG_EXAMS);

    }



    @Override
    public void openResultsFragment(Result result) {

    }



    @Override
    public void openIndicationsFragment(University university, Exam exam) {


        if (mIndicationsFragment != null){
            getSupportFragmentManager().beginTransaction().remove(mIndicationsFragment).commitAllowingStateLoss();

            mBackStackArrayList.remove(TAG_INDICATIONS);
        }

        mIndicationsFragment = new IndicationsFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARGUMENT_UNIVERSITY, university);
        args.putParcelable(ARGUMENT_EXAM, exam);
        mIndicationsFragment.setArguments(args);

        setFragmentWithArgument(mIndicationsFragment, TAG_INDICATIONS);

    }


    @Override
    public void openTestFragment(University university, Exam exam, Career career) {

        if ( mTestFragment != null ){
            getSupportFragmentManager().beginTransaction().remove(mTestFragment).commitAllowingStateLoss();

            mBackStackArrayList.remove(TAG_TEST);
        }

        mTestFragment = new TestFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARGUMENT_UNIVERSITY, university);
        args.putParcelable(ARGUMENT_EXAM, exam);
        args.putParcelable(ARGUMENT_CAREER, career);
        mTestFragment.setArguments(args);

        setFragmentWithArgument(mTestFragment, TAG_TEST);

    }

    @Override
    public void openQuestionFragment(Question question) {

        if ( mQuestionFragment != null ){
            getSupportFragmentManager().beginTransaction().remove(mQuestionFragment).commitAllowingStateLoss();

            mBackStackArrayList.remove(TAG_QUESTION);
        }

        mQuestionFragment = new QuestionFragment();
        Answer answer;
        Bundle args = new Bundle();


        answer = mAnswersArrayList.get( question.getUid()-1 );
        args.putParcelable(ARGUMENT_QUESTION, question);
        args.putParcelable(ARGUMENT_ANSWER, answer);
        mQuestionFragment.setArguments(args);

        setFragmentWithArgument(mQuestionFragment, TAG_QUESTION);

    }

    @Override
    public void sendQuestions(ArrayList<Question> questionsArrayList) {
            mQuestionsArrayList = questionsArrayList;

            initAnswers();
    }


    private void initAnswers(){

        mAnswersArrayList = new ArrayList<>();

        for (int i=0; i<100; i++){
            Answer answer = new Answer();
            mAnswersArrayList.add(answer);
        }

    }


    @Override
    public void sendAnswer(Answer answer) {

        mAnswersArrayList.set( Integer.parseInt(answer.getUid())-1, answer );

        if (Integer.parseInt(answer.getUid()) == 100){
            mBackStackArrayList.remove(TAG_QUESTION);
            setFragmentVisibilites(TAG_TEST);

        } else {
            Question question = mQuestionsArrayList.get( Integer.parseInt(answer.getUid()) );
            openQuestionFragment(question);
        }

    }



    @Override
    public void reviseTest(University university, Exam exam, Career career) {

        int whiteQuestions = 0;
        int correctQuestions = 0;
        int wrongQuestions = 0;
        final float scoreWhite = 1.25f;
        final int scoreCorrect = 20;
        final float scoreWrong = -1.25f;
        double score = 0;
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference pathExam;
        String keyExam;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        for ( int i=0; i<100; i++ ){

            String answerQuestion = mQuestionsArrayList.get(i).getRespuesta();
            String answerUser = mAnswersArrayList.get(i).getRespuesta();

            if ( answerUser == null ){
                whiteQuestions++;

            } else if ( answerUser.equals(answerQuestion) ){
                correctQuestions++;

            } else {
                wrongQuestions++;

            }

        }


        score = whiteQuestions*scoreWhite + correctQuestions*scoreCorrect + wrongQuestions*scoreWrong;


//        ingreso los resultados del examen
//        String uidUser = UUID.randomUUID().toString();
        pathExam = db.child("exams")
                    .child( FirebaseAuth.getInstance().getCurrentUser().getUid() )
                    .push();

        keyExam = pathExam.getKey();



        Result result = new Result(exam.getSemestre(),
                            career.getEscuela(),
                            score,
                            whiteQuestions,
                            scoreCorrect,
                            wrongQuestions,
                            university.getUid(),
                            null,
                            keyExam);

        pathExam.setValue(result)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "reviseTest: ingreso de datos exitoso");
                        } else {
                            Log.d(TAG, "reviseTest: no se ingresaron los datos");
                        }
                    }
                });

//        ingresamos las respuestas del examen
        db.child("answers")
            .child(keyExam)
            .setValue(mAnswersArrayList);




        determinePosition(keyExam, university, exam, career, score);
        Toast.makeText(this, "blancos: " + whiteQuestions, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "correcto: " + correctQuestions, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "incorrecto: " + wrongQuestions, Toast.LENGTH_SHORT).show();
    }



    private void determinePosition(final String keyExam, final University university, final Exam exam, final Career career, final double score){

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("universidades")
            .document( university.getUid() )
            .collection("examenes")
            .document( exam.getUid() )
            .collection("resultados")
            .whereEqualTo("area", career.getArea())
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    if (task.isSuccessful()){
                        Log.d(TAG, "determinePosition: si se obtuvieron los datos" + task.getResult().size());

                        for (QueryDocumentSnapshot document : task.getResult()){

                            Career career = document.toObject(Career.class);
                            processAndSendPosition(keyExam, university,
                                    exam,
                                    career,
                                    score);

                        }


                    } else {
                        Log.d(TAG, "determinePosition: no se obtuvieron los datos");
                    }

                }
            });

    }


    private void processAndSendPosition(final String keyExam, final University university, final Exam exam, final Career career, final Double score1){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DatabaseReference dbRealtime = FirebaseDatabase.getInstance().getReference();
        final ArrayList<Position> positions = new ArrayList<>();

//        PRUEBA
        final Double score = 576.25;
//        PRUEBA

//        VALIDAR
//        el positions, debe estar arriba como global
//        VALIDAR

        if ( score > career.getPuntajeMaximo()  ){

            Position position = new Position(career.getEscuela(),
                                            "ingreso",
                                            1,
                                            "1"+"/"+Integer.toString(career.getNumeroPostulantes()),
                                            score,
                                            null);
            positions.add(position);


            dbRealtime.child("positions")
                        .child(keyExam)
                        .push()
                        .setValue(position);

        } else {

            db.collection("universidades")
                .document( university.getUid() )
                .collection("examenes")
                .document( exam.getUid() )
                .collection("resultados")
                .document( career.getUid() )
                .collection("puntajes")
                .whereGreaterThanOrEqualTo("puntaje", score)
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            Log.d(TAG, "processPosition: si se obtuvieron los datos" + task.getResult().size() + "escuela" + career.getEscuela().toLowerCase());

                            Score scoreCareer = new Score();
                            String condicion;
                            Position position;


                            for (QueryDocumentSnapshot document : task.getResult()){
                                scoreCareer = document.toObject(Score.class);
                            }


                            if ( scoreCareer.getPuntaje() > score ){


                                if ( career.getNumeroVacantes() == scoreCareer.getMerito() ){
                                    condicion = "no ingreso";
                                } else {
                                    condicion = "ingreso";
                                }

                                position = new Position(career.getUid(),
                                                                condicion,
                                                                scoreCareer.getMerito()+1,
                                                                (scoreCareer.getPosicion()+1)+"/"+career.getNumeroPostulantes(),
                                                                score,
                                                                null);


                                dbRealtime.child("positions")
                                            .child(keyExam)
                                            .push()
                                            .setValue(position);

                            } else {

                                if ( scoreCareer.getPosicion() <= career.getNumeroVacantes() ){
                                    condicion = "ingreso";
                                } else {
                                    condicion = "no ingreso";
                                }

                                position = new Position(career.getUid(),
                                                                condicion,
                                                                scoreCareer.getMerito(),
                                                                scoreCareer.getPosicion()+"/"+career.getNumeroPostulantes(),
                                                                score,
                                                                null);

                                dbRealtime.child("positions")
                                        .child(keyExam)
                                        .push()
                                        .setValue(position);

                            }

                            positions.add(position);
                        } else {
                            Log.d(TAG, "processPosition: no se obtuvieron los datos");
                        }

                    }
                });



        }




    }



    @Override
    public void notifyChangeColorItemQuestion(int position) {
        ( (TestFragment) mTestFragment).changeColorItemQuestion(position);
    }

    @Override
    public Answer getAnswer(int position) {
        return mAnswersArrayList.get(position);
    }


    @Override
    public void openStatisticsFragment(Result result) {

        if (mStatisticsFragment != null){
            getSupportFragmentManager().beginTransaction().remove(mStatisticsFragment).commitAllowingStateLoss();

            mBackStackArrayList.remove(TAG_STATISTICS);
        }

        mStatisticsFragment = new StatisticsFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARGUMENT_RESULT, result);
        mStatisticsFragment.setArguments(args);

        setFragmentWithArgument(mStatisticsFragment, TAG_STATISTICS);

    }

    @Override
    public void openPositionsFragment(String pathExam) {

        if (mPositionsFragment != null){
            getSupportFragmentManager().beginTransaction().remove(mPositionsFragment).commitAllowingStateLoss();

            mBackStackArrayList.remove(TAG_POSITIONS);
        }

        mPositionsFragment = new PositionsFragment();
        Bundle args = new Bundle();

        args.putString(ARGUMENT_PATH_EXAM, pathExam);
        mPositionsFragment.setArguments(args);

        setFragmentWithArgument(mPositionsFragment, TAG_POSITIONS);
        
    }



    /*
        PENDIENTE
        ERROR, falta que cada vez q entre a un fragment q tenga q remover
        tb tengo q remover de FragmentTag (pq sigue ingresando)
        en los OPEN ...
     */
}
