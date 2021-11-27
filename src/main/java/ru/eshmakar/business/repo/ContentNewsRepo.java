package ru.eshmakar.business.repo;

import org.springframework.data.repository.CrudRepository;
import ru.eshmakar.business.domain.ContentNews;

public interface ContentNewsRepo extends CrudRepository<ContentNews, Long> {
}
