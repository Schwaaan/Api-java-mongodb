package com.test.src.service.impl;

import com.test.src.domain.Appointment;
import com.test.src.domain.ClassificationType;
import com.test.src.domain.Clipping;
import com.test.src.domain.Notification;
import com.test.src.repository.NotificationRepository;
import com.test.src.service.ClippingService;
import com.test.src.repository.AppointmentRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClippingServiceImpl implements ClippingService {

  @Autowired
  private AppointmentRepository appointmentRepository;
  @Autowired
  private NotificationRepository notificationRepository;

  @Override
  public void validations(Clipping clipping) {
    if (clipping.getClassificationType() == ClassificationType.HEARING) {
      Appointment appointment = new Appointment();
      if (clipping.getClassifiedDate() != null) {
        appointment.setCreated_at(clipping.getClassifiedDate());
      }
      appointment.setDescription("New appointment received");
      appointmentRepository.save(appointment);
    }
    this.createNotification(clipping);
  }

  public void createNotification(Clipping clipping) {
    if (clipping.isImportant()) {
      Notification notification = new Notification();
      notification.setCreated_at(new Date());
      notification.setDescription("New clipping received");
      notificationRepository.save(notification);
    }
  }
}
