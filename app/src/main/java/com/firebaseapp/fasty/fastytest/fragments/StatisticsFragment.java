package com.firebaseapp.fasty.fastytest.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.firebaseapp.fasty.fastytest.IMainActivity;
import com.firebaseapp.fasty.fastytest.R;
import com.firebaseapp.fasty.fastytest.activities.MainActivity;
import com.firebaseapp.fasty.fastytest.model.Result;

import org.w3c.dom.Text;


public class StatisticsFragment extends Fragment {

    private static final String TAG = "StatisticsFragment";

    private TextView mCareerTextView;
    private TextView mScoreTextView;
    private TextView mBlankAnswersTextView;
    private TextView mCorrectAnswersTextView;
    private TextView mWrongAnswersTextView;
    private Button mStatisticsButton;

    private Result result;
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
            result = bundle.getParcelable(MainActivity.ARGUMENT_RESULT);
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root;
        root = inflater.inflate(R.layout.fragment_statistics, container, false);

        mCareerTextView = root.findViewById(R.id.text_statistics_career);
        mScoreTextView = root.findViewById(R.id.text_statistics_score);
        mBlankAnswersTextView = root.findViewById(R.id.text_statistics_blank_answers);
        mCorrectAnswersTextView = root.findViewById(R.id.text_statistics_correct_answers);
        mWrongAnswersTextView = root.findViewById(R.id.text_statistics_wrong_answers);
        mStatisticsButton = root.findViewById(R.id.button_positions);

        initData();
        implementationListenerButtons();

        return root;
    }



    private void initData(){

        mCareerTextView.setText( result.getCarrera() );
        mScoreTextView.setText( String.valueOf(result.getPuntaje()) );
        mBlankAnswersTextView.setText( String.valueOf(result.getRespuestasBlanco()) );
        mCorrectAnswersTextView.setText( String.valueOf(result.getRespuestasCorrectas()) );
        mWrongAnswersTextView.setText( String.valueOf(result.getRespuestasIncorrectas()) );

    }


    private void implementationListenerButtons(){

        mStatisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMainActivityInterface.openPositionsFragment( result.getUid() );

            }
        });

    }




}
