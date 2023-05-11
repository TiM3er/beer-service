package pl.tim3erland.beerservice.web.mappers;

import org.mapstruct.Mapper;
import pl.tim3erland.beerservice.domain.Beer;
import pl.tim3erland.beerservice.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto dto);
    BeerDto beerToBeerDto(Beer beer);
}
