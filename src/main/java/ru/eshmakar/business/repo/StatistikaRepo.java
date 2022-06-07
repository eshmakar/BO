package ru.eshmakar.business.repo;

import org.springframework.data.repository.CrudRepository;
import ru.eshmakar.business.domain.Statistika;

import java.util.List;

public interface StatistikaRepo extends CrudRepository<Statistika, Long> {
    List<Statistika> getTop300ByOrderByDateDesc();
}
