package org.chandima.quizservice.feign;

import org.chandima.quizservice.model.QuestionWrapper;
import org.chandima.quizservice.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    // Return the List of Question Ids
    @GetMapping("generate")
    ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numQ);

    // get questions
    @PostMapping("get")
    ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsIds);

    // get Score
    @PostMapping("get/score")
    ResponseEntity<Integer> getScore(@RequestBody List<UserResponse> responses);


}
