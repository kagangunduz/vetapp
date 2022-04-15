package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.entity.Owner;
import com.kagangunduz.vet.service.impl.OwnerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerServiceImpl ownerService;

    @GetMapping
    public String getAllByPagination(Model model, @RequestParam(name = "page", defaultValue = "1", required = false) int pageNumber) {

        Page<Owner> page = ownerService.getAllPageable(pageNumber);
        int totalPages = page.getTotalPages();

        if (totalPages > 1 && pageNumber > totalPages) {
            throw new IllegalArgumentException(pageNumber + " numaralı sayfa bulunamadı....");
        }

        long totalItems = page.getTotalElements();
        List<Owner> ownerList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("owners", ownerList);
        return "owner/index";
    }

    @GetMapping("/search")
    public String findAllWithPartOfFullName(Model model,
                                            @RequestParam(name = "keyword", required = true) String keyword,
                                            @RequestParam(name = "page", defaultValue = "1", required = false) int pageNumber) {
        if (keyword.equals("")) {
            return "redirect:/owners";
        }

        keyword = keyword.toLowerCase();
        Page<Owner> page = ownerService.findAllWithPartOfFullName(keyword, pageNumber);
        int totalPages = page.getTotalPages();

        if (totalPages > 1 && pageNumber > totalPages) {
            throw new IllegalArgumentException(pageNumber + " numaralı sayfa bulunamadı....");
        }

        long totalItems = page.getTotalElements();

        List<Owner> ownerList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("keyword", keyword);
        model.addAttribute("owners", ownerList);
        return "owner/search";

    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owner/addForm";
    }

    @PostMapping("/add")
    public String save(@Valid Owner owner, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "owner/addForm";
        }
        ownerService.save(owner);
        redirectAttributes.addFlashAttribute("message", "Kayıt Başarılı.");
        return "redirect:/owners";

    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(name = "id") Long id) {
        try {
            model.addAttribute("owner", ownerService.findById(id));
            return "owner/show";
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Lütfen rakam giriniz.");
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("owner", ownerService.findById(id));
        return "owner/editForm";
    }

    @PostMapping("/edit/{id}")
    public String update(Model model, @PathVariable(name = "id") Long id, @Valid Owner owner,
                         BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "owner/editForm";
        }
        model.addAttribute("owner", ownerService.update(id, owner));
        redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı");
        return "redirect:/owners/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        Owner deletedOwner = ownerService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Kayıt Silindi => "
                + deletedOwner.getFullName() + " | "
                + deletedOwner.getTelephoneNumber() + " | "
                + deletedOwner.getEmail());
        return "redirect:/owners";
    }

}
