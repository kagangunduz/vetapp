package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.entity.Pet;
import com.kagangunduz.vet.service.impl.OwnerServiceImpl;
import com.kagangunduz.vet.service.impl.PetServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetServiceImpl petService;
    private final OwnerServiceImpl ownerService;


    @GetMapping
    public String getAllByPagination(Model model, @RequestParam(name = "page", defaultValue = "1", required = false) int pageNumber) {

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

    @GetMapping("/search")
    public String findAllWithPartOfNameOrOwnerFullName(Model model,
                                                       @RequestParam(name = "keyword") String keyword,
                                                       @RequestParam(name = "pageNumber", defaultValue = "1", required = false) int pageNumber) {

        if (keyword.equals("")) {
            return "redirect:/pets";
        }

        keyword = keyword.toLowerCase();
        Page<Pet> page = petService.findAllWithPartOfNameOrOwnerFullName(keyword, pageNumber);

        int totalPages = page.getTotalPages();
        if (totalPages > 1 && pageNumber > totalPages) {
            throw new IllegalArgumentException(pageNumber + " numaralı sayfa bulunamadı....");
        }

        long totalItems = page.getTotalElements();
        
        List<Pet> petList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pets", petList);
        return "pet/search";

    }

    @GetMapping("/add")
    public String showNewForm(Model model, @RequestParam(name = "ownerId", required = false) Long ownerId) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("genus", this.getGenusAsHashMap());
        model.addAttribute("owners", ownerService.findAll());
        if (ownerId != null) {
            model.addAttribute("ownerId", ownerId);
        }
        return "pet/addForm";
    }

    @PostMapping("/add")
    public String save(Model model, @Valid Pet pet, BindingResult result, RedirectAttributes redirectAttributes,
                       @RequestParam(name = "ownerId", required = false) Long ownerId) {

        if (result.hasErrors()) {
            model.addAttribute("genus", this.getGenusAsHashMap());
            model.addAttribute("owners", ownerService.findAll());
            return "pet/addForm";
        }
        petService.save(pet);
        redirectAttributes.addFlashAttribute("message", "Kayıt Başarılı.");
        if (ownerId != null && ownerId.equals(pet.getOwner().getId())) {
            return "redirect:/owners/" + ownerId;
        }
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
    public String update(Model model, @PathVariable(name = "id") Long id, @Valid Pet pet, BindingResult result,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("genus", this.getGenusAsHashMap());
            model.addAttribute("owners", ownerService.findAll());
            return "pet/editForm";
        }
        model.addAttribute("pet", petService.update(id, pet));
        redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı.");
        return "redirect:/pets/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        Pet deletedPet = petService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Kayıt Silindi => " + deletedPet.getName() + " | " + deletedPet.getAge() + " | " + deletedPet.getGenus());
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
