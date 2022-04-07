package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.dto.PetDto;
import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.service.impl.PetServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetServiceImpl petService;

    @GetMapping
    public String getAllPageable(Model model, Pageable pageable) {
        Page<PetDto> petDtos = petService.getAllPageable(pageable);
        model.addAttribute("pets", petDtos);
        return "pet/index";
    }


    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(name = "id") Long id) {
        PetDto petDto = petService.getById(id);
        model.addAttribute("pet", petDto);
        System.out.println("asd");
        return "pet/show";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("petDto", new PetDto());

        Map<Genus, String> genusHashMap = new HashMap<Genus, String>();
        for (Genus genus : Genus.values()) {
            genusHashMap.put(genus, genus.getValue());
        }
        model.addAttribute("genusHashMap", genusHashMap);
        return "pet/addForm";
    }

    @PostMapping("/add")
    public String addSubmit(Model model, @Valid PetDto petDto, BindingResult result) {

        if (!result.hasErrors()) {
            petService.save(petDto);
            return "redirect:/pets";
        }

        Map<Genus, String> genusHashMap = new HashMap<Genus, String>();
        for (Genus genus : Genus.values()) {
            genusHashMap.put(genus, genus.getValue());
        }
        model.addAttribute("genusHashMap", genusHashMap);

        return "pet/addForm";
    }


}
