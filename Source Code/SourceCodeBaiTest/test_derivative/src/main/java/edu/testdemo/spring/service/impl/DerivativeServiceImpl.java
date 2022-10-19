package edu.testdemo.spring.service.impl;

import edu.testdemo.spring.entity.Derivative;
import edu.testdemo.spring.entity.Paging;
import edu.testdemo.spring.model.dto.DerivativeDTO;
import edu.testdemo.spring.model.mapper.DerivativeMapper;
import edu.testdemo.spring.repository.DerivativeRepository;
import edu.testdemo.spring.service.DerivativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DerivativeServiceImpl implements DerivativeService {

    private final DerivativeRepository derivativeRepository;

    @Override
    public List<DerivativeDTO> getAllDeri() {
        List<Derivative> derivativeList = derivativeRepository.findAll();
        List<DerivativeDTO> derivativeDTOS = new ArrayList<>();

        for (Derivative derivative: derivativeList){
            derivativeDTOS.add(DerivativeMapper.toDeriDTO(derivative));
        }
        return derivativeDTOS;
    }

    @Override
    public List<Paging> getAllPaging() {
        List<Derivative> derivativeList = derivativeRepository.findAll();

        List<DerivativeDTO> derivativeDTOS = new ArrayList<>();

        for (Derivative derivative: derivativeList){
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
    public List<Paging> searchByCode(List<String> code) {
        System.out.println("Code: " + code);
        List<Derivative> derivativeList = derivativeRepository.findAllById(code);
//        System.out.println("List: " + derivativeList);
        List<DerivativeDTO> derivativeDTOS = new ArrayList<>();

        for (Derivative derivative: derivativeList){
            derivativeDTOS.add(DerivativeMapper.toDeriDTO(derivative));
        }
        System.out.println("List DTO: " + derivativeDTOS);

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
}
