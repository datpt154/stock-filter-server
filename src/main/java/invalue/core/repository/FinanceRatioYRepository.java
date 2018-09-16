package invalue.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import invalue.core.entity.FinanceRatioY;


/**
 * Created by HUYNP4 on 15/09/2018.
 */

@Repository
public interface FinanceRatioYRepository extends JpaRepository<FinanceRatioY, Long>,FinanceRatioYRepositoryCustom {


}
