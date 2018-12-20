package invalue.core.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import invalue.core.entity.News;
import invalue.core.util.DateUtil;
import invalue.core.util.StringUtil;

public class NewsDTO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String createdTime;

	private String title;

	private String thumbnailUrl;

	private List<String> listTag;

	private String content;

	private Integer categoryId;
	
	public NewsDTO() {
		// TODO Auto-generated constructor stub
	}
	public NewsDTO(News news) {
		this.id=news.getId();
		this.createdTime=DateUtil.toDateString(news.getCreatedTime(), DateUtil.DATE_FORMAT_STR_PRO);
		this.title=news.getTitle();
		this.thumbnailUrl=news.getThumbnailUrl();
		if(!StringUtil.isNullOrEmpty(news.getTag())) {
			String [] arrTag=news.getTag().split("#");
			this.listTag= new ArrayList<>();
			for(String tag :arrTag) {
				this.listTag.add(tag);
			}
		}
		this.content=news.getContent();
		this.categoryId=news.getCategoryId();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public List<String> getListTag() {
		return listTag;
	}

	public void setListTag(List<String> listTag) {
		this.listTag = listTag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
}