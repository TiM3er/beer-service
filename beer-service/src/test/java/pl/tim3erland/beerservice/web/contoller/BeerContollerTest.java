package pl.tim3erland.beerservice.web.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.tim3erland.beerservice.bootstrap.BeerLoader;
import pl.tim3erland.beerservice.service.BeerService;
import pl.tim3erland.beerservice.web.model.BeerDto;
import pl.tim3erland.beerservice.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BeerContoller.class)
class BeerContollerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        given(beerService.getBeerById(any())).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        var beerDto = getValidBeerDto();
        var beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void updateBeerById() throws Exception {
        var beerDto = getValidBeerDto();
        var beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(BigDecimal.ONE)
                .upc(BeerLoader.BEER_3_UPC)
                .build();
    }
}