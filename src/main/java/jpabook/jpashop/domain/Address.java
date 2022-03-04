package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //내장타입
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //기본생성자로 protected로 막아준다.
    protected Address() {
    }

    //주소 같은 경우 실무에서 생성할떄만 값을 넣어준다.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
