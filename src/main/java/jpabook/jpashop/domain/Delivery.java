package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name ="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) //Order와의 관계에서 하위 관계임 그래서 mappedby로 mapping만 해줌
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //string으로 써줘야 enum 에 추가 되어도 상관없다
    private DeliveryStatus status;
}
