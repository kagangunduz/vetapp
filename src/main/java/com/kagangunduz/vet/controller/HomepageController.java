package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.service.impl.GenusServiceImpl;
import com.kagangunduz.vet.service.impl.OwnerServiceImpl;
import com.kagangunduz.vet.service.impl.PetServiceImpl;
import com.kagangunduz.vet.service.impl.SpeciesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomepageController {

    private final OwnerServiceImpl ownerService;
    private final PetServiceImpl petService;
    private final GenusServiceImpl genusService;
    private final SpeciesServiceImpl speciesService;

    @GetMapping
    public String showHomepage(Model model) {
        model.addAttribute("ownerCount", ownerService.getOwnerCount());
        model.addAttribute("petCount", petService.getPetCount());
        model.addAttribute("genusCount", genusService.getGenusCount());
        model.addAttribute("speciesCount", speciesService.getSpeciesCount());
        return "homepage";
    }
}
