package com.test.src.web;

import com.test.src.domain.Appointment;
import com.test.src.repository.AppointmentRepository;
import com.test.utils.ModelFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@Import({ModelFactory.class})
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentControllerTest {

  @Autowired
  public ModelFactory modelFactory;
  @Autowired
  private AppointmentController appointmentController;
  @Autowired
  private AppointmentRepository appointmentRepository;


  @Before
  public void init() {
    modelFactory.cleanAll();
    appointmentController = new AppointmentController(appointmentRepository);
  }

  @Test
  public void shouldReturnZeroAppointments() {
    Page<Appointment> pageAppointment = appointmentController
        .getAllAppointments(Pageable.unpaged());
    Assert.assertEquals(0, pageAppointment.getTotalElements());
  }

  @Test
  public void shouldReturnOneAppointments() {
    modelFactory.createAppointment();
    Page<Appointment> pageAppointment = appointmentController
        .getAllAppointments(Pageable.unpaged());
    Assert.assertEquals(1, pageAppointment.getTotalElements());
  }

  @Test
  public void shouldReturnAllAppointments() {
    modelFactory.createAppointment();
    modelFactory.createAppointment();
    modelFactory.createAppointment();
    modelFactory.createAppointment();
    modelFactory.createAppointment();
    Page<Appointment> pageAppointment = appointmentController
        .getAllAppointments(Pageable.unpaged());
    Assert.assertEquals(5, pageAppointment.getTotalElements());
  }
}