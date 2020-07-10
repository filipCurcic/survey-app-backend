package survey_app.controllers;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import survey_app.dto.QuestionnaireDto;
import survey_app.models.Answer;
import survey_app.models.Question;
import survey_app.models.Questionnaire;
import survey_app.services.AnswerService;
import survey_app.services.QuestionService;
import survey_app.services.QuestionnaireService;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<QuestionnaireDto>> getQuestionnaires() {
		List<QuestionnaireDto> result = new ArrayList<QuestionnaireDto>();
		for(Questionnaire q : questionnaireService.getByTemplateFalse()) {
			result.add(new QuestionnaireDto(q));
		}
        return new ResponseEntity<Iterable<QuestionnaireDto>>(result, HttpStatus.OK);
    }
	
	
	
	@RequestMapping(value="/templates", method=RequestMethod.GET)
    public ResponseEntity<Iterable<QuestionnaireDto>> getTemplates() {
		List<QuestionnaireDto> result = new ArrayList<QuestionnaireDto>();
		for(Questionnaire q : questionnaireService.getByTemplateTrue()) {
			result.add(new QuestionnaireDto(q));
		}
        return new ResponseEntity<Iterable<QuestionnaireDto>>(result, HttpStatus.OK);
    }

//	@JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<QuestionnaireDto> getById(@PathVariable Long id) {
        Optional<Questionnaire> questionnaire = questionnaireService.getById(id);
        if(questionnaire.isPresent()) {
        	QuestionnaireDto result = new QuestionnaireDto();
        	Questionnaire q = questionnaire.get();
        	result = new QuestionnaireDto(q);
            return new ResponseEntity<QuestionnaireDto>(result, HttpStatus.OK);
        }
        return new ResponseEntity<QuestionnaireDto>(HttpStatus.NOT_FOUND);
    }
		
	@RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<Questionnaire> addQuestionnaire(@RequestBody Questionnaire questionnaire) {
    	questionnaireService.addQuestionnaire(questionnaire);
        return new ResponseEntity<Questionnaire>(questionnaire, HttpStatus.CREATED);
    }
	
	
	@RequestMapping(value="/add_template", method=RequestMethod.POST)
    public ResponseEntity<Questionnaire> addQuestionnaireFromTemplate(@RequestBody Questionnaire questionnaire) {
		Questionnaire newQ = new Questionnaire();
		newQ.setCreated(new Date());
		newQ.setName(questionnaire.getName());
		newQ.setTemplate(false);
    	questionnaireService.addQuestionnaire(newQ);
    	for (Question q : questionnaire.getQuestion()) {
    		Question newQuestion = new Question();
    		newQuestion.setName(q.getName());
    		newQuestion.setQuestionnaire((questionnaireService.getById(questionnaireService.getLastQuestionnaireId())).get());
    		questionService.addQuestion(newQuestion);
    		for (Answer a : q.getAnswer()) {
    			Answer newAnswer = new Answer();
    			newAnswer.setName(a.getName());
    			newAnswer.setQuestion((questionService.getById(questionService.getLastQuestionId())).get());
    			answerService.addAnswer(newAnswer);
    		}
    	}
        return new ResponseEntity<Questionnaire>(questionnaire, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/copy_questionnaire", method=RequestMethod.POST)
    public ResponseEntity<Questionnaire> copyQuestionnaire(@RequestBody Questionnaire questionnaire) {
		Questionnaire newQ = new Questionnaire();
		newQ.setCreated(new Date());
		newQ.setName(questionnaire.getName() + " (Copy)");
		newQ.setTemplate(false);
    	questionnaireService.addQuestionnaire(newQ);
    	for (Question q : questionnaire.getQuestion()) {
    		Question newQuestion = new Question();
    		newQuestion.setName(q.getName());
    		newQuestion.setQuestionnaire((questionnaireService.getById(questionnaireService.getLastQuestionnaireId())).get());
    		questionService.addQuestion(newQuestion);
    		for (Answer a : q.getAnswer()) {
    			Answer newAnswer = new Answer();
    			newAnswer.setName(a.getName());
    			newAnswer.setQuestion((questionService.getById(questionService.getLastQuestionId())).get());
    			answerService.addAnswer(newAnswer);
    		}
    	}
    	
    	return new ResponseEntity<Questionnaire>(questionnaire, HttpStatus.CREATED);
		
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Questionnaire> updateQuestionnaire(@PathVariable Long id, @RequestBody Questionnaire questionnaire) {
    	questionnaireService.editQuestionnaire(id, questionnaire);
        return new ResponseEntity<Questionnaire>(questionnaire, HttpStatus.CREATED);
    }

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Questionnaire> removeQuestionnaire(@PathVariable Long id) {
        try {
        	questionnaireService.deleteQuestionnaire(id);
        }catch (Exception e) {
            return new ResponseEntity<Questionnaire>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Questionnaire>(HttpStatus.NO_CONTENT);
    }
	
	

	
	
	
		
}