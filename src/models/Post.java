package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "posts")
@NamedQueries({
	@NamedQuery(
			name = "getAllPosts",
			query = "SELECT p FROM Post AS p ORDER BY p.updated_at DESC"
			),
	@NamedQuery(
			name = "getAllPostsCount",
			query = "SELECT COUNT(p) FROM Post AS p"
			),
	@NamedQuery(
			name = "getMyPosts",
			query = "SELECT p FROM Post AS p WHERE p.writer = :login_user"
			),
	@NamedQuery(
			name = "getMyPostsCount",
			query = "SELECT COUNT(p) FROM Post AS p WHERE p.writer = :login_user"
			),
	@NamedQuery(
			name = "getGoodPost",
			query = "SELECT p FROM Post AS p WHERE p.id = :goodpost_id"
			),
	@NamedQuery(
			name = "getAllDestroyPost",
			query = "SELECT p FROM Post AS p WHERE p.writer = :destroy_writer"
			)
	
})
@Entity
public class Post {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "writer", nullable = false)
    private User writer;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Lob
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "registration", nullable = false)
	private Timestamp registration;
	
	@Column(name = "updated_at", nullable = false)
	private Timestamp updated_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegistration() {
		return registration;
	}

	public void setRegistration(Timestamp registration) {
		this.registration = registration;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
