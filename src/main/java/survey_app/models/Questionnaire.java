package survey_app.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Questionnaire {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Date created;
	
	private boolean isTemplate;
	
	@OneToMany(mappedBy = "questionnaire", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.REMOVE})
	private List<Question> question = new ArrayList<Question>();
	
	public Questionnaire() {
		
	}

	public Questionnaire(Long id, String name, Date created, boolean isTemplate,
			List<survey_app.models.Question> question) {
		super();
		this.id = id;
		this.name = name;
		this.created = created;
		this.isTemplate = isTemplate;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isTemplate() {
		return isTemplate;
	}

	public void setTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	
	

}
