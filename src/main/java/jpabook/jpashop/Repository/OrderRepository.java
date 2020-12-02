package jpabook.jpashop.Repository;


import jpabook.jpashop.Service.OrderService;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.JoinType;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);

    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {


       return em.createQuery("select o  from Order o join o.member m" +
                " where o.status = :status" +
                " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName()) //위 2개는 파라미터 바인딩
                .setMaxResults(1000)//최대 조회 갯수
                .getResultList();

    }
    /**
     * JPA Criteria 동적쿼리 작성
     */
    //public List<Order> findAllByCriteria(OrderSearch orderSearch) {
   /* CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaBuilder<Order> cq = cb.createQuery(Order.class);
    Root<Order> o = cq.fro(Order.class);
    Join<Object,Object> m = o.join("member", JoinType.INNER);

    List<Predicate> criteria = new ArrayList<>();

    //주문 상태 검색
    if(orderSearch.getOrderStatus()!= null) {
        cb.equal(o.get("status"), orderSearch.getOrderStatus());
        criteria.add(status);

    //회원 이용 검색
    if(orderSearch.getOrderStatus()!= null) {
        cb.equal(o.get("status"), orderSearch.getOrderStatus());
        criteria.add(status);
    }

    cq.where(cb.and(criteria.toArray(new Predicate(criteria.size()))));
        TypedQuery<Object> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }*/


}
