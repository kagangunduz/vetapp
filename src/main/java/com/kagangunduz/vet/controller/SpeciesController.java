package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.entity.Species;
import com.kagangunduz.vet.exception.RecordAlreadyExistException;
import com.kagangunduz.vet.service.impl.GenusServiceImpl;
import com.kagangunduz.vet.service.impl.SpeciesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/species")
@RequiredArgsConstructor
public class SpeciesController {

    private final SpeciesServiceImpl speciesService;
    private final GenusServiceImpl genusService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("species", new Species());
        model.addAttribute("genera", genusService.findAll());
        return "species/addForm";
    }

    @PostMapping("/add")
    public String save(Model model, @Valid @ModelAttribute(value = "species") Species species, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("genera", genusService.findAll());
            return "species/addForm";
        }
        species.setName(species.getName().toLowerCase());

        try {
            speciesService.save(species);
            redirectAttributes.addFlashAttribute("message", "Kayıt Başarılı.");
            return "redirect:/species";
        } catch (RecordAlreadyExistException exception) {
            ObjectError error = new ObjectError("name", exception.getMessage());
            result.addError(error);
            model.addAttribute("genera", genusService.findAll());
            return "species/addForm";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("species", speciesService.findById(id));
        model.addAttribute("genera", genusService.findAll());
        return "species/editForm";
    }

    @PostMapping("/edit/{id}")
    public String update(Model model, @PathVariable(name = "id") Long id, @Valid @ModelAttribute(value = "species") Species species, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("genera", genusService.findAll());
            return "species/editForm";
        }

        try {
            model.addAttribute("species", speciesService.update(id, species));
            redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı");
            return "redirect:/species";
        } catch (RecordAlreadyExistException exception) {
            ObjectError objectError = new ObjectError("name", exception.getMessage());
            result.addError(objectError);
            model.addAttribute("genera", genusService.findAll());
            return "species/editForm";
        }
    }

    @GetMapping
    public String getAll(Model model) {
        List<Species> speciesList = speciesService.findAll();
        model.addAttribute("species", speciesList);
        model.addAttribute("totalItems", speciesList.size());
        return "species/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id, @RequestParam(name = "genusId", required = false) Long genusId, RedirectAttributes redirectAttributes) {
        Species species = speciesService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Kayıt Silindi => " + species.getName());

        if (genusId != null) {
            return "redirect:/genera/" + genusId;
        }
        return "redirect:/species";
    }

    @PostMapping("/get-species")
    @ResponseBody
    public List<Species> getSpecies(@ModelAttribute("genusId") Long genusId) {
        return speciesService.getAllByGenusId(genusId);
    }

}
