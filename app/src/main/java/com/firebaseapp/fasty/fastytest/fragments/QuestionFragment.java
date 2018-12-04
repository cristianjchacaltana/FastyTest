package com.firebaseapp.fasty.fastytest.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebaseapp.fasty.fastytest.IMainActivity;
import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.activities.MainActivity;
import com.firebaseapp.fasty.fastytest.model.Answer;
import com.firebaseapp.fasty.fastytest.model.Question;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.UUID;


public class QuestionFragment extends Fragment {

    private static final String TAG = "QuestionFragment";

    private static final String ALTERNATIVE_A = "alternativaA";
    private static final String ALTERNATIVE_B = "alternativaB";
    private static final String ALTERNATIVE_C = "alternativaC";
    private static final String ALTERNATIVE_D = "alternativaD";
    private static final String ALTERNATIVE_E = "alternativaE";

    private TextView mQuestionNumberTextView;
    private TextView mQuestionTextView;
    private ImageView mQuestionImageView;
    private RadioGroup mAlternativesRadioGroup;
    private RadioButton mAlternativeARadioButton;
    private RadioButton mAlternativeBRadioButton;
    private RadioButton mAlternativeCRadioButton;
    private RadioButton mAlternativeDRadioButton;
    private RadioButton mAlternativeERadioButton;
    private Button mQualifyButton;
    private Button mSkipButton;

    private Question question;
    private Answer answer;
    private IMainActivity mMainActivityInterface;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mMainActivityInterface = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if (bundle != null){
            question = bundle.getParcelable(MainActivity.ARGUMENT_QUESTION);
            answer = bundle.getParcelable(MainActivity.ARGUMENT_ANSWER);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.fragment_question, container, false);

        mQuestionNumberTextView = root.findViewById(R.id.text_question_number);
        mQuestionTextView = root.findViewById(R.id.text_question);
        mQuestionImageView = root.findViewById(R.id.image_question);
        mAlternativesRadioGroup = root.findViewById(R.id.radiogroup_alternatives);
        mAlternativeARadioButton = root.findViewById(R.id.radiobutton_alternative_a);
        mAlternativeBRadioButton = root.findViewById(R.id.radiobutton_alternative_b);
        mAlternativeCRadioButton = root.findViewById(R.id.radiobutton_alternative_c);
        mAlternativeDRadioButton = root.findViewById(R.id.radiobutton_alternative_d);
        mAlternativeERadioButton = root.findViewById(R.id.radiobutton_alternative_e);
        mQualifyButton = root.findViewById(R.id.button_qualify);
        mSkipButton = root.findViewById(R.id.button_skip);

        initData();
        initRadioGroup();
        initLayout();
        implementationListenerButtons();

        return root;
    }


    private void initData(){

        mQuestionNumberTextView.setText( "Pregunta " + question.getUid() );

        if (question.getPreguntaImagen()){
            Glide.with(getContext())
                .load(Uri.parse(question.getPregunta() ))
                .into(mQuestionImageView);
        } else {
            mQuestionTextView.setText( question.getPregunta() );
        }

        mAlternativeARadioButton.setText( question.getAlternativaA() );
        mAlternativeBRadioButton.setText( question.getAlternativaB() );
        mAlternativeCRadioButton.setText( question.getAlternativaC() );
        mAlternativeDRadioButton.setText( question.getAlternativaD() );
        mAlternativeERadioButton.setText( question.getAlternativaE() );

    }


    private void initRadioGroup(){

        mAlternativesRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mQualifyButton.setEnabled(true);
            }
        });

    }


    private void implementationListenerButtons(){

        mQualifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnswer();
            }
        });


        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( answer.getUid()!=null && answer.getRespuesta()!=null ){
                    mMainActivityInterface.sendAnswer(answer);
                } else {
                    Answer answer = new Answer(String.valueOf(question.getUid()), null);
                    mMainActivityInterface.sendAnswer(answer);
                }

            }
        });

    }


    private void initLayout(){

        if ( answer.getUid()!=null && answer.getRespuesta()!=null ){

            switch (answer.getRespuesta()){
                case "alternativaA":
                    mAlternativeARadioButton.setChecked(true);
                    break;

                case "alternativaB":
                    mAlternativeBRadioButton.setChecked(true);
                    break;

                case "alternativaC":
                    mAlternativeCRadioButton.setChecked(true);
                    break;

                case "alternativaD":
                    mAlternativeDRadioButton.setChecked(true);
                    break;

                case "alternativaE":
                    mAlternativeERadioButton.setChecked(true);
                    break;

            }

        } else {
            mAlternativeARadioButton.setChecked(false);
            mAlternativeBRadioButton.setChecked(false);
            mAlternativeCRadioButton.setChecked(false);
            mAlternativeDRadioButton.setChecked(false);
            mAlternativeERadioButton.setChecked(false);
        }

    }



    private void sendAnswer(){

        int checkedId = mAlternativesRadioGroup.getCheckedRadioButtonId();

        switch (checkedId){
            case R.id.radiobutton_alternative_a:
                Answer answerA = new Answer(String.valueOf(question.getUid()), ALTERNATIVE_A);
                mMainActivityInterface.sendAnswer(answerA);
                break;

            case R.id.radiobutton_alternative_b:
                Answer answerB = new Answer(String.valueOf(question.getUid()), ALTERNATIVE_B);
                mMainActivityInterface.sendAnswer(answerB);
                break;

            case R.id.radiobutton_alternative_c:
                Answer answerC = new Answer(String.valueOf(question.getUid()), ALTERNATIVE_C);
                mMainActivityInterface.sendAnswer(answerC);
                break;

            case R.id.radiobutton_alternative_d:
                Answer answerD = new Answer(String.valueOf(question.getUid()), ALTERNATIVE_D);
                mMainActivityInterface.sendAnswer(answerD);
                break;

            case R.id.radiobutton_alternative_e:
                Answer answerE = new Answer(String.valueOf(question.getUid()), ALTERNATIVE_E);
                mMainActivityInterface.sendAnswer(answerE);
                break;
        }


        mMainActivityInterface.notifyChangeColorItemQuestion(question.getUid() - 1);

    }


}
