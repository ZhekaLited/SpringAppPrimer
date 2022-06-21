package com.company.spring_thymeleaf_form.os;

import com.company.spring_thymeleaf_form.firms.Firm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
    public class OsController {

        @Autowired
        private OsService osService;

        @GetMapping(value ="/add_os")
        public String indexOs(Model model) {
            model.addAttribute("os",new Os()); //Если не добавить, то не будет выполняться парсинг шаблона исходной страницы
            return "add_os";
        }

        @GetMapping(value ="/list_os" )
        public String listOs(Model model) {
            model.addAttribute("os", osService.findAll());
            return "list_os";
        }

        @PostMapping(value="/add_os")
        public String saveOs(Os os, Model model, HttpServletResponse response) {
            //Передать id в заголовке ответа
            Os newOs = osService.save(os);
            long id = newOs.getId();
            response.addHeader("id", String.valueOf(id));
            model.addAttribute("os", osService.findAll());
            return "redirect:/list_os";
        }

    @DeleteMapping(value = "/delete_os")
        public String deleteOs(@RequestParam(name="id")Long id) { osService.deleteById(id);
        return "redirect:/list_os";
        }

    @GetMapping(value ="/edit_os")
    public String editOs(Model model, @RequestParam(name="id")Long id) {
            Os os = osService.findById(id);
        model.addAttribute("os",os);
        return "edit_os";
    }

    @PutMapping(value="/update_os")
    public String updateOs(Os os, Model model) {
            Os osDb = osService.findById(os.getId());
        osDb.setName(os.getName());
        osDb.setDeveloper(os.getDeveloper());
        //osService.deleteById(os.getId());
        osService.save(osDb);
        model.addAttribute("os", osService.findAll());
        return "list_os";
    }
    }

