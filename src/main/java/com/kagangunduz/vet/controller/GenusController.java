package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.exception.RecordAlreadyExistException;
import com.kagangunduz.vet.service.impl.GenusServiceImpl;
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
@RequestMapping("/genera")
@RequiredArgsConstructor
public class GenusController {

    private final GenusServiceImpl genusService;

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

        try {
            genusService.save(genus);
            redirectAttributes.addFlashAttribute("message", "Kayıt Başarılı.");
            return "redirect:/genera";
        } catch (RecordAlreadyExistException exception) {
            ObjectError error = new ObjectError("name", "Cins adı zaten kayıtlı.");
            result.addError(error);
            return "genus/addForm";
        }

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

        try {
            model.addAttribute("genus", genusService.update(id, genus));
            redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı");
            return "redirect:/genera/" + id;
        } catch (RecordAlreadyExistException exception) {
            ObjectError error = new ObjectError("name", "Cins adı zaten kayıtlı.");
            result.addError(error);
            return "genus/editForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        Genus genus = genusService.findById(id);
        System.out.println(genus.getSpecies());
        if (genus.getSpecies().size() > 0) {
            redirectAttributes.addFlashAttribute("message", "Öncelikle cinse kayıtlı türleri silmelisiniz.");
            return "redirect:/genera/" + genus.getId();
        }
        genusService.deleteById(genus.getId());
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
