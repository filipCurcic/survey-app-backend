package survey_app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import survey_app.dto.QuestionDto;
import survey_app.models.Question;
import survey_app.services.QuestionService;
import survey_app.services.QuestionnaireService;
import survey_app.utils.View.HideOptionalProperties;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<QuestionDto>> getQuestions() {
    	List<QuestionDto> result = new ArrayList<QuestionDto>();
    	for(Question q : questionService.getQuestions()) {
    		result.add(new QuestionDto(q));
    	}
        return new ResponseEntity<Iterable<QuestionDto>>(result, HttpStatus.OK);
    }
	
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<QuestionDto> getById(@PathVariable Long id) {
        Optional<Question> question = questionService.getById(id);
        if(question.isPresent()) {
        	QuestionDto result = new QuestionDto();
        	Question q = question.get();
        	result = new QuestionDto(q);
            return new ResponseEntity<QuestionDto>(result, HttpStatus.OK);
        }
        return new ResponseEntity<QuestionDto>(HttpStatus.NOT_FOUND);
    }
    
    @JsonView(HideOptionalProperties.class)
   	@RequestMapping(value="/all/{id}", method=RequestMethod.GET)
       public ResponseEntity<Iterable<Optional<Question>>> getQuestion(@PathVariable Long id) {
           return new ResponseEntity<Iterable<Optional<Question>>>(questionService.f(id), HttpStatus.OK);
       }

	
	@RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
    	questionService.addQuestion(question);
        return new ResponseEntity<Question>(question, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/add_template", method=RequestMethod.POST)
    public ResponseEntity<Question> addQuestionForTemplate(@RequestBody Question question) {
		question.getQuestionnaire().setId(questionnaireService.getLastQuestionnaireId());
    	questionService.addQuestion(question);
        return new ResponseEntity<Question>(question, HttpStatus.CREATED);
    }

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
    	questionService.editQuestion(id, question);
        return new ResponseEntity<Question>(question, HttpStatus.CREATED);
    }

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Question> removeQuestion(@PathVariable Long id) {
        try {
        	questionService.deleteQuestion(id);
        }catch (Exception e) {
            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
    }

}