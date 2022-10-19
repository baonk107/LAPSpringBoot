package edu.testdemo.spring.service;

import edu.testdemo.spring.entity.Derivative;
import edu.testdemo.spring.entity.Paging;
import edu.testdemo.spring.model.dto.DerivativeDTO;

import java.util.List;

public interface DerivativeService {
    public List<DerivativeDTO> getAllDeri();

    public List<Paging> getAllPaging();

    public List<Paging> filterByPageOrSort(String strPage, String sort, String size);

    public List<Paging> filterByCodeOrPagingOrSort(List<String> codes, String page, String sort, String size);

}
