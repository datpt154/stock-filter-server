package invalue.core.entity;

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

import invalue.core.dto.NewsDTO;
import invalue.core.util.DateUtil;
import invalue.core.util.StringUtil;

@Entity
@Table(name = "news")
public class News implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_generator")
	@SequenceGenerator(name="news_generator", sequenceName = "news_seq", allocationSize=1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Basic
	@Column(name = "TYPE", nullable = false)
	private Integer type;
	
	@Basic
	@Column(name = "STATUS", nullable = false)
	private Integer status;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME", nullable = false, length = 19)
	private Date createdTime;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_TIME", length = 19, insertable = false, updatable = true)
	private Date updatedTime;
	
	@Basic
	@Column(name = "TITLE")
	private String title;
	
	@Basic
	@Column(name = "THUMBNAIL_URL")
	private String thumbnailUrl;
	
	@Basic
	@Column(name = "TAG")
	private String tag;
	
	@Basic
	@Column(name = "content")
	private String content;
	
	@Basic
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Basic
	@Column(name = "user_id")
	private Integer userId;

	public News() {
		// TODO Auto-generated constructor stub
	}
	public News(NewsDTO news) {
		this.id=news.getId();
		this.createdTime=DateUtil.parse(news.getCreatedTime(), DateUtil.DATE_FORMAT_STR_PRO);
		this.title=news.getTitle();
		this.thumbnailUrl=news.getThumbnailUrl();
		if(null!=news.getListTag() && !news.getListTag().isEmpty()) {
			this.setTag("");
			for(String tag :news.getListTag()) {
				this.setTag(this.getTag()+"#"+tag);
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}