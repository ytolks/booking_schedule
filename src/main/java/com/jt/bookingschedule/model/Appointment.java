package com.jt.bookingschedule.model;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;


public class Appointment {

    private Long id;

    private ServiceType serviceType;

    private LocalDateTime serviceStartTime;

    private LocalDateTime serviceEndTime;

    public Appointment(Long id, ServiceType serviceType, LocalDateTime serviceStartTime, LocalDateTime serviceEndTime) {
        this.id = id;
        this.serviceType = serviceType;
        this.serviceStartTime = serviceStartTime;
        this.serviceEndTime = serviceEndTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDateTime getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(LocalDateTime serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public LocalDateTime getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(LocalDateTime serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id.equals(that.id) && serviceType == that.serviceType && serviceStartTime.equals(that.serviceStartTime) && serviceEndTime.equals(that.serviceEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceType, serviceStartTime, serviceEndTime);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", serviceType=" + serviceType +
                ", serviceStartTime=" + serviceStartTime +
                ", serviceEndTime=" + serviceEndTime +
                '}';
    }
}
