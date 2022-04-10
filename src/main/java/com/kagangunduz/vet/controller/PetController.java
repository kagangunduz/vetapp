package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.entity.Pet;
import com.kagangunduz.vet.service.impl.OwnerServiceImpl;
import com.kagangunduz.vet.service.impl.PetServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetServiceImpl petService;
    private final OwnerServiceImpl ownerService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("pets", petService.findAll());
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

        if (!result.hasErrors()) {
            Pet newPet = petService.save(pet);
            redirectAttributes.addFlashAttribute("message", "Kayıt Başarılı => " + newPet.toString());
            return "redirect:/pets";
        }
        model.addAttribute("genusHashMap", this.getGenusAsHashMap());
        return "pet/addForm";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("pet", petService.findById(id));
        return "pet/show";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("pet", petService.findById(id));
        model.addAttribute("owners", ownerService.findAll());
        model.addAttribute("genus", this.getGenusAsHashMap());
        return "pet/editForm";
    }

    @PostMapping("/edit/{id}")
    public String update(Model model, @PathVariable(name = "id") Long id, Pet pet, BindingResult result,
                         RedirectAttributes redirectAttributes) {

        model.addAttribute("genus", this.getGenusAsHashMap());

        if (!result.hasErrors()) {
            model.addAttribute("pet", petService.save(pet));
            redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı.");
            return "redirect:/pets/edit/" + id;
        }
        return "pet/editForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        petService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Kayıt Silindi");
        return "redirect:/pets";
    }


    /*
    @GetMapping
    public String getAllPageable(Model model, Pageable pageable) {
        model.addAttribute("pets", petService.getAllPageable(pageable));
        return "pet/index";
    }
    */

    private Map<Genus, String> getGenusAsHashMap() {
        Map<Genus, String> genusHashMap = new HashMap<>();
        for (Genus genus : Genus.values()) {
            genusHashMap.put(genus, genus.getValue());
        }
        return genusHashMap;
    }

}
