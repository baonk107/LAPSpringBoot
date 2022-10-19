package edu.testdemo.spring.controller;

import edu.testdemo.spring.entity.Derivative;
import edu.testdemo.spring.entity.Paging;
import edu.testdemo.spring.model.dto.DerivativeDTO;
import edu.testdemo.spring.service.DerivativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/baonk107")
public class DerivativeController {

    private final DerivativeService derivativeService;

    @RequestMapping(value = "/derivativeAll")
    public List<DerivativeDTO> getListDeri() {

        return derivativeService.getAllDeri();
    }

    @RequestMapping(value = "/derivative")
    public List<Paging> getListpaging(@RequestParam(name = "q") String ids,
                                      @RequestParam(name = "page") int page) {
        ids=ids.trim();
        System.out.println("String ID: " + ids);
        System.out.println("Paging: " + page);

        String strFinal = ids.substring(5).trim();
        System.out.println("SubStr: "+strFinal);

        String[] words = strFinal.split("\\,");
        List<String> code = new ArrayList<>();
        for (String w : words) {
            code.add(w);
        }

        return derivativeService.searchByCode(code);
    }
}
