package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

@WebAppConfiguration
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA book", 10000, 10); //이름, 가격, 재고
        int orderCount = 2;

        //when
        Long orderId =  orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order getOder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOder.getStatus());
        Assert.assertEquals("주문한 상품 종류 수가 정확해야 한다", 1, getOder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격 * 수량이다.",10000 * orderCount, getOder.getTotalPrice());
        Assert.assertEquals("주문 수량만큼 재고가 줄어야 한다 ",8,item.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA book", 10000, 10); //이름, 가격, 재고
        int orderCount = 11;

        //when
        // orderService.order(member.getId(), item.getId(), orderCount);

        //then
        assertThrows(NotEnoughStockException.class, ()->{
            orderService.order(member.getId(), item.getId(), orderCount);//예외가 발생해야 한다.
        });
    }

    @Test
    public void 주문취소() throws Exception {
        //given

        //when

        //then
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123")); em.persist(member);
        return member;
    }
    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }
}
