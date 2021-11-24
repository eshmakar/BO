package ru.eshmakar.business.repo;

import org.springframework.data.repository.CrudRepository;
import ru.eshmakar.business.domain.LastNews;

public interface LastNewsRepo extends CrudRepository<LastNews, Long> {
}
