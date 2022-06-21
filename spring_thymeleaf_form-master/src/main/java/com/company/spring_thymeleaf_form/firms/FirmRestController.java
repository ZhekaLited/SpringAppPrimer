package com.company.spring_thymeleaf_form.firms;

import com.company.spring_thymeleaf_form.smartphone.Smartphone;
import com.company.spring_thymeleaf_form.smartphone.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FirmRestController {
    @Autowired
    private FirmService firmService;
    @Autowired
    private SmartphoneService smartphoneService;

    @GetMapping(value="/firms", params={})
    public List<Firm> getUsers(){
        var firms = (List<Firm>) firmService.findAll();
        return firms;
    }

    @GetMapping(value="/smarts", params={})
    public List<Smartphone> getSmarts(){
        var smartphones = (List<Smartphone>) smartphoneService.findAll();
        return smartphones;
    }
}
