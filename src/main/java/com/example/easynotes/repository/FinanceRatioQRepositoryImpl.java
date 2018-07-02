package com.example.easynotes.repository;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.easynotes.model.Note;
import com.example.easynotes.model.vo.ReportFilterInfo;
import com.example.easynotes.model.FinanceRatioQ;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by gkatzioura on 6/3/16.
 */
@Repository
@Transactional(readOnly = true)
public class FinanceRatioQRepositoryImpl implements FinanceRatioQRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;
   
}