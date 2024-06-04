package org.example.service;

import org.example.data.Item;
import org.example.dto.ItemDTO;
import org.example.dto.ItemResponseDTO;
import org.example.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    @DisplayName("Add new item success")
    void addItem() {
        Item savedItem = Item.builder().id(123).name("test").unit("ml").expirationDate(LocalDate.now()).build();
        when(itemRepository.save(any())).thenReturn(savedItem);
        ItemDTO itemDTO = ItemDTO.builder().name("test").build();
        ItemResponseDTO itemResponseDTO = itemService.addItem(itemDTO);
        assertNotNull(itemResponseDTO);
        assertEquals(savedItem.getId(), itemResponseDTO.getId());
    }
    @Test
    @DisplayName("Get item by ID")
    void getItem() {
        Integer id = 123;
        Item item =  Item.builder().id(123).name("test").unit("ml").expirationDate(LocalDate.now()).build();
        when(itemRepository.findById(any())).thenReturn(Optional.of(item));
        ItemResponseDTO result = itemService.getItem(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }
}