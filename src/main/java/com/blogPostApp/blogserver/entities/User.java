package com.blogPostApp.blogserver.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String bio;

    @Column(nullable = false)
    private String role = "user";

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    @ManyToMany
    @JoinTable(name = "followers", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "following_user_id"))
    private List<User> following;

    public User() {
    }

    public User(int id, String username, String email, String password, String profilePicture, String bio, String role,
            List<Post> posts, List<Comment> comments, List<Like> likes, List<User> following) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.role = role;
        this.posts = posts;
        this.comments = comments;
        this.likes = likes;
        this.following = following;
    }

    // Implement methods from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the user's roles and authorities here
        // Example: You can return a list of SimpleGrantedAuthority based on user roles
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement custom logic for account expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement custom logic for account locking
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement custom logic for credentials expiration
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement custom logic for user status
    }

	public UserDetails toUserDetails() {
		// TODO Auto-generated method stub
		return null;
	}
    
    

    // Getters and setters
}
