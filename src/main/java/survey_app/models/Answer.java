package survey_app.models;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private boolean value;
	
	@ManyToOne(cascade =  {CascadeType.REFRESH, CascadeType.MERGE }, optional = false)
	private Question question;
	
	public Answer() {
		
	}
	
	
	// Test constructor
	public Answer(Long id, String name, boolean value, Question question) {
		this.id = id;
		this.name = name;
		this.value = value;
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


	public boolean isValue() {
		return value;
	}


	public void setValue(boolean value) {
		this.value = value;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
	
	
}
