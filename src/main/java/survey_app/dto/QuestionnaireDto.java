package survey_app.dto;

import java.util.ArrayList;
import java.util.List;

import survey_app.dto.QuestionDto;
import survey_app.models.Question;
import survey_app.models.Questionnaire;

public class QuestionnaireDto {

	private Long id;
	private String name;
	private List<QuestionDto> question = new ArrayList<QuestionDto>();
	private boolean isTemplate;
	
	public QuestionnaireDto() {
		
	}
	
	public QuestionnaireDto(Questionnaire questionnaire) {
		this.id = questionnaire.getId();
		this.name = questionnaire.getName();
		this.isTemplate = questionnaire.isTemplate();
		for (Question q : questionnaire.getQuestion()) {
			question.add(new QuestionDto(q));
		}

	}
	
	public QuestionnaireDto(Long id, String name) {
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

	public List<QuestionDto> getQuestion() {
		return question;
	}

	public void setQuestion(List<QuestionDto> question) {
		this.question = question;
	}

	public boolean isTemplate() {
		return isTemplate;
	}

	public void setTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
	}
	
	
}
