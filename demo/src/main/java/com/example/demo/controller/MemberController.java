package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/member")
@RestController
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

//    @GetMapping("/gettest1")
//    public Member memberTest() {
//        return new Member(12, "jaeww", 23, "제주", new Date());
//    }
//
//    @GetMapping("/gettest2")
//    public ArrayList<Member> memberTest2() {
//        ArrayList<Member> mList = new ArrayList<Member>();
//        mList.add(new Member(11, "히철", 20, "제주", new Date()));
//        mList.add(new Member(11, "재철", 23, "서울", new Date()));
//        return mList;
//    }
    @GetMapping("/get")
    @ApiOperation(value = "전체 멤버 조회", notes = "전체 멤버를 조회")
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @PostMapping("/insert")
    @ApiOperation(value = "멤버 추가" , notes = "멤버를 추가 한다.")
    public Member insert(@RequestBody Map<String, String> map) {
        return memberRepository.save(new Member(map.get("name"), intParser(map.get("age")), map.get("address")));
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        memberRepository.deleteById(id);
    }

    @PostMapping("/update/{id}")
    public Member updateOne(@PathVariable("id") long id, @RequestBody Map<String, String> map) {
        Member member = memberRepository.findById(id).orElse(null);
        member.setName(map.get("name"));
        member.setAge(intParser(map.get("age")));
        member.setAddress(map.get("address"));

        return memberRepository.save(member);

    }
    int intParser(String age){
        try{
            return Integer.parseInt(age);
        } catch(ClassCastException e){
            e.printStackTrace();
            return 0;
        }
    }

}

