
package survey_app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import survey_app.models.Question;


@Repository
public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {

	@Query("SELECT q FROM Question q WHERE q.questionnaire.id = ?1")
	Iterable<Optional<Question>> findQuestionByQuestionnaireId(Long id);
	
	@Query("SELECT MAX(id) FROM Question q")
	Long getLastQuestionId();
	
}
