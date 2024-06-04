package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ItemDTO;
import org.example.dto.ItemResponseDTO;
import org.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
