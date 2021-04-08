package net.pay.you.back.request.manager.dao;

import net.pay.you.back.request.manager.domain.DocVersion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentVersionDAO extends MongoRepository<DocVersion, Long> {

}
