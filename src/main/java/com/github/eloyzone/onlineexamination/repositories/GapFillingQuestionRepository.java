package com.github.eloyzone.onlineexamination.repositories;

import com.github.eloyzone.onlineexamination.domain.GapFillingQuestion;
import com.github.eloyzone.onlineexamination.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/8/20, 8:49 AM.
 */
@Repository
public interface GapFillingQuestionRepository extends JpaRepository<GapFillingQuestion, Long>
{
    @Query(value = "SELECT question.*  FROM book INNER JOIN gap_filling_question as question ON book.id = question.book_id where book.user_id = :userId and (question.level=1 or question.level = :requestedLevel)", nativeQuery = true)
    List<GapFillingQuestion> findByUserIdAndLevels(@Param("userId") Long userId, @Param("requestedLevel") int level);

    @Query(value = "SELECT count(question.id) FROM book INNER JOIN gap_filling_question as question ON book.id = question.book_id where book.user_id = :userId and question.level = :requestedLevel", nativeQuery = true)
    Integer numberOfQuestionsInSpecificLevel(@Param("userId") Long userId, @Param("requestedLevel") int level);
}
