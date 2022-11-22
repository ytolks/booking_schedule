package com.jt.bookingschedule.repository;

import com.jt.bookingschedule.model.Appointment;
import com.jt.bookingschedule.model.ServiceType;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentRepository {

    private List<Appointment> appointmentList = new ArrayList<>();

    public AppointmentRepository() {
        appointmentList.add(
                new Appointment(
                        1L,
                        ServiceType.MANICURE_FRENCH_GEL,
                        LocalDateTime.of(2022, Month.DECEMBER, 13, 12, 0),
                        LocalDateTime.of(2022, Month.DECEMBER, 13, 13, 0)));
        appointmentList.add(
                new Appointment(
                        2L,
                        ServiceType.PEDICURE_FRENCH_GEL,
                        LocalDateTime.of(2022, Month.DECEMBER, 13, 13, 30),
                        LocalDateTime.of(2022, Month.DECEMBER, 13, 15, 0)));
    }

    //get a list of appointments
    public List<Appointment> findAll() {
        return appointmentList;
    }

    //find appointment by id
    public Appointment findById(Long id){
      return appointmentList.stream()
              .filter(appointment -> appointment.getId().equals(id))
              .findFirst()
              .orElseThrow(()->new IllegalStateException("id not found"));
    }

    // add new appointment
    public Appointment create(Appointment newAppointment){
        appointmentList.add(newAppointment);
        return newAppointment;

    }

    //update appointment list by id
    public void update (Appointment newAppointment, Long id) {
        appointmentList.stream()
                .filter(appointment1 -> appointment1.getId().equals(id))
                .findFirst()
                .map(appointment1 -> {
                    appointment1.setServiceType(newAppointment.getServiceType());
                    appointment1.setServiceStartTime(newAppointment.getServiceStartTime());
                    appointment1.setServiceEndTime(newAppointment.getServiceEndTime());
                    return appointmentList.add(newAppointment);
                })
                .orElseGet(() -> {
                            newAppointment.setId(id);
                            return appointmentList.add(newAppointment);
                        }
                );
    }

    //delete appointment
    public void delete(Long id){
        appointmentList.removeIf(appointment -> appointment.getId().equals(id));
    }

}
