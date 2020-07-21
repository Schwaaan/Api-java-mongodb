package com.test.src.web;

import com.test.src.domain.Notification;
import com.test.src.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
public class NotificationController {

  private NotificationRepository notificationRepository;

  public NotificationController(
      NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  @GetMapping("/v1/notifications")
  public Page<Notification> getAllNotifications(@PageableDefault Pageable pageable) {
    return notificationRepository.findAllByDeletedIsFalse(pageable);
  }
}
