package telran.favorites.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.favorites.domain.entities.MessagingEntity;

//@Repository
//Можно не писать аннотацию, потому-что аннотация есть в монго репозиторий
public interface MessagingRepository extends MongoRepository<MessagingEntity, String> {

//	List<MessagingEntity> findAllBy(Pageable pageable);
//	List<MessagingEntity> findAllByTypePost(boolean typePost);
	
}
