package com.company.spring_thymeleaf_form.smartphone;

import com.company.spring_thymeleaf_form.firms.Firm;
import com.company.spring_thymeleaf_form.firms.FirmService;
import com.company.spring_thymeleaf_form.os.Os;
import com.company.spring_thymeleaf_form.os.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;


@Controller
public class SmartphoneController {

    @Autowired
    private SmartphoneService smartphoneService;
    @Autowired
    private FirmService firmService;
    @Autowired
    private OsService osService;

    @GetMapping(value ="/add_smart")
    public String index(Model model) {
        model.addAttribute("smartphone",new Smartphone()); //Если не добавить, то не будет выполняться парсинг шаблона исходной страницы
        model.addAttribute("os",osService.findAll());
        model.addAttribute("firms",firmService.findAll());
        return "add_smart";
    }

    @GetMapping(value ="/list_smarts" )
    public String listSmartphones(Model model) {
        model.addAttribute("smartphones", smartphoneService.findAll());
        return "list_smarts";
    }

    @PostMapping(value="/add_smart")
    public String saveSmartphone(Smartphone smartphone, Model model, HttpServletResponse response) {
        System.out.println(smartphone);
        if (smartphone.getFirm()==null ||smartphone.getOs()==null)
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "The firm or OS is null");
        //Передать id в заголовке ответа
        Smartphone newSmartphone = smartphoneService.save(smartphone);
        long id = newSmartphone.getId();
        response.addHeader("id", String.valueOf(id));
        smartphoneService.save(smartphone);
        model.addAttribute("smartphones", smartphoneService.findAll());
        return "redirect:/list_smarts";
    }

    @DeleteMapping(value ="/delete_smart")
    public String deleteSmartphone(@RequestParam(name="id")Long id) {
        smartphoneService.deleteById(id);
        return "redirect:/list_smarts";
    }

    @GetMapping(value ="/edit_smart")
    public String editSmart(Model model, @RequestParam(name="id")Long id) {
        Smartphone smartphone = smartphoneService.findById(id);
        model.addAttribute("smartphone",smartphone);
        model.addAttribute("os",osService.findAll());
        model.addAttribute("firms",firmService.findAll());
        return "edit_smart";
    }

    @PutMapping(value="/update_smart")
    public String updateSmart(Smartphone smartphone, Model model) {
        Smartphone smartphoneDb = smartphoneService.findById(smartphone.getId());
        //System.out.println(smartphone.getFirm());
        //System.out.println(smartphoneDb.getFirm());
        smartphoneDb.setName(smartphone.getName());
        smartphoneDb.setFirm(smartphone.getFirm());
        smartphoneDb.setOs(smartphone.getOs());
        smartphoneDb.setSize(smartphone.getSize());
        smartphoneDb.setColor(smartphone.getColor());
        smartphoneService.save(smartphoneDb);
        model.addAttribute("smartphones", osService.findAll());
        return "redirect:/list_smarts";
    }
}
