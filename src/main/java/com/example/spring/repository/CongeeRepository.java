package com.example.spring.repository;

import com.example.spring.model.Congee;
import com.example.spring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CongeeRepository extends JpaRepository<Congee, Integer> {
    @Query(value = """
            select * from congee WHERE date_debut BETWEEN :debut AND :fin
            """, nativeQuery = true)
    List<Congee> findBetweenDateDebutAndDateFin(@Param("debut") Date debut,@Param("fin") Date fin);
    List<Congee> findByEmployee(Employee employee);

    @Query(value = """
    SELECT c.* FROM congee c
    LEFT JOIN employee e ON e.id_employee = c.id_employee
    LEFT JOIN fonction f ON f.id_fonction = e.fonction_id
    WHERE c.date_debut = :debut AND f.id_fonction = :fonction
    """, nativeQuery = true)
    List<Congee> findByDateAndFonction(@Param("debut") Date debut, @Param("fonction") Integer fonction);

    @Query(value = """
    SELECT
        COALESCE(SUM(c.durre), 0) AS total_duree
    FROM congee c
    WHERE MONTH(c.date_debut) = :mois
    AND YEAR(c.date_debut) = :annee
    AND c.id_employee = :id_employee
    """, nativeQuery = true)
    Integer TotalMois(@Param("mois") Integer mois, @Param("annee") Integer annee, @Param("id_employee") Integer id_employee);


    @Query(value = """
    SELECT c.*
    FROM congee c
    WHERE c.id_congee NOT IN (
        SELECT cv.id_congee FROM conger_valider cv
    )
    AND c.id_congee NOT IN (
        SELECT cr.id_congee FROM conger_refuser cr
    )
    """, nativeQuery = true)
    List<Congee> findCongeeNonValider();

}
