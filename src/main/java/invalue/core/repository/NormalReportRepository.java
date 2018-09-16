package invalue.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import invalue.core.entity.FinanceRatioQ;
import invalue.core.entity.NormalReport;
import invalue.core.entity.NormalReportY;


/**
 * Created by HUYNP4 on 15/09/2018.
 */

@Repository
public interface NormalReportRepository extends JpaRepository<NormalReport, Long>,NormalReportCustom {


}
