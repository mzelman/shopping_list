package com.mz.shoppinglist.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mz.shoppinglist.pojo.Item;
import com.mz.shoppinglist.service.ItemService;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    @GetMapping("/")
    public String getShoppingList(Model model, @RequestParam(required = false) String id) {
        int index = itemService.getItemIndex(id);
        model.addAttribute("item", itemService.indexExists(index) ? itemService.getItem(index) : new Item());
        model.addAttribute("items", itemService.getList());
        model.addAttribute("list_size", itemService.numberOfItems());
        return "form";
    }

    @PostMapping("/submit")
    public String submit(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("items", itemService.getList());
            model.addAttribute("list_size", itemService.numberOfItems());
            System.out.println(result.getAllErrors());
            return "form";
        }
        String status = itemService.checkDuplicate(item);
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        int index = itemService.getItemIndex(id);
        itemService.deleteItem(index);
        return "redirect:/";
    }

    @GetMapping("/clear")
    public String clearList() {
        itemService.clearList();
        return "redirect:/";
    }

    @GetMapping("/crossOut/{id}")
    public String crossOutItem(@PathVariable String id) {
        itemService.crossOut(id);
        return "redirect:/";
    }

    @GetMapping("/uncross/{id}")
    public String uncrossItem(@PathVariable String id) {
        itemService.uncross(id);
        return "redirect:/";
    }

    @GetMapping("/deleteCrossedOut")
    public String deleteCrossedOut() {
        itemService.findAndDeleteCrossedOut();
        return "redirect:/";
    }

}
