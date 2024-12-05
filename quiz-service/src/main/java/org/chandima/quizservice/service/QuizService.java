package org.chandima.quizservice.service;

import org.chandima.quizservice.feign.QuizInterface;
import org.chandima.quizservice.model.QuestionWrapper;
import org.chandima.quizservice.model.Quiz;
import org.chandima.quizservice.model.UserResponse;
import org.chandima.quizservice.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

          // method for interact with question-service
          List<Integer> questionsIDs = quizInterface.getQuestionForQuiz(category,numQ).getBody();

          // create quiz
          Quiz quiz = new Quiz();
          quiz.setTitle(title);
          quiz.setQuestionsIds(questionsIDs);
          quizRepo.save(quiz);

        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }


    // Get all Questions for particular Quiz
    public ResponseEntity<List<QuestionWrapper>> getQuiz(int quizId) {

        // get the Quiz by particular Id.
        Quiz quiz = quizRepo.findById(quizId).get();

        List<Integer> questionIds = quiz.getQuestionsIds();

        // method for interact with question-service

        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        //List<QuestionWrapper> questions = quizInterface.getQuestionsFromId(questionIds).getBody();

        //return new ResponseEntity<>(questions, HttpStatus.CREATED);
        return questions;

    }


    // calculate the Quiz Score
    public ResponseEntity<Integer> getScore(Integer id, List<UserResponse> userResponses) {

        ResponseEntity<Integer> score = quizInterface.getScore(userResponses);

        return score;
    }
}
