package edu.testdemo.spring.repository;

import edu.testdemo.spring.entity.Derivative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DerivativeRepository extends JpaRepository<Derivative, String> {

//    @Query(value = "SELECT * FROM derivative d WHERE d.derivative_code IN :ids")
//    List<Derivative> getByIds(@Param("ids") List<String> ids);
}
