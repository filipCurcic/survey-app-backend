package survey_app.models;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToOne(cascade = {CascadeType.MERGE}, optional = false)
	private Questionnaire questionnaire;
	
	@OneToMany(mappedBy = "question", cascade = {CascadeType.ALL, CascadeType.MERGE})
	private List<Answer> answer = new ArrayList<Answer>();
	
	public Question() {
		
	}
	
	

	public Question(Long id, String name, Questionnaire questionnaire, List<Answer> answer) {
		super();
		this.id = id;
		this.name = name;
		this.questionnaire = questionnaire;
		this.answer = answer;
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

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public List<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}
	
	

}
