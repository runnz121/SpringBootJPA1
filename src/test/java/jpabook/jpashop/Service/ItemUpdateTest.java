package jpabook.jpashop.Service;


import jpabook.jpashop.domain.Item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
       Book book =  em.find(Book.class, 1L  );

       book.setName("asdfasdf");

       //변경감지  == dirty checking :://변경분에 대해서 JPA가 자동으로 찾아서 반환
       //update 가능



    }
}
