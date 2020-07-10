package survey_app.dto;

import java.util.ArrayList;
import java.util.List;

import survey_app.dto.AnswerDto;
import survey_app.dto.QuestionnaireDto;
import survey_app.models.Answer;
import survey_app.models.Question;

public class QuestionDto {

	private Long id;
	private String name;
	private QuestionnaireDto questionnaire;
	private List<AnswerDto> answer = new ArrayList<AnswerDto>();
	
	public QuestionDto() {
		
	}
	
	public QuestionDto(Question question) {
		this.id = question.getId();
		this.name = question.getName();
		this.questionnaire = new QuestionnaireDto(question.getQuestionnaire().getId(), question.getQuestionnaire().getName());
		for (Answer a: question.getAnswer()) {
			answer.add(new AnswerDto(a));
		}
	}
	
	public QuestionDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<AnswerDto> getAnswer() {
		return answer;
	}

	public void setAnswer(List<AnswerDto> answer) {
		this.answer = answer;
	}

	public QuestionnaireDto getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(QuestionnaireDto questionnaire) {
		this.questionnaire = questionnaire;
	}
	
	
	
	
	
}