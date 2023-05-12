package pl.tim3erland.beerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tim3erland.beerservice.domain.Beer;
import pl.tim3erland.beerservice.repositories.BeerRepository;
import pl.tim3erland.beerservice.web.contoller.NotFoundException;
import pl.tim3erland.beerservice.web.mappers.BeerMapper;
import pl.tim3erland.beerservice.web.model.BeerDto;

import java.util.UUID;

@Service("beerServiceImpl")
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerID) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerID).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeerById(UUID beerID, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerID).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
