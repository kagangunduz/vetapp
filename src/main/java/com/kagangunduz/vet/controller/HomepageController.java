package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.service.impl.OwnerServiceImpl;
import com.kagangunduz.vet.service.impl.PetServiceImpl;
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


    @GetMapping
    public String showHomepage(Model model) {
        model.addAttribute("ownerCount", ownerService.getOwnerCount());
        model.addAttribute("petCount", petService.getPetCount());
        return "homepage";
    }
}
