package survey_app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import survey_app.models.Answer;

@Repository
public interface AnswerRepository extends PagingAndSortingRepository<Answer, Long> {

}
