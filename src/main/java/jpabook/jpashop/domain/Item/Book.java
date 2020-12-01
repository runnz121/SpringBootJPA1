package jpabook.jpashop.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("B") //single table이라서 dtype을 통해 아이템 종류를 분류
public class Book extends Item { //상속관계 : extends

   private String author;
   private String isbn;

}
