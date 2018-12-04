package com.firebaseapp.fasty.fastytest;

import com.firebaseapp.fasty.fastytest.model.Answer;
import com.firebaseapp.fasty.fastytest.model.Career;
import com.firebaseapp.fasty.fastytest.model.Exam;
import com.firebaseapp.fasty.fastytest.model.Question;
import com.firebaseapp.fasty.fastytest.model.Result;
import com.firebaseapp.fasty.fastytest.model.University;

import java.util.ArrayList;

public interface IMainActivity {

    void openExamsFragment(University university);

    void openIndicationsFragment(University university, Exam exam);

    void openTestFragment(University university, Exam exam, Career career);

    void openQuestionFragment(Question question);

    void sendQuestions(ArrayList<Question> questionsArrayList);

    void sendAnswer(Answer answer);

    void reviseTest(University university, Exam exam, Career career);

    void openResultsFragment(Result result);

    void notifyChangeColorItemQuestion(int position);

    Answer getAnswer(int position);

    void openStatisticsFragment(Result result);

    void openPositionsFragment(String pathExam);

}
