package jpabook.jpashop;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class HelloControler {

    @GetMapping("hello")
    public String hello(Model model) { //model : model에 데이터를 실어서 controller 에 데이터를 넘길 수 있다.
        model.addAttribute("data", "hello"); //이값을 html 페이지에 넘긴다
        return "hello"; //hello 라는 view 이름으로 (html 페이지 이름) 해당 결과가 반환
    }

}
