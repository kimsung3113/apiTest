package com.example.demo.data.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass	// 상속을 받은 필드들을 shortUrlEntity에 포함을 시켜준다.
@EntityListeners(AuditingEntityListener.class)	// entity가 사용되는 시점에서 create나 update가되는 전 시점인지 후 시점인지
// listener가 이벤트를 받아들인 후에 특정행동들을 해주는 그런 작업을 한다.
public class BaseEntity {

  @CreatedDate	// 저장하는 시점에 값이 필드에 자동으로 주입이 된다.
  @Column(updatable = false)	// 개발자가 임의로 값을 고칠 수 없게 한다.
  private LocalDateTime createdAt;

  /*
  @CreatedBy
  @Column(updatable = false)
  private String createdBy;
  */

  @LastModifiedDate // 저장하는 시점에 값이 필드에 자동으로 주입이 된다.
  private LocalDateTime updatedAt;

  /*
  @LastModifiedBy
  private String updatedBy;
  */

}
