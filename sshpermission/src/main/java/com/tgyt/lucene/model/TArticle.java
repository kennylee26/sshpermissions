package com.tgyt.lucene.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TArticle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_article", catalog = "tgpermission")
public class TArticle implements java.io.Serializable {

	// Fields

	private Integer id;
	private String articleTitle;
	private String articleTag;
	private String articleContent;

	// Constructors

	/** default constructor */
	public TArticle() {
	}

	/** full constructor */
	public TArticle(String articleTitle, String articleTag,
			String articleContent) {
		this.articleTitle = articleTitle;
		this.articleTag = articleTag;
		this.articleContent = articleContent;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ARTICLE_TITLE")
	public String getArticleTitle() {
		return this.articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	@Column(name = "ARTICLE_TAG")
	public String getArticleTag() {
		return this.articleTag;
	}

	public void setArticleTag(String articleTag) {
		this.articleTag = articleTag;
	}

	@Column(name = "ARTICLE_CONTENT", length = 65535)
	public String getArticleContent() {
		return this.articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

}