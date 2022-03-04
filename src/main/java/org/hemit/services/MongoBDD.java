package org.hemit.services;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;

@MongoEntity(collection="BDD")
public class MongoBDD extends PanacheMongoEntity {
  public String name;

  // will be persisted as a 'birth' field in MongoDB
  @BsonProperty("birth")
  public LocalDate birthDate;

}
