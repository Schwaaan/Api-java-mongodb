package com.test.src.web;

import com.test.src.domain.Appointment;
import com.test.src.repository.AppointmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
public class AppointmentController {

  private AppointmentRepository appointmentRepository;

  public AppointmentController(AppointmentRepository appointmentRepository) {
    this.appointmentRepository = appointmentRepository;
  }

  @GetMapping("/v1/appointments")
  public Page<Appointment> getAllAppointments(@PageableDefault Pageable pageable) {
    return appointmentRepository.findAllByDeletedIsFalse(pageable);
  }
}
