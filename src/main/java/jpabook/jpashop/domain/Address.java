package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable//내장타입
@Getter
//@Setter //값타입은 Immutable하게 설계
public class Address {
    private String city;
    private String street;
    private String zipcode;

    //@Embeddable @Entity 기본생성자를 생성해야됨 JPA가 인식을 못함 -> protected로 생성  
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
