package org.example;

import org.example.data.Item;
import org.example.dto.SearchItemDTO;
import org.example.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.example.persistence.ItemSpecification.bySpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SpecificationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void givenID_shouldReturnSingleResult() {
        SearchItemDTO searchItemDTO = SearchItemDTO.builder().id(1).build();

        Pageable page = PageRequest.of(0, Integer.MAX_VALUE, Sort.unsorted());

        Page<Item> results = itemRepository.findAll(bySpecification(searchItemDTO), page);

        assertEquals(1, results.getTotalElements());
    }

    @Test
    public void givenUniqueName_shouldReturnSingleResult() {
        SearchItemDTO searchItemDTO = SearchItemDTO.builder().name("TEST").build();

        Pageable page = PageRequest.of(0, Integer.MAX_VALUE, Sort.unsorted());

        Page<Item> results = itemRepository.findAll(bySpecification(searchItemDTO), page);

        assertEquals(1, results.getTotalElements());
    }

    @Test
    public void givenManufacturer_shouldReturn2() {
        SearchItemDTO searchItemDTO = SearchItemDTO.builder().manufacturer("MAN2").build();

        Pageable page = PageRequest.of(0, Integer.MAX_VALUE, Sort.unsorted());

        Page<Item> results = itemRepository.findAll(bySpecification(searchItemDTO), page);

        assertEquals(2, results.getTotalElements());
    }



}
