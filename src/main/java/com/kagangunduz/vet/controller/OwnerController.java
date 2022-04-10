package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.entity.Owner;
import com.kagangunduz.vet.service.impl.OwnerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerServiceImpl ownerService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owner/index";
    }

    @GetMapping("/add")
    public String showNewForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owner/addForm";
    }

    @PostMapping("/add")
    public String save(Model model, Owner owner, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!result.hasErrors()) {
            Owner newOwner = ownerService.save(owner);
            redirectAttributes.addFlashAttribute("message", "Kayıt Başarılı => " + newOwner.toString());
            return "redirect:/owners";
        }
        return "owner/addForm";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("owner", ownerService.findById(id));
        return "owner/show";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("owner", ownerService.findById(id));
        return "owner/editForm";
    }

    @PostMapping("/edit/{id}")
    public String update(Model model, @PathVariable(name = "id") Long id, Owner owner, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!result.hasErrors()) {
            model.addAttribute("owner", ownerService.save(owner));
            redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı");
            return "redirect:/owners/edit/" + id;
        }
        return "owner/editForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        ownerService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Kayıt Silindi");
        return "redirect:/owners";
    }


   /* @GetMapping
    public String getAllPageable(Model model, Pageable pageable) {
        model.addAttribute("ownerDtos", ownerService.getAllPageable(pageable));
        return "owner/index";
    }
   */

}
