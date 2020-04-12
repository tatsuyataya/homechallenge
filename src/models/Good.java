package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "good")
@NamedQueries({
	@NamedQuery(
			name = "checkGoodOrNot",
			query = "SELECT COUNT(g) FROM Good AS g WHERE g.good_from = :good_from AND g.good_post = :good_post"
			),
	@NamedQuery(
			name = "goodKaijo",
			query = "SELECT g FROM Good AS g WHERE g.good_from = :good_from AND g.good_post = :good_post"
			),
	@NamedQuery(
			name = "getAllMyGood",
			query = "SELECT g FROM Good AS g WHERE g.good_from = :good_from"
			),
	@NamedQuery(
			name = "getMyFavoritePost",
			query = "SELECT g FROM Good AS g WHERE g.good_from = :me"
			),
	@NamedQuery(
			name = "countMyFavoritePost",
			query = "SELECT COUNT(g) FROM Good AS g WHERE g.good_from = :me"
			)
})
@Entity
public class Good {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "good_from", nullable = false)
	private User good_from;
	
	@ManyToOne
	@JoinColumn(name = "good_post", nullable = false)
	private Post good_post;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getGood_from() {
		return good_from;
	}

	public void setGood_from(User good_from) {
		this.good_from = good_from;
	}

	public Post getGood_post() {
		return good_post;
	}

	public void setGood_post(Post good_post) {
		this.good_post = good_post;
	}

}
