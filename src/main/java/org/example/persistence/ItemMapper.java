package org.example.persistence;

import org.example.data.Item;
import org.example.dto.ItemResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemResponseDTO map(Item item);


}
