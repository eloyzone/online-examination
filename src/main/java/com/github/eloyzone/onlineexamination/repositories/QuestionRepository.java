package com.github.eloyzone.onlineexamination.repositories;

import com.github.eloyzone.onlineexamination.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/8/20, 8:49 AM.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>
{
    List<Question> findByLevelInAndCreatedDateNotAndBook_User_id(List<Integer> levels, LocalDate createdDate, Long UserId);

    @Query(value = "SELECT count(*) as count, level as level FROM book INNER JOIN question as question ON book.id = question.book_id where book.user_id = :userId group by level", nativeQuery = true)
    List<Tuple> questionsLevelsCount(@Param("userId") Long userId);

    default Map<Integer, Integer> questionsLevelsCountMapped(@Param("userId") Long userId) {
        return questionsLevelsCount(userId)
                .stream()
                .collect(
                        Collectors.toMap(
                                tuple -> ((Number) tuple.get("level")).intValue(),
                                tuple -> ((Number) tuple.get("count")).intValue()
                        ));
    }

}
