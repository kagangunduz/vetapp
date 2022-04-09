package com.kagangunduz.vet.controller;

import com.kagangunduz.vet.dto.OwnerDto;
import com.kagangunduz.vet.exception.OwnerNotFoundException;
import com.kagangunduz.vet.service.impl.OwnerServiceImpl;
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

@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerServiceImpl ownerService;

    @GetMapping
    public String getAllPageable(Model model, Pageable pageable) {
        Page<OwnerDto> ownerDtos = ownerService.getAllPageable(pageable);
        model.addAttribute("ownerDtos", ownerDtos);
        return "owner/index";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable(name = "id") Long id) {
        OwnerDto ownerDto = ownerService.findById(id);
        model.addAttribute("ownerDto", ownerDto);
        /*model.addAttribute("petDtos", ownerDto.getPets());*/
        return "owner/show";
    }

    @GetMapping("/add")
    public String showNewForm(Model model) {
        model.addAttribute("ownerDto", new OwnerDto());
        return "owner/addForm";
    }

    @PostMapping("/add")
    public String save(Model model, OwnerDto ownerDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!result.hasErrors()) {
            ownerService.save(ownerDto);
            redirectAttributes.addFlashAttribute("message", "Kayıt Başarılı");
            return "redirect:/owners";
        }
        return "owner/addForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        try {
            ownerService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Kayıt Silindi");
            return "redirect:/owners";
        } catch (OwnerNotFoundException exception) {
            redirectAttributes.addFlashAttribute("message", "Kayıt bulunamadı.");
        }
        return "redirect:/owners";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        OwnerDto ownerDto = ownerService.findById(id);
        model.addAttribute("ownerDto", ownerDto);
        return "owner/editForm";
    }

    @PostMapping("/edit/{id}")
    public String updateById(Model model, @PathVariable(name = "id") Long id, @Valid OwnerDto ownerDto, BindingResult result, RedirectAttributes redirectAttributes) {

        if (!result.hasErrors()) {
            ownerDto = ownerService.save(ownerDto);
            model.addAttribute("ownerDto", ownerDto);
            redirectAttributes.addFlashAttribute("message", "Güncelleme başarılı");
            return "redirect:/owners/edit/" + id;
        }

        return "owner/editForm";
    }

}
