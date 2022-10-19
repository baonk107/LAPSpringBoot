package edu.testdemo.spring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
@Table(name = "derivative")
public class Derivative {

    @Column(name = "deri_composite_code", nullable = false, length = 20)
    private String deriCode;
    @Id
    @Column(name = "derivative_code", nullable = false, length = 20)
    private String code;

    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "created_date")
    private Date created_date;

    @Column(name = "created_by", length = 10)
    private String createdBy;

    @Column(name = "modified_date")
    private Date modified_date;

    @Column(name = "modified_by", length = 10)
    private String modifiedBy;

    @Column(name = "underlying_type", length = 10)
    private String underlyingType;
}
