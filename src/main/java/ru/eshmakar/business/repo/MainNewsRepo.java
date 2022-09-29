package ru.eshmakar.business.repo;

import org.springframework.data.repository.CrudRepository;
import ru.eshmakar.business.domain.MainNews;

public interface MainNewsRepo extends CrudRepository<MainNews, Long> {
}
