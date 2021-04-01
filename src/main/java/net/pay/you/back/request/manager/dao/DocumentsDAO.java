package net.pay.you.back.request.manager.dao;

import net.pay.you.back.request.manager.comm.DocumentCreator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsDAO extends MongoRepository<DocumentCreator, Long> {

}
