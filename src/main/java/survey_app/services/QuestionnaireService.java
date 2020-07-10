package survey_app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import survey_app.models.Question;
import survey_app.models.Questionnaire;
import survey_app.repositories.QuestionnaireRepository;

@Service
public class QuestionnaireService {
	
	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	
	
	public Iterable<Questionnaire> getQuestionnaires() {
		return questionnaireRepository.findAll();
	}
	
	public Optional<Questionnaire> getById(Long id) {
		return questionnaireRepository.findById(id);
	}
	
	public void addQuestionnaire(Questionnaire questionnaire) {
		questionnaireRepository.save(questionnaire);
	}
	
	public void deleteQuestionnaire(Long id) {
		Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
		if(questionnaire.isPresent()) {
			questionnaireRepository.delete(questionnaire.get());
			
		}
	}
	
	public void editQuestionnaire(Long id, Questionnaire questionnaire) {
		Optional<Questionnaire> q = questionnaireRepository.findById(id);
		
		if(q.isPresent()) {
			questionnaire.setId(q.get().getId());
			questionnaireRepository.save(questionnaire);
		}
	}
	
	public Iterable<Questionnaire> getByTemplateTrue() {
		return questionnaireRepository.findByIsTemplateTrue();
	}
	
	public Iterable<Questionnaire> getByTemplateFalse() {
		return questionnaireRepository.findByIsTemplateFalse();
	}
	
	public Long getLastQuestionnaireId() {
		return questionnaireRepository.getLastQuestionnaireId();
	}
	
	public void AddQuestionsFromTemplate(Questionnaire questionnaire) {
		for (Question q : questionnaire.getQuestion()) {
    		Question newQuestion = new Question();
    		newQuestion.setName(q.getName());
    		newQuestion.setQuestionnaire((getById(getLastQuestionnaireId())).get());
    	}
	}
	
		
		
	

}

