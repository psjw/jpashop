package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){
            em.persist(item); //최초 insert
        }else{
            em.merge(item); //update 
            //파라미터는 영속성 컨텍스가 안됨 리턴 값이 영속성임
            //모든 item의 값이 세팅되어 버림
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findALl(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }


}
