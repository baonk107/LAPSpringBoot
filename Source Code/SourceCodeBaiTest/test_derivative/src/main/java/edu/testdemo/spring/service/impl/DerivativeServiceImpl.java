package edu.testdemo.spring.service.impl;

import edu.testdemo.spring.entity.Derivative;
import edu.testdemo.spring.entity.Paging;
import edu.testdemo.spring.model.dto.DerivativeDTO;
import edu.testdemo.spring.model.mapper.DerivativeMapper;
import edu.testdemo.spring.repository.DerivativeRepository;
import edu.testdemo.spring.service.DerivativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DerivativeServiceImpl implements DerivativeService {

    private final DerivativeRepository derivativeRepository;

    //Get Find All Deri
    @Override
    public List<DerivativeDTO> getAllDeri() {
        List<Derivative> derivativeList = derivativeRepository.findAll();
        List<DerivativeDTO> derivativeDTOS = new ArrayList<>();

        for (Derivative derivative : derivativeList) {
            derivativeDTOS.add(DerivativeMapper.toDeriDTO(derivative));
        }
        return derivativeDTOS;
    }

    @Override
    public List<Paging> getAllPaging() {
        List<Derivative> derivativeList = derivativeRepository.findAll();

        List<DerivativeDTO> derivativeDTOS = new ArrayList<>();

        for (Derivative derivative : derivativeList) {
            derivativeDTOS.add(DerivativeMapper.toDeriDTO(derivative));
        }

        Paging pagings = Paging.builder()
                .data(derivativeDTOS)
                .currentPage(1)
                .size(20)
                .totalElements(2)
                .totalPages(1)
                .build();

        List<Paging> list = new ArrayList<>();
        list.add(pagings);
        return list;
    }

    @Override
    public List<Paging> filterByPageOrSort(String strPage, String sort, String size) {
        System.out.println("Page: " + strPage);
        System.out.println("Page: " + sort);
        //Xử lý page
        try {
            int page = Integer.parseInt(strPage);
            int size_of_page = Integer.parseInt(size);

            Pageable paging;
            if (sort.equals("asc")) {
                paging = PageRequest.of(page - 1, size_of_page, Sort.by("deriCode").ascending());
            } else {
                paging = PageRequest.of(page - 1, size_of_page, Sort.by("deriCode").descending());
            }
            Page<Derivative> pagedResult = derivativeRepository.findAll(paging);

            List<DerivativeDTO> derivativeDTOS = new ArrayList<>();
            for (Derivative derivative : pagedResult) {
                derivativeDTOS.add(DerivativeMapper.toDeriDTO(derivative));
            }
            System.out.println("List: " + derivativeDTOS);

            //Find Total Page and Page Element
            List<Derivative> derivativeListByCode = derivativeRepository.findAll();
            int totalElements = derivativeListByCode.size();
            System.out.println("Total E: " + totalElements);
            int totalPages = totalElements / size_of_page;
            if (totalElements % size_of_page != 0) {
                totalPages += 1;
            }
            System.out.println("Total Page: " + totalPages);

            Paging pagings = Paging.builder()
                    .data(derivativeDTOS)
                    .currentPage(page)
                    .size(size_of_page)
                    .totalElements(totalElements)
                    .totalPages(totalPages)
                    .build();

            List<Paging> list = new ArrayList<>();
            list.add(pagings);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Paging> filterByCodeOrPagingOrSort(List<String > codes, String strPage, String sort, String size) {
        //Xử lý page
        try {
            int page = Integer.parseInt(strPage);
            int size_of_page = Integer.parseInt(size);
            Pageable paging;
            if (sort.equals("asc")) {
                paging = PageRequest.of(page - 1, size_of_page, Sort.by("deriCode").ascending());
            } else {
                paging = PageRequest.of(page - 1, size_of_page, Sort.by("deriCode").descending());
            }

            List<Derivative> derivativeList = derivativeRepository.getByIds(codes, paging);

            List<DerivativeDTO> derivativeDTOS = new ArrayList<>();
            for (Derivative derivative : derivativeList) {
                derivativeDTOS.add(DerivativeMapper.toDeriDTO(derivative));
            }

            List<Derivative> derivativeListByCode = derivativeRepository.findAllById(codes);
            int totalElements = derivativeListByCode.size();
            System.out.println("Total E: " + totalElements);
            int totalPages = totalElements / size_of_page;
            if (totalElements % size_of_page != 0) {
                totalPages += 1;
            }
            System.out.println("Total Page: " + totalPages);

            Paging pagings = Paging.builder()
                    .data(derivativeDTOS)
                    .currentPage(page)
                    .size(size_of_page)
                    .totalElements(totalElements)
                    .totalPages(totalPages)
                    .build();

            List<Paging> list = new ArrayList<>();
            list.add(pagings);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
