package org.chandima.questionservice.controller;


import org.chandima.questionservice.model.Question;
import org.chandima.questionservice.model.QuestionWrapper;
import org.chandima.questionservice.model.UserResponse;
import org.chandima.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("id") String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    // Generate Questions for Quiz
    // Return the List of Question Ids
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numQ){
        return questionService.getQuestionForQuiz(category, numQ);
    }


    // get questions (Question Id)
    @PostMapping("get")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsIds){

        return questionService.getQuestionsFromId(questionsIds);
    }

    // get Score
    @PostMapping("get/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<UserResponse> responses){

        return questionService.getScore(responses);
    }



}
