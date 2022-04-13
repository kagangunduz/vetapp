package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.entity.Owner;
import com.kagangunduz.vet.service.impl.OwnerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/owners")
@AllArgsConstructor
@Slf4j
public class OwnerController {

    private final OwnerServiceImpl ownerService;

    @GetMapping
    public String getAllByPagination(Model model, @RequestParam(name = "page", defaultValue = "1", required = false) int pageNumber) {

        Page<Owner> page = ownerService.getAllPageable(pageNumber);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Owner> ownerList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("owners", ownerList);
        return "owner/index";
    }

    @GetMapping("/search")
    public String findAllByFullName(Model model, @RequestParam(name = "fullName", required = true) String fullName) {
        fullName = fullName.toLowerCase(Locale.ROOT);
        List<Owner> ownerList = ownerService.findAllWithPartOfFullName(fullName);
        if (ownerList == null || ownerList.isEmpty()) {
            model.addAttribute("message", "Sonuç bulunamadı.");
        } else {
            model.addAttribute("message", "Bulunan kayıt => " + ownerList.size());
            model.addAttribute("owners", ownerList);
        }
        return "owner/search";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owner/addForm";
    }

    @PostMapping("/add")
    public String save(Owner owner, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "owner/addForm";
        }
        Owner newOwner = ownerService.save(owner);
        redirectAttributes.addFlashAttribute("message", "Kayıt Başarılı.");
        return "redirect:/owners";

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
        if (result.hasErrors()) {
            return "owner/editForm";
        }
        model.addAttribute("owner", ownerService.update(id, owner));
        redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı");
        return "redirect:/owners/edit/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        if (ownerService.deleteById(id)) {
            redirectAttributes.addFlashAttribute("message", "Kayıt Silindi");
        } else {
            redirectAttributes.addFlashAttribute("message", "Kayıt Silinemedi");
        }
        return "redirect:/owners";
    }

}
