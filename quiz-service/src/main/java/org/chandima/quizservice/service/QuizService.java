package org.chandima.quizservice.service;

import org.chandima.quizservice.model.Question;
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
import java.util.Optional;


@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        Quiz quiz = new Quiz();

//        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category,numQ);
//
//        quiz.setTitle(title);
//        quiz.setQuestions(questions);
//        quizRepo.save(quiz);

        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int quizId) {

            Optional<Quiz> quiz = quizRepo.findById(quizId);
            List<Question> questionsFromDb = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();

            for(Question q : questionsFromDb){
                QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());

                questionsForUser.add(questionWrapper);
            }

            return new ResponseEntity<>(questionsForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<UserResponse> userResponses) {

        int i=0,marks = 0;
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questions =  quiz.getQuestions();
        for(UserResponse response : userResponses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                marks++;
            }
            i++;
        }

        return new ResponseEntity<>(marks,HttpStatus.OK);
    }
}
