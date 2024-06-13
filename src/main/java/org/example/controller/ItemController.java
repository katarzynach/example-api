package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ItemDTO;
import org.example.dto.ItemResponseDTO;
import org.example.dto.SearchItemDTO;
import org.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Slf4j
@RequestMapping(value = "/item")

public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add new item")
    public ResponseEntity<ItemResponseDTO> addItem(@RequestBody @Valid ItemDTO itemDTO){
        return new ResponseEntity<>(itemService.addItem(itemDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get item")
    public ResponseEntity<ItemResponseDTO> getItem(@PathVariable Integer id){
        return new ResponseEntity<>(itemService.getItem(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete item")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id){
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Search for item")
    public ResponseEntity<Page<ItemResponseDTO>> searchItems(@RequestParam(value = "id", required = false) Integer id,
                                                             @RequestParam(value = "name", required = false) String name,
                                                             @RequestParam(value = "manufacturer", required = false) String manufacturer,
                                                             @RequestParam(value = "amount", required = false) Double amount,
                                                             @RequestParam(value = "unit", required = false) String unit,
                                                             @RequestParam(value = "expirationDateFrom", required = false) LocalDate expirationDateFrom,
                                                             @RequestParam(value = "expirationDateTo", required = false) LocalDate expirationDateTo,
                                                             @RequestParam(value = "additionalInfo", required = false) String additionalInfo,
                                                             @PageableDefault Pageable pageable
                                                             )
    {
        SearchItemDTO searchItemDTO = SearchItemDTO.builder().id(id).name(name).manufacturer(manufacturer).amount(amount)
                .unit(unit).fromDate(expirationDateFrom).toDate(expirationDateTo).additionalInfo(additionalInfo).build();
        return new ResponseEntity<>(itemService.getItems(searchItemDTO, pageable), HttpStatus.OK);

    }

}
