package invalue.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import invalue.core.entity.FinanceRatioY;


/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface FinanceRatioYRepository extends JpaRepository<FinanceRatioY, Long>,FinanceRatioYRepositoryCustom {


}
