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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("petDtos", petDtos);
        return "pet/index";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("petDto", petService.findById(id));
        return "pet/show";
    }

    @GetMapping("/add")
    public String showNewForm(Model model) {
        model.addAttribute("petDto", new PetDto());
        model.addAttribute("genusHashMap", this.getGenusAsHashMap());
        return "pet/addForm";
    }

    @PostMapping("/add")
    public String save(Model model, @Valid PetDto petDto, BindingResult result, RedirectAttributes redirectAttributes) {

        if (!result.hasErrors()) {
            petService.save(petDto);
            redirectAttributes.addFlashAttribute("message", "Kayıt başarılı.");
            return "redirect:/pets";
        }

        model.addAttribute("genusHashMap", this.getGenusAsHashMap());
        return "pet/addForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(Model model, @PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        petService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Kayıt Silindi");
        return "redirect:/pets";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        PetDto petDto = petService.findById(id);
        model.addAttribute("petDto", petDto);
        model.addAttribute("genusHashMap", this.getGenusAsHashMap());
        System.out.println(petDto);
        return "pet/editForm";
    }

    @PostMapping("/edit/{id}")
    public String updateById(Model model, @PathVariable(name = "id") Long id, @Valid PetDto petDto, BindingResult result, RedirectAttributes redirectAttributes) {

        model.addAttribute("genusHashMap", this.getGenusAsHashMap());

        if (!result.hasErrors()) {
            petDto = petService.save(petDto);
            model.addAttribute("petDto", petDto);
            redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı");
            return "redirect:/pets/edit/" + id;
        }

        return "pet/editForm";
    }

    private Map<Genus, String> getGenusAsHashMap() {
        Map<Genus, String> genusHashMap = new HashMap<Genus, String>();
        for (Genus genus : Genus.values()) {
            genusHashMap.put(genus, genus.getValue());
        }
        return genusHashMap;
    }


}
