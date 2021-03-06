package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name ="delivery_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY) //Order 테이블의 delivery 컬럼의 연관관계 매핑
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //Enum순서의 보장 ORDINAL 사용 x
    private DeliveryStatus status; //READY, COMP

}

