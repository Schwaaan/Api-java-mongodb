package com.test.src.web;

import com.test.src.domain.Notification;
import com.test.src.service.ClippingService;
import com.test.src.domain.Clipping;
import com.test.src.repository.ClippingRepository;
import com.test.src.utils.Assert;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
public class ClippingController {

  private ClippingService clippingService;
  private ClippingRepository clippingRepository;

  public ClippingController(
      ClippingRepository clippingRepository, ClippingService clippingService) {
    this.clippingRepository = clippingRepository;
    this.clippingService = clippingService;
  }

  @PostMapping(path = "/v1/clippings")
  public Clipping createClippings(@RequestBody Clipping clipping) {
    clippingService.validations(clipping);
    return clippingRepository.save(clipping);
  }

  @GetMapping("/v1/clippings")
  public Page<Clipping> getAllClippings(@PageableDefault Pageable pageable) {
    return clippingRepository.findAllByDeletedIsFalse(pageable);
  }

  @GetMapping("/v1/clippings/{id}")
  public Clipping getOneClippingById(@PathVariable String id) {
    return Assert.found(clippingRepository.findOneByIdAndDeletedIsFalse(id), "Entity not found");
  }

  @PutMapping("/v1/clippings/{id}")
  public Clipping markClippingViewed(@PathVariable String id,
      @RequestBody Clipping clippingBody) {
    Clipping clipping = Assert
        .found(clippingRepository.findOneByIdAndDeletedIsFalse(id), "Entity not found");
    clipping.setViewed(clippingBody.isViewed());
    return clippingRepository.save(clipping);
  }

  @DeleteMapping("/v1/clippings")
  public String deleteOneOrAll(@RequestParam(value = "id", required = false) String id) {
    if (StringUtils.isEmpty(id)) {
      List<Clipping> clippingList = clippingRepository.findAll();
      for (Clipping clipping : clippingList) {
        clipping.setDeleted(true);
        clippingRepository.save(clipping);
      }
      return "Entities Deleted";
    }
    Clipping clipping = Assert
        .found(clippingRepository.findOneByIdAndDeletedIsFalse(id), "Entity not found");
    clipping.setDeleted(true);
    clippingRepository.save(clipping);
    return "Entity Deleted";
  }
}