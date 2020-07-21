package com.test.src.repository;

import com.test.src.domain.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

  Page<Appointment> findAllByDeletedIsFalse(Pageable pageable);
}
