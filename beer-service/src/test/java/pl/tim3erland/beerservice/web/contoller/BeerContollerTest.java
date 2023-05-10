package pl.tim3erland.beerservice.web.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.tim3erland.beerservice.web.model.BeerDto;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(BeerContoller.class)
class BeerContollerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v1/beer" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        var beerDto = BeerDto.builder().build();
        var beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer" )
                        .accept(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void updateBeerById() throws Exception {
        var beerDto = BeerDto.builder().build();
        var beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer"+ UUID.randomUUID().toString() )
                        .accept(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}