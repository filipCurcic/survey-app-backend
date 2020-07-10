package survey_app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import survey_app.models.Questionnaire;


@Repository
public interface QuestionnaireRepository extends PagingAndSortingRepository<Questionnaire, Long> {
	
	Iterable<Questionnaire> findByIsTemplateTrue();
	
	Iterable<Questionnaire> findByIsTemplateFalse();

	
	@Query("SELECT MAX(id) FROM Questionnaire q")
	Long getLastQuestionnaireId();
}
