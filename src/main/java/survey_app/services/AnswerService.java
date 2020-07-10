package survey_app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import survey_app.models.Answer;
import survey_app.repositories.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;
	
	
	public Iterable<Answer> getAnswers() {
		return answerRepository.findAll();
	}
	
	public Optional<Answer> getById(Long id) {
		return answerRepository.findById(id);
	}
	
	public void addAnswer(Answer answer) {
		answerRepository.save(answer);
	}
	
	public void deleteAnswer(Long id) {
		Optional<Answer> answer = answerRepository.findById(id);
		
		if(answer.isPresent()) {
			answerRepository.delete(answer.get());
		}
	}
	
	public void editAnswer(Long id, Answer answer) {
		Optional<Answer> a = answerRepository.findById(id);
		
		if(a.isPresent()) {
			answer.setId(a.get().getId());
			answerRepository.save(answer);
		}
		
	}
}
