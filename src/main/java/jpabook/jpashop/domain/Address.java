package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // jpa 생성할 때 리플렉션이나 프록시 같은 기술을 쓰는데, 이때 기본 생성자가 있어야함
    // 대신 protected로 해두면 명시적으로 알 수 있음
    protected Address() {

    }

    // Embeddable; 변경이 되어서는 안되므로 setter은 두지 않고 생성 시에 set되도록 함
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
