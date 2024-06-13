package org.example.service;


import lombok.extern.slf4j.Slf4j;
import org.example.data.Item;
import org.example.dto.ItemDTO;
import org.example.dto.ItemResponseDTO;
import org.example.dto.SearchItemDTO;
import org.example.persistence.ItemMapper;
import org.example.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.example.persistence.ItemSpecification.bySpecification;

@Slf4j
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    public ItemResponseDTO addItem(ItemDTO itemDTO){
        Item entity = new Item();
        BeanUtils.copyProperties(itemDTO, entity);
        Item item = itemRepository.save(entity);
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
        BeanUtils.copyProperties(item, itemResponseDTO);
        return itemResponseDTO;
    }

    public ItemResponseDTO getItem(Integer id){
        Optional<Item> response = itemRepository.findById(id);
        if(response.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found: " + id);
        }
        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
        BeanUtils.copyProperties(response.get(), itemResponseDTO);
        return itemResponseDTO;
    }

    public void deleteItem(Integer id){
        Optional<Item> response = itemRepository.findById(id);
        if(response.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found: " + id);
        }
        itemRepository.delete(response.get());
    }

    public Page<ItemResponseDTO> getItems(SearchItemDTO searchItemDTO, Pageable pageable){
        return itemRepository.findAll(bySpecification(searchItemDTO), pageable).map(itemMapper::map);
    }
}
