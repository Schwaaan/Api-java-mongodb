package com.test.utils;


import com.test.src.domain.Appointment;
import com.test.src.domain.Clipping;
import com.test.src.domain.Notification;
import com.test.src.repository.AppointmentRepository;
import com.test.src.repository.ClippingRepository;
import com.test.src.repository.NotificationRepository;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@AllArgsConstructor
@Service
@ApplicationScope
@Import({LocalMongo.class})
@Data
public class ModelFactory {

  private ClippingRepository clippingRepository;
  private NotificationRepository notificationRepository;
  private AppointmentRepository appointmentRepository;

  public void cleanAll() {
    clippingRepository.deleteAll();
    notificationRepository.deleteAll();
    appointmentRepository.deleteAll();
  }

  public Appointment createAppointment() {
    Appointment appointment = new Appointment();
    appointment.setDescription("Appointment test");
    return appointmentRepository.save(appointment);
  }

  public Clipping createClipping() {
    Clipping clipping = new Clipping();
    clipping.setClippingMatter("Clipping test");
    clipping.setClippingDate(new Date());
    clipping.setImportant(false);
    clipping.setViewed(false);
    return clippingRepository.save(clipping);
  }

  public Clipping returnClipping(){
    Clipping clipping = new Clipping();
    clipping.setClippingMatter("Clipping test - not saved");
    clipping.setClippingDate(new Date());
    clipping.setImportant(false);
    clipping.setViewed(false);
    return clipping;
  }

  public Notification createNotification() {
    Notification notification = new Notification();
    notification.setDescription("Notification test");
    return notificationRepository.save(notification);
  }

}
