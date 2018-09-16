package invalue.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import invalue.core.entity.RecommendationsOfStock;
import invalue.core.entity.Stock;


/**
 * Created by HUYNP4 on 15/09/2018.
 */

@Repository
public interface RecommendationsOfStockRepository extends JpaRepository<RecommendationsOfStock, Long>, RecommendationsOfStockRepositoryCustom {

}
