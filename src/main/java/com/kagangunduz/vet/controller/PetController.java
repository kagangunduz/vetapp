package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.entity.Pet;
import com.kagangunduz.vet.service.impl.OwnerServiceImpl;
import com.kagangunduz.vet.service.impl.PetServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/pets")
@AllArgsConstructor
public class PetController {

    private final PetServiceImpl petService;
    private final OwnerServiceImpl ownerService;


    @GetMapping
    public String getAllByPagination(Model model,
                                     @RequestParam(value = "page", defaultValue = "1", required = false) int pageNumber) {

        Page<Pet> page = petService.getAllPageable(pageNumber);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Pet> petList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("pets", petList);

        return "pet/index";
    }

    @GetMapping("/add")
    public String showNewForm(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("genusHashMap", this.getGenusAsHashMap());
        model.addAttribute("owners", ownerService.findAll());
        return "pet/addForm";
    }

    @PostMapping("/add")
    public String save(Model model, Pet pet, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("genusHashMap", this.getGenusAsHashMap());
            return "pet/addForm";
        }

        Pet newPet = petService.save(pet);
        redirectAttributes.addFlashAttribute("message", "Kayıt Başarılı.");
        return "redirect:/pets";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("pet", petService.findById(id));
        return "pet/show";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("pet", petService.findById(id));
        model.addAttribute("genus", this.getGenusAsHashMap());
        model.addAttribute("owners", ownerService.findAll());
        return "pet/editForm";
    }

    @PostMapping("/edit/{id}")
    public String update(Model model, @PathVariable(name = "id") Long id, Pet pet, BindingResult result, RedirectAttributes redirectAttributes) {

        model.addAttribute("genus", this.getGenusAsHashMap());

        if (result.hasErrors()) {
            return "pet/editForm";
        }
        model.addAttribute("pet", petService.update(id, pet));
        redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı.");
        return "redirect:/pets/edit/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        if (petService.deleteById(id)) {
            redirectAttributes.addFlashAttribute("message", "Kayıt Silindi");
        } else {
            redirectAttributes.addFlashAttribute("message", "Kayıt Silinemedi.");
        }
        return "redirect:/pets";
    }

    private Map<Genus, String> getGenusAsHashMap() {
        Map<Genus, String> genusHashMap = new HashMap<>();
        for (Genus genus : Genus.values()) {
            genusHashMap.put(genus, genus.getValue());
        }
        return genusHashMap;
    }

}
