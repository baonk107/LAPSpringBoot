package edu.testdemo.spring.repository;

import edu.testdemo.spring.entity.Derivative;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DerivativeRepository extends JpaRepository<Derivative, String> {

    @Query(value = "SELECT d FROM Derivative d WHERE d.code IN (:ids)")
    List<Derivative> getByIds(@Param("ids") List<String> ids, Pageable pageable);
}
