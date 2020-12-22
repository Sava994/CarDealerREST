package rc.springbootmongodb.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rc.springbootmongodb.Models.Ad;

import java.util.function.Predicate;

@Repository
public interface AdRepository extends MongoRepository<Ad, String> {
    @Query("{user_id : ?0}")
    Page<Ad> findByUserId(String id, Pageable pageable);
}
