package edu.testdemo.spring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "effective_date")
    private Date effectiveDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_date")
    private Date expirationDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date created_date;

    @Column(name = "created_by", length = 10)
    private String createdBy;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private Date modified_date;

    @Column(name = "modified_by", length = 10)
    private String modifiedBy;

    @Column(name = "underlying_type", length = 10)
    private String underlyingType;
}
