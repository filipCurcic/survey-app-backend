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

import survey_app.dto.AnswerDto;
import survey_app.models.Answer;
import survey_app.services.AnswerService;
import survey_app.services.QuestionService;

@RestController
@RequestMapping("/answer")
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<AnswerDto>> getAll() {
		List<AnswerDto> result = new ArrayList<AnswerDto>();
		for(Answer a: answerService.getAnswers()) {
			result.add(new AnswerDto(a));
		}
        return new ResponseEntity<Iterable<AnswerDto>>(result , HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<AnswerDto> getById(@PathVariable Long id) {
        Optional<Answer> answer = answerService.getById(id);
        if(answer.isPresent()) {
        	AnswerDto result = new AnswerDto();
    		Answer a = answer.get();
    		result = new AnswerDto(a);
    		
            return new ResponseEntity<AnswerDto>(result, HttpStatus.OK);
        }
        return new ResponseEntity<AnswerDto>(HttpStatus.NOT_FOUND);
    }
		
	@RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<Answer> addAnswer(@RequestBody Answer answer) {
    	answerService.addAnswer(answer);
        return new ResponseEntity<Answer>(answer, HttpStatus.CREATED);
    }
	
	
	@RequestMapping(value="/add_template", method=RequestMethod.POST)
    public ResponseEntity<Answer> addAnswerFromTemplate(@RequestBody Answer answer) {
		answer.getQuestion().setId(questionService.getLastQuestionId());
    	answerService.addAnswer(answer);
        return new ResponseEntity<Answer>(answer, HttpStatus.CREATED);
    }

	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody Answer answer) {
    	answerService.editAnswer(id, answer);
    	System.out.println(answer.getName());
        return new ResponseEntity<Answer>(answer, HttpStatus.CREATED);
    }

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Answer> removeAnswer(@PathVariable Long id) {
        try {
        	answerService.deleteAnswer(id);
        }catch (Exception e) {
            return new ResponseEntity<Answer>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Answer>(HttpStatus.NO_CONTENT);
    }


}