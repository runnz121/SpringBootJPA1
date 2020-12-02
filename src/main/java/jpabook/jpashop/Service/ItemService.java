package jpabook.jpashop.Service;


import jpabook.jpashop.Repository.ItemRepository;
import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    @Transactional//위에 readonly라고 선언해서 저장하기 위해 따로 지정
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /*@Transactional
    public void updateItem(Long itemId, Book param) {
        Item findItem = itemRepository.findOne(itemId); //id를 찾아온다
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());
        ..
        .
        .


    }*/ //==변경감지 기법 : findItem 에 준영속 객채임으로 저렇게 set get으로만 꺼내와서 쓰기만 하면 된다.
        // 마지막에 @Transactional에 자동으로 parsist된다.

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
