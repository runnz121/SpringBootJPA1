package jpabook.jpashop.Repository;


import jpabook.jpashop.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {//item은 jpa 저장전까지 id값이 없다.
        if(item.getId()== null) {//따라서 여기서 DB조회해서 없는 경우
            em.persist(item);    //item값을 저장한다.
        }else {
            em.merge(item);      //DB에 이미 있는 경우 업데이트한다
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);

    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
