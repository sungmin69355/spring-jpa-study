package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED) protected로 new 생성 불가하게
public class OrderItem {
    @Id @GeneratedValue
    @Column(name= "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "oder_id")
    private Order order;

    private int orderPrice; //주문 가격
    private int count; //주문 수량

    protected OrderItem(){
        // new로 생성할수없도록 선언
    }

    //== 셍성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); //아이템의 재고를 줄여준다.
        return orderItem;
    }


    //==비지니스 로직==//
    public void cencel() {
        //재고 수량을 원복해주는역할
        getItem().addStock(count);
    }

    //==조회 로직==//
    /** 주문상품 전체 가격 조회 */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
