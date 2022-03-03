package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //싱글테이블 전략을 사용 들어가보면 TABLE_PER_CLASS, JOINED도 있다.
@DiscriminatorColumn(name="dtype") //구분자
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;



    private String name;
    private int price;
    private int stockQuantity;
}
