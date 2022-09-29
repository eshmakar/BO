package ru.eshmakar.business.repo;

import org.springframework.data.repository.CrudRepository;
import ru.eshmakar.business.domain.HotNews;

public interface HotNewsRepo extends CrudRepository<HotNews, Long> {
}
