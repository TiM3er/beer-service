package pl.tim3erland.beerservice.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.tim3erland.beerservice.domain.Beer;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
