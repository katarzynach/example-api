package org.example;

import org.example.data.Item;
import org.example.dto.ItemResponseDTO;
import org.example.persistence.ItemMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void givenSourceToDestination_whenMaps_thenCorrect() {
        Item source = Item.builder().name("test").id(123).build();

        ItemResponseDTO destination = itemMapper.map(source);

        assertEquals(source.getName(), destination.getName());
        assertEquals(source.getId(), destination.getId());
    }
}
