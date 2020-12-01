package jpabook.jpashop.domain;


import jpabook.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany //item 과 N:N관계임
    @JoinTable(name = "category_item", //중간테이블을 맵핑해줘야함 >> 이 테이블은 N:N관계를 풀기 위한 table을 말함(Category_item)
    joinColumns = @JoinColumn(name ="category_id"), //해당 테이블의 column을 join 현재 엔티티를 참조하는 외래키
        inverseJoinColumns =  @JoinColumn(name = "item_id") //inversejoincolumn : 반대방향 엔티티를 참조하는 외래키
    )
    private List<Item> items = new ArrayList<>(); //필드에서 초기화하고 손대지말자!!


    //하위 두 객체는 부모 자식 관계를 보기 위해 서로 맵핑 한것(자기자신을맵핑
    @ManyToOne(fetch = FetchType.LAZY)//@ToOne은 기본 fetch type이 즉시(eager)이기 때문에 일일히 Lazy로 바꿔주어야 한다.
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>(); //필드에서 초기화하고 손대지말자!!

    //연관관계 메서드//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }



}
