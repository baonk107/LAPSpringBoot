package edu.testdemo.spring.entity;

import edu.testdemo.spring.model.dto.DerivativeDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Paging {
    private List<DerivativeDTO> data;
    private int currentPage;
    private int size;
    private int totalElements;
    private int totalPages;
}
