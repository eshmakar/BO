package ru.eshmakar.business.repo;

import org.springframework.data.repository.CrudRepository;
import ru.eshmakar.business.domain.Statistika;

import java.util.LinkedList;

public interface StatistikaRepo extends CrudRepository<Statistika, Long> {
    LinkedList<Statistika> getTop100ByOrderByDateDesc();
}
