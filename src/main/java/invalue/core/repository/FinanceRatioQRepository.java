package invalue.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import invalue.core.entity.FinanceRatioQ;


/**
 * Created by HUYNP4 on 15/09/2018.
 */

@Repository
public interface FinanceRatioQRepository extends JpaRepository<FinanceRatioQ, Long>,FinanceRatioQRepositoryCustom {

//    @Query("SELECT p FROM Note p WHERE 1=2")
//    public List<Note> find1();
}
