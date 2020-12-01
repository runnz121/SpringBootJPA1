package jpabook.jpashop.domain.Item;


import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockExcpetion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //하나의 테이블에 모두 관리
public abstract class Item { //추상 클래스

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;


    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>(); //필드에서 초기화하고 손대지말자!!


    //== 비즈니스 로직 ==//

    /**재고증가 로직
     *
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;

    }
    /**
     * 재고감소 로직
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock<0) {
            throw new NotEnoughStockExcpetion("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
