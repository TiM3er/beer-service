package pl.tim3erland.beerservice.web.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.tim3erland.beerservice.service.BeerService;
import pl.tim3erland.beerservice.web.model.BeerDto;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerContoller {
    private final BeerService beerService;


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerID) {
        return ResponseEntity.ok(beerService.getBeerById(beerID));
    }

    @PostMapping()
    public ResponseEntity<Void> saveNewBeer(@RequestBody @Validated BeerDto beerDto) {
        beerService.saveNewBeer(beerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeerById(@PathVariable("beerId") UUID beerID,@Validated @RequestBody BeerDto beerDto) {
        beerService.updateBeerById(beerID,beerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
