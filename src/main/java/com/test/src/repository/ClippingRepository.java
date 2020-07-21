package com.test.src.repository;

import com.test.src.domain.Clipping;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClippingRepository extends MongoRepository<Clipping, String> {

  Optional<Clipping> findOneByIdAndDeletedIsFalse(String id);

  Page<Clipping> findAllByDeletedIsFalse(Pageable pageable);
}
