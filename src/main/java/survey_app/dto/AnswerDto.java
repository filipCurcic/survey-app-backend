package survey_app.dto;

import survey_app.models.Answer;

public class AnswerDto {

	private Long id;
	private String name;
	private QuestionDto question;

	public AnswerDto() {
		
	}
	
	public AnswerDto(Answer answer) {
		this.id = answer.getId();
		this.name = answer.getName();
		this.question = new QuestionDto(answer.getQuestion().getId(), answer.getQuestion().getName());
	}
	
	public AnswerDto(Long id, String name, QuestionDto question) {
		this.id = id;
		this.name = name;
		this.question = question;
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

	public QuestionDto getQuestion() {
		return question;
	}

	public void setQuestion(QuestionDto question) {
		this.question = question;
	}
	
	
}
