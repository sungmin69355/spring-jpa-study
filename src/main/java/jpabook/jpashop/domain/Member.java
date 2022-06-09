package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id") //테이블칼람
    private Long id;
    private String name;

    @Embedded //내장타입이라고 둘다써줘도된다.
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member") //Order에서 member 필드위에서 매핑인걸 인지
    private List<Order> orders = new ArrayList<>();
}
