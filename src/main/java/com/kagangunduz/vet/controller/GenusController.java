package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.service.impl.GenusServiceImpl;
import com.kagangunduz.vet.service.impl.SpeciesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/genera")
@RequiredArgsConstructor
public class GenusController {

    private final GenusServiceImpl genusService;
    private final SpeciesServiceImpl speciesService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("genus", new Genus());
        return "genus/addForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute(value = "genus") Genus genus, BindingResult result,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "genus/addForm";
        }

        genusService.save(genus);
        redirectAttributes.addFlashAttribute("message", "Kayıt Başarılı.");
        return "redirect:/genera";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("genus", genusService.findById(id));
        return "genus/show";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("genus", genusService.findById(id));
        return "genus/editForm";
    }


    @PostMapping("/edit/{id}")
    public String update(Model model,
                         @PathVariable(name = "id") Long id,
                         @Valid @ModelAttribute(value = "genus") Genus genus,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "genus/editForm";
        }
        model.addAttribute("genus", genusService.update(id, genus));
        redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı");
        return "redirect:/genera/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        Genus genus = genusService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Kayıt Silindi => " + genus.getName());
        return "redirect:/genera";
    }

    @GetMapping
    public String getAll(Model model) {
        List<Genus> genusList = genusService.findAll();
        model.addAttribute("genera", genusList);
        model.addAttribute("totalItems", genusList.size());
        return "genus/index";
    }


}
