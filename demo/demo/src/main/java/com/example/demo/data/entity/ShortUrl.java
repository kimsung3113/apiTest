package com.example.demo.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "short_url")
public class ShortUrl extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)	// 알아서 테이블을 읽어서 인덱스를 가져온다.
  private Long id;

  @Column(nullable = false, unique = true)
  private String hash;

  @Column(nullable = false, unique = true)
  private String url;

  @Column(nullable = false, unique = true)
  private String orgUrl;
  
  
  public ShortUrl(String orgUrl, String url, String hash) {
	  
	  this.orgUrl = orgUrl;
	  this.url = url;
	  this.hash = hash;
  }
  
}


