package io.joonhak.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post {
	
	@Id @GeneratedValue
	private Long id;
	private String title;
	private String author;
	private String content;
	
	public Post(String title, String author, String content) {
		this.title = title;
		this.author = author;
		this.content = content;
	}
	
}
