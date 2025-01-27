package com.example.spring.controller;

import com.example.spring.model.Congee;
import com.example.spring.model.Employee;
import com.example.spring.service.CongeeService;
import com.example.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/congee")
public class CongeeController {
    @Autowired
    private CongeeService congeeService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Congee> congees = congeeService.findAll();
            return new ResponseEntity<>(congees, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody Congee congee){
        try{
            List<Congee> allCongee =
                    congeeService.findByDateDebutAndFonction
                            (congee.getDate_debut(),congee.getEmployee_id().getFonction_id().getId_fonction());
            if(allCongee.size() > 0){
                return new ResponseEntity<>("Un employé a dejas pris un congé pour le "+congee.getDate_debut(),HttpStatus.UNAUTHORIZED);
            }else{
                if(congee.getDurre() < 10){
                    Employee employee = employeeService.findById(congee.getEmployee_id().getId_employee()).get();
                    if(employee.getConge_annuel() >= congee.getDurre()){
                        Date dateDebut = congee.getDate_debut();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(dateDebut);

                        Integer mois = calendar.get(Calendar.MONTH) + 1;
                        Integer annee = calendar.get(Calendar.YEAR);
                        Integer congeDejasPrixParMois = congeeService.TotalMois(mois,annee,congee.getEmployee_id().getId_employee());

                        if(congeDejasPrixParMois < 10){
                            Congee congees = congeeService.create(congee);
                            return new ResponseEntity<>(congees, HttpStatus.OK);
                        }else{
                            return new ResponseEntity<>("Vous avez prix tous vos conger pour cette mois!",HttpStatus.UNAUTHORIZED);
                        }
                    }else{
                        return new ResponseEntity<>("Congé annuel insuffisant!",HttpStatus.UNAUTHORIZED);
                    }
                }else{
                    return new ResponseEntity<>("Le nombre de jours demandé doit etre inférieur a 10 jours",HttpStatus.UNAUTHORIZED);
                }
            }
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) throws Exception{
        try{
            Congee congee = congeeService.findById(id).get();
            return new ResponseEntity<>(congee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/date")
    public ResponseEntity<Object> findBetweenDebutAndFin(@RequestBody Date debut, @RequestBody Date fin) throws Exception{
        try{
            List<Congee> congee = congeeService.findBetweenDateDebutAndDateFin(debut, fin);
            return new ResponseEntity<>(congee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<Object> findByEmployee(@RequestBody Employee employee) throws Exception{
        try{
            List<Congee> congee = congeeService.findByEmployee(employee);
            return new ResponseEntity<>(congee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws Exception{
        try{
            congeeService.delete(id);
            return new ResponseEntity<>("suppresion effectuer!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Congee congee) throws Exception{
        try{
            Optional<Congee> ExistingCongee = congeeService.findById(id);
            if(ExistingCongee.isPresent()){
                ExistingCongee.get().setDate_debut(congee.getDate_debut());
                ExistingCongee.get().setDate_fin(congee.getDate_fin());
                ExistingCongee.get().setMotif(congee.getMotif());
                ExistingCongee.get().setDurre(congee.getDurre());
                congeeService.update(id,ExistingCongee.get());
            }
            return new ResponseEntity<>(congee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nonValider")
    public ResponseEntity<Object> findCongerNonValider() throws Exception{
        try{
            List<Congee> congee = congeeService.findCongeeNonValider();
            return new ResponseEntity<>(congee, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
