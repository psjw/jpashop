package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne(fetch = FetchType.EAGER) //ManyToOne은 기본값 : EAGER(즉시로딩) order 조회시 Member를 join해서 같이 가져옴 -> em.find() 한건 조회시
//    JPQL select o From order o -> SQL select * from order 시 Member 다 가져옴 -> n+1문제 ->101번 호출 order 쿼리 1번 member 쿼리 100번 조회
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //OneToMany는 기본값 : LAZY(지연로딩) -> fetch join , 엔티티 그래프 사용해서 해결
    private List<OrderItem> orderItems = new ArrayList<>();  //초기화의 best좋음 -> null 문제에 안전, 하이버네이트 내장 컬렉션으로 변경됨(한번 감싸게됨) 방지해야됨
    //cascade = CascadeType.ALL -> orderitems스를 저장하고 order를 저장해야됨
    // em.persist(orderItemA) , em.persist(orderItemB), em.persist(orderItemC), em.persist(order) 순으로 저장해야댐
    // cascade = CascadeType.ALL ->  em.persist(order) 하나만 써주면 다 저장됨

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//OneToOne는 기본값 : LAZY
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //SpringPhysicalNamingStrategy의 의해 -> 1. 카멜케이스(order_date)변경 , . -> _, 대문자 -> 소문자
    private LocalDateTime orderDate; //주문시간 (java8이상) -> 날짜관련 맵핑안해줘도됨

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    //연관관계 편의 메서드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderITem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }


    //원래라면 이렇게 양쪽으로 넣어주어야함
    /*public static void main(String[] args) {
        Member member = new Member();
        Order order = new Order();
        member.getOrders().add(order);
        order.setMember(member);
    }*/
}
