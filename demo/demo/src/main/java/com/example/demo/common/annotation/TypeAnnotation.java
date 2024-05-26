package com.example.demo.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 클래스, 인터페이스, ENUM 이런 타입에서 선언할 수 있는 어노테이션이기 때문에 클래스 위쪽에 선언.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TypeAnnotation {
  String name();

  String value();
}
