package org.example.ibmskillsbuildapp.service;

import org.example.ibmskillsbuildapp.model.Question;
import org.example.ibmskillsbuildapp.model.QuestionForm;
import org.example.ibmskillsbuildapp.model.Result;
import org.example.ibmskillsbuildapp.repo.QuestionRepo;
import org.example.ibmskillsbuildapp.repo.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private ResultRepo resultRepo;

    public QuestionForm getQuestions() {
        List<Question> allQuestions = questionRepo.findAll();
        List<Question> questionList = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < 5; i++) {
            int rand = random.nextInt(allQuestions.size());
            questionList.add(allQuestions.get(rand));
            allQuestions.remove(rand);
        }

        QuestionForm questionForm = new QuestionForm();
        questionForm.setQuestions(questionList);

        return questionForm;
    }

    public int getResult(QuestionForm questionForm) {
        int correctAnswers = 0;

        for(Question question: questionForm.getQuestions()) {
            if(question.getAns() == question.getChose()) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    public void saveScore(Result result) {
        resultRepo.save(result);
    }

    public List<Result> getTopScores() {
        return resultRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
    }
}
