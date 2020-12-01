package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")//1 :N 관계 >order table에 있는 member을 맵핑(읽기전용)
    private List<Order> orders = new ArrayList<>();//필드에서 초기화하고 손대지말자!!



}
