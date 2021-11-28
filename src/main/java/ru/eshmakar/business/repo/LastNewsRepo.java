package ru.eshmakar.business.repo;

import org.springframework.data.repository.CrudRepository;
import ru.eshmakar.business.domain.LastNews;

import java.util.List;
public interface LastNewsRepo extends CrudRepository<LastNews, Long> {
//    @Query(value = "select * from last_news order by comments desc  limit 10", nativeQuery = true)
//    List<LastNews> getTopComments();

    List<LastNews> getTop10ByOrderByCommentsDesc();
}
