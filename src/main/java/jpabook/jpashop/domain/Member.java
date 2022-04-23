package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    // 한명의 회원이 여러개의 주문을 할 수 있으므로, 그리고 mappedBy 의 경우, member_id 값은 orders 테이블에 저장되므로, 여기 있는 리스트는 거울일 뿐이다
    private List<Order> orders = new ArrayList<>();

}
