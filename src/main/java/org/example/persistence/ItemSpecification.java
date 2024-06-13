package org.example.persistence;

import lombok.experimental.UtilityClass;
import org.example.data.Item;
import org.example.dto.SearchItemDTO;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@UtilityClass
public class ItemSpecification {


    public static Specification<Item> bySpecification(SearchItemDTO searchItemDTO){
        if(searchItemDTO == null) return null;

        return Specification.where(hasName(searchItemDTO.getName()))
                .and(hasManufacturer(searchItemDTO.getManufacturer()))
                .and(hasUnit(searchItemDTO.getUnit()))
                .and(hasId(searchItemDTO.getId()))
                .and(hasAmount(searchItemDTO.getAmount()))
                .and(hasAdditionalInfo(searchItemDTO.getAdditionalInfo()))
                .and(hasExpirationDateAfter(searchItemDTO.getFromDate()))
                .and(hasExpirationDateBefore(searchItemDTO.getToDate()));
    }

    private static Specification<Item> hasName(String name){
        if(name == null) return null;

        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("name"), name);

    }

    private static Specification<Item> hasManufacturer(String manufacturer){
        if(manufacturer == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("manufacturer"), manufacturer);
    }

    private static Specification<Item> hasUnit(String unit){
        if(unit == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("unit"), unit);
    }

    private static Specification<Item> hasId(Integer id){
        if(id == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    private static Specification<Item> hasAmount(Double amount){
        if(amount == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("amount"), amount);
    }

    private static Specification<Item> hasAdditionalInfo(String info){
        if(info == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("additionalInfo"), info);
    }

    private static Specification<Item> hasExpirationDateBefore(LocalDate date){
        if(date == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("expirationDate"), date);
    }

    private static Specification<Item> hasExpirationDateAfter(LocalDate date){
        if(date == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("expirationDate"), date);
    }




}
