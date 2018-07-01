package com.example.easynotes.repository;


import java.util.List;

import com.example.easynotes.model.Note;
import com.example.easynotes.model.vo.ReportFilterInfo;
import com.example.easynotes.model.FinanceRatioQ;
/**
 * Created by gkatzioura on 6/3/16.
 */
public interface NoteRepositoryCustom {
	public List<Note> getFirstNamesLike(String title);
	public List<FinanceRatioQ> getFinanceRatioFillter(List<ReportFilterInfo> list);
}