package pl.tim3erland.beerservice.service;

import pl.tim3erland.beerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerID);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeerById(UUID beerID, BeerDto beerDto);
}
