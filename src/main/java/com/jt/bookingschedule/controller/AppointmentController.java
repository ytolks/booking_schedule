package com.jt.bookingschedule.controller;

import com.jt.bookingschedule.model.Appointment;
import com.jt.bookingschedule.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointment")
public class AppointmentController {


   private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    //GET http://localhost:8080/appointment
    @GetMapping
    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }
    //GET http://localhost:8080/appointment/{id}

    @GetMapping("/{id}")
    public Appointment findAppointmentById(@PathVariable (value = "id") Long id){
        return appointmentRepository.findById(id);
    }

    //POST http://localhost:8080/appointment
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Appointment create( @RequestBody Appointment appointment){
        return appointmentRepository.create(appointment);
    }

    //POST http://localhost:8080/appointment

    //@ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void updateAppointment(@RequestBody Appointment appointment,
                                  @PathVariable Long id){
        appointmentRepository.update(appointment,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSelectedAppointmentByID(@PathVariable Long id){
        appointmentRepository.delete(id);
    }

}
