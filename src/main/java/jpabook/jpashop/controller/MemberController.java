package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm()); // form 에서 원하는 validation 과 도메인(Model)에서의 validation 이 다를 수 있기 때문에 MemberForm 를 따로 둔다
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        // BindingResult 가 없으면, 메소드 진입하지 않고 바로 error 로 튕기는데 있으면, result 를 담아서 메소드 안으로 진입함
        if (result.hasErrors()) {
            return "members/createMemberForm"; // 이미 입력된 form 데이터를 가지고 재진입하게 되므로 유지됨
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        try {
            memberService.join(member);
        } catch (IllegalStateException e) {
            result.addError(new FieldError("name", "name", e.getMessage()));
            return "members/createMemberForm";
        }

        return "redirect:/";
    }

    //추가
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // 엔티티를 직접 넘기기 보다는 dto를 만들어서 필요한 데이터만 변환하는게 좋음
        // API 를 만들 때는 엔티티를 그대로 반환해서는 안됨 <- 보안상 & API 스펙이 엔티티 수정에 따라 시시각각 변할 수 있음
        return "members/memberList";
    }
}
