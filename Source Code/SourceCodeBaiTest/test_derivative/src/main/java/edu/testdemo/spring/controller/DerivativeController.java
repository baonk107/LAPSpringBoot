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
    public List<Paging> getListpaging(@RequestParam(name = "code", required = false) List<String> codes,
                                      @RequestParam(name = "page", required = false) String page,
                                      @RequestParam(name = "sort", required = false) String sort,
                                      @RequestParam(name = "size", required = false) String size) {

        //Xử Lý page là chữ, < 0, size là chữ, size < 0 sau :v
        if (page == null || page.isEmpty()) {
            page = "1"; //Default = 1
        }
        if (sort == null || sort.isEmpty()) {
            sort = "desc"; //Default desc
        }
        if (size == null || size.isEmpty()) {
            size = "20"; //Default size = 2
        }
        if (codes == null || codes.isEmpty()) {
            return derivativeService.filterByPageOrSort(page, sort, size);
        }

        return derivativeService.filterByCodeOrPagingOrSort(codes, page, sort, size);
    }
}
