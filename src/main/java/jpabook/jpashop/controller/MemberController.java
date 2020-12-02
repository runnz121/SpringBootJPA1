package jpabook.jpashop.controller;

import jpabook.jpashop.Service.MemberService;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService; // final은 1. 초기화를 한번만 하거나 2. 재할당 방지 2가지 이유로 쓰이는데 매소드가 여러번 불러지기 때문에 final을 쓰지 않으면 계속 변수초기화 되기 떄문에 꼭 지정해주어야 한다.

    @GetMapping("/members/new") //내용을 페이지에서 전달 받음
    public String creatForm(Model model) { //여기에서 model이란 https://velog.io/@msriver/Spring-Model-%EA%B0%9D%EC%B2%B4
        model.addAttribute("memberForm", new MemberForm()); // Controller 에서 View로 넘어갈때 model 이라는 객체에 memberform 을 담아 넘긴다
        return "members/createMemberForm";

    }

    @PostMapping("/members/new") //페이지에서 내용을 전달 받음
    public String create(@Valid MemberForm form, BindingResult result)  {

        if(result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member); //맨 위에 변수에 final을 붙이지 않으면 nullpointexception 발생

        return "redirect:/"; //첫번째 페이지로 리턴
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);//key ,list 꺼내옴
        return "members/memberList";
    }

}
