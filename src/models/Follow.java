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

@Table(name = "follow")
@NamedQueries({
	@NamedQuery(
			name = "checkFollowOrNot",
			query = "SELECT COUNT(f) FROM Follow AS f WHERE f.follower = :follower AND f.followed = :followed"
			),
	@NamedQuery(
			name = "FollowKaijo",
			query = "SELECT f FROM Follow AS f WHERE f.follower = :follower AND f.followed = :followed"
			),
	@NamedQuery(
			name = "getFollowMe",
			query = "SELECT f FROM Follow AS f WHERE f.followed = :me"
			),
	@NamedQuery(
			name = "getMyFollowed",
			query = "SELECT f FROM Follow AS f WHERE f.follower = :me"
			),
	@NamedQuery(
			name = "getAllMyFollow",
			query = "SELECT f FROM Follow AS f WHERE f.follower = :follower"
			)
})
@Entity
public class Follow {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "follower", nullable = false)
	private User follower;
	
	@ManyToOne
	@JoinColumn(name = "followed", nullable = false)
	private User followed;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowed() {
		return followed;
	}

	public void setFollowed(User followed) {
		this.followed = followed;
	}
	
	

}
