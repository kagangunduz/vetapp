package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.dto.PetDto;
import com.kagangunduz.vet.service.impl.PetServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


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
        return "pet/show";
    }
}
