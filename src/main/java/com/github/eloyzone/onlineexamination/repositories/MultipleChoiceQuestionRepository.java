package com.github.eloyzone.onlineexamination.repositories;

import com.github.eloyzone.onlineexamination.domain.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/8/20, 8:49 AM.
 */
@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Long>
{
}
