package invalue.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import invalue.core.entity.User;


/**
 * Created by HUYNP4 on 28/11/2018.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

//    @Query("SELECT p FROM Note p WHERE 1=2")
//    public List<Note> find1();
}
