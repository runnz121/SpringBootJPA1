package jpabook.jpashop.controller;


import jpabook.jpashop.Service.ItemService;
import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        //setter는 모두닫아 놓는게 좋다 method로 빼라 이말이야야
       Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";

    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    /**
     * 상품 수정
     * */

    @GetMapping("/items/{itemId}/edit") //가운데는 path variable 로 넣어준다
    public String updateItemForm(@PathVariable("itemId")Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit") //merge로 부터 가져온 item 값을 모두 바꿔치기 해서 저장
    public String updateItem (@PathVariable String itemId, @ModelAttribute("form") BookForm form) {

        Book book = new Book();      // 여기서 Book객체는 DB에 들어갔다가 호출되어서 나왔는데 이러한 객체를 "준영속엔티티"라고한다 = 영속성 컨텍스트(JPA)가 더 이상 관리하지않는다
        book.setId(form.getId());       //따라서 JPA가 관리하지 않기때문에 upadate 쿼리를 날리지 않아 DB에 저장하지 않는다.
        book.setName(form.getName());     //이를 해결하기 위해 변경감지기능 or merge를 쓴다. >여기에 작성된건 merge방법
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);//book entity가 넘어간다 그런데 이값은 itemservice > itemrepository에 서 받아온 값인데,
                                   //repository의 if문의 merge에 의해 넘어온 값이다 이걸 반환
        return "redirect:/items";

    }


}
