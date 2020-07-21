package com.test.utils;

import de.flapdoodle.embed.mongo.MongodStarter;
import org.junit.Rule;
import org.springframework.stereotype.Component;

@Component
public class LocalMongo {

  @Rule
  public MongodStarter starter = MongodStarter.getDefaultInstance();

}
