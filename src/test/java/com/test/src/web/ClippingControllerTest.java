package com.test.src.web;

import com.test.src.domain.Appointment;
import com.test.src.domain.ClassificationType;
import com.test.src.domain.Clipping;
import com.test.src.domain.Notification;
import com.test.src.repository.AppointmentRepository;
import com.test.src.repository.ClippingRepository;
import com.test.src.repository.NotificationRepository;
import com.test.src.service.ClippingService;
import com.test.utils.ModelFactory;
import java.util.List;
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
public class ClippingControllerTest {

  @Autowired
  private ModelFactory modelFactory;
  @Autowired
  private ClippingController clippingController;
  @Autowired
  private ClippingService clippingService;
  @Autowired
  private ClippingRepository clippingRepository;
  @Autowired
  private NotificationRepository notificationRepository;
  @Autowired
  private AppointmentRepository appointmentRepository;


  @Before
  public void init() {
    modelFactory.cleanAll();
    clippingController = new ClippingController(clippingRepository, clippingService);
  }

  @Test
  public void shouldReturnOneClippingCreated() {
    clippingController.createClippings(modelFactory.returnClipping());
    Page<Clipping> clippings = clippingController.getAllClippings(Pageable.unpaged());
    Assert.assertEquals(1, clippings.getTotalElements());
  }

  @Test
  public void shouldReturnOneAppointment() {
    Clipping clipping = modelFactory.returnClipping();
    clipping.setClassificationType(ClassificationType.HEARING);
    clippingController.createClippings(clipping);
    List<Appointment> list = appointmentRepository.findAll();
    Assert.assertEquals(1, list.size());
    Assert.assertNotEquals(clipping.getClippingDate(), list.get(0).getCreated_at());
  }

  @Test
  public void shouldReturnOneNotificationBecauseClippingIsImportant() {
    Clipping clipping = modelFactory.returnClipping();
    clipping.setImportant(true);
    clippingController.createClippings(clipping);
    Page<Notification> notifications = notificationRepository
        .findAllByDeletedIsFalse(Pageable.unpaged());
    Assert.assertEquals(1, notifications.getTotalElements());
  }

  @Test
  public void shouldReturnOneClipping() {
    modelFactory.createClipping();
    Page<Clipping> clippings = clippingController.getAllClippings(Pageable.unpaged());
    Assert.assertEquals(1, clippings.getTotalElements());
  }

  @Test
  public void shouldReturnAllClippings() {
    modelFactory.createClipping();
    modelFactory.createClipping();
    modelFactory.createClipping();
    modelFactory.createClipping();
    modelFactory.createClipping();
    Page<Clipping> clippings = clippingController.getAllClippings(Pageable.unpaged());
    Assert.assertEquals(5, clippings.getTotalElements());
  }

  @Test
  public void shouldReturnOneClippingById() {
    Clipping clipping = modelFactory.createClipping();
    Clipping clippingReturn = clippingController.getOneClippingById(clipping.getId());
    Assert.assertEquals(clipping.getClippingMatter(), clippingReturn.getClippingMatter());
  }

  @Test
  public void markClippingViewed() {
    Clipping clippingBody = modelFactory.returnClipping();
    clippingBody.setViewed(true);
    Clipping clipping = modelFactory.createClipping();
    Clipping clippingReturn = clippingController.markClippingViewed(clipping.getId(), clippingBody);
    Assert.assertTrue(clippingReturn.isViewed());
  }

  @Test
  public void shouldReturnZeroBecauseDeletedAll() {
    modelFactory.createClipping();
    modelFactory.createClipping();
    modelFactory.createClipping();
    modelFactory.createClipping();
    modelFactory.createClipping();
    String clippings = clippingController.deleteOneOrAll("");
    Page<Clipping> clippingList = clippingRepository.findAllByDeletedIsFalse(Pageable.unpaged());
    Assert.assertEquals("Entities Deleted", clippings);
    Assert.assertEquals(0, clippingList.getTotalElements());
  }

  @Test
  public void shouldReturnOneBecauseDeletedOneById() {
    Clipping clipping = modelFactory.createClipping();
    modelFactory.createClipping();
    String clippings = clippingController.deleteOneOrAll(clipping.getId());
    Page<Clipping> clippingList = clippingRepository.findAllByDeletedIsFalse(Pageable.unpaged());
    Assert.assertEquals("Entity Deleted", clippings);
    Assert.assertEquals(1, clippingList.getTotalElements());
  }
}