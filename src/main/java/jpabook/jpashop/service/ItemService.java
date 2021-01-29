package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }
    
    @Transactional
    public void updateITem(Long itemId, String name, int price, int stockQuantity){ //파미터 많으면 Dto사용하자
        Item findItem = itemRepository.findOne(itemId);
        //findItem으로 찾아오면 영속상태
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems(){
        return itemRepository.findALl();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
