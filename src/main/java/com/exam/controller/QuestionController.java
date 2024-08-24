package com.exam.controller;


import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }
//@PostMapping("/")
//public ResponseEntity<?> addQuestion(@RequestBody Question question) {
//    // Retrieve the quiz from the question
//    Quiz quiz = question.getQuiz();
//
//    // Check if the quiz is not null and has an ID
//    if (quiz != null && quiz.getQid() != 0) {
//        // Retrieve the quiz from the database using the provided ID
//        Quiz existingQuiz = quizService.getQuiz(quiz.getQid());
//
//        // If the quiz exists, set it in the question
//        if (existingQuiz != null) {
//            question.setQuiz(existingQuiz);
//        } else {
//            // If the quiz doesn't exist, handle this case based on your requirements
//            // You might want to throw an exception, return an error response, or create a new quiz
//            // ...
//
//            // For example, you could throw an exception:
//            throw new IllegalArgumentException("Quiz with ID " + quiz.getQid() + " not found");
//        }
//    }

    // Save the question after handling the quiz association
//    Question savedQuestion = this.questionService.addQuestion(question);
//
//    // Ensure that the question and associated quiz are saved
//    return ResponseEntity.ok(savedQuestion);
//}



    @GetMapping("/")
    public ResponseEntity<?> getQuestions(){
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    @GetMapping("/{quesId}")
    public Question getQuestion(@PathVariable("quesId") Long quesId){
        return this.questionService.getQuestion(quesId);
    }

    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }

    //get all question of quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid){
        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> question = quiz.getQuestion();
        List<Question> list = new ArrayList(question);
        if (list.size()>Integer.parseInt(quiz.getNoOfQuestion())) {

            list = list.subList(0, Integer.parseInt(quiz.getNoOfQuestion() + 1));
        }
        list.forEach((q)->{
            q.setAnswer("");
        });
        Collections.shuffle(list);
       return ResponseEntity.ok(list);
//        Quiz quiz = new Quiz();
//        quiz.setQid(qId);
//        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);
    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid){

        Quiz quiz = new Quiz();
        quiz.setQid(qid);
        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);
    }

    // eval Questions
    @PostMapping("/eval-quiz")
    public  ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
        System.out.println(questions);
       double marksGot = 0;
     int   correctOptions = 0;
      int  attempted = 0;
        for(Question q: questions)
        {
            Question questionByQuestionId = this.questionService.getQuestionByQuestionId(q.getQuesId());
            if (questionByQuestionId.getAnswer().trim().equals(q.getGivenAnswers().trim())){
                //correcr
                correctOptions++;

                double singleMarks = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                marksGot+=singleMarks;
            }
            // Check if the question was attempted (not null and not an empty string)
            if (q.getGivenAnswers() != null && !q.getGivenAnswers().trim().isEmpty()) {
                attempted++;
            }
        }
        Map<Object , Object> map =Map.of("marksGot" , marksGot ,"correctOptions" , correctOptions ,"attempted" ,attempted);

        return ResponseEntity.ok(map);

    }
}
