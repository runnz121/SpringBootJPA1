package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable //내장이 될 수 있따
@Getter
public class Address {



    private String city;
    private String street;
    private String zipcode;

    protected Address(){ //생성자 생성후 보호 차원에서 지정

    }//값 타입은 변경 불가능하게 설계해야한다

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
