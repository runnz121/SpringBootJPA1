package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //N: 1 관계
    @JoinColumn(name = "memeber_id") //FK id , join시 해당 아이디로 join
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)//persist 선언을 연관된 것들에 자동으로 해준다
    private List<OrderItem> orderItems = new ArrayList<>(); //필드에서 초기화하고 손대지말자!!

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id") //delivery와의 관계에서 주인임
    private Delivery delivery;


    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;//enum타입(주문, 캔슬)

    //연관관계 메서드 //
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    //원래는 이렇게 작성해야 함(양방향 관계 있는 것들을 서로 묶기 위해)
    //두 매서드에 서로 관련 값이 들어갈때
   /* public static void main(String[] args) {
        Member member = new Member();
        Order order = new Order();

        member.getOrders().add(order);
        order.setMember(member);

    }*/

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성메서드 ==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;

    }

    //==비즈니스 로직 ==//
    /**
     * 주문취소
     */
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem: this.orderItems) { // for( A : B) B에서 객체를 꺼내 A에 넣는다
            orderItem.cancel();
        }
    }
    //==조회 로직 ==/
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }


}
