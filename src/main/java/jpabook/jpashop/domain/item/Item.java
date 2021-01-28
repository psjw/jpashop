package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//Single Table전략 사용
//SINGLE_TABLE 한테이블에 다 넣음
//TABLE_PER_CLASS 3개의 테이블 Book Movie Album
//JOINED 가장 정규화
@DiscriminatorColumn(name="dtype") //구분컬럼
@Getter
@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
