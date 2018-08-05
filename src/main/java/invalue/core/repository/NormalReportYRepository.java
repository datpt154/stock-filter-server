package invalue.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import invalue.core.entity.FinanceRatioQ;
import invalue.core.entity.NormalReportY;


/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface NormalReportYRepository extends JpaRepository<NormalReportY, Long> {

//    @Query("SELECT p FROM Note p WHERE 1=2")
//    public List<Note> find1();
}
