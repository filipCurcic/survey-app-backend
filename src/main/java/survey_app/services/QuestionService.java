package survey_app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import survey_app.models.Question;
import survey_app.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	
	public Iterable<Question> getQuestions() {
		return questionRepository.findAll();
	}
	
	public Optional<Question> getById(Long id) {
		return questionRepository.findById(id);
	}
	
	@Transactional
	public void addQuestion(Question question) {
		questionRepository.save(question);
	}
	
	public void deleteQuestion(Long id) {
		Optional<Question> question = questionRepository.findById(id);
		
		if(question.isPresent()) {
			questionRepository.delete(question.get());
		}
	}
	
	public void editQuestion(Long id, Question question) {
		Optional<Question> q = questionRepository.findById(id);
		
		if(q.isPresent()) {
			question.setId(q.get().getId());
			questionRepository.save(question);
		}
		
	}
	
	public Long getLastQuestionId() {
		return questionRepository.getLastQuestionId();
	}
	
	public Iterable<Optional<Question>> f (Long id) {
		return questionRepository.findQuestionByQuestionnaireId(id);
	}
}