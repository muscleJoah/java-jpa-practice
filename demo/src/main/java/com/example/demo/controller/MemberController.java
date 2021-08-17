package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RequestMapping("/member")
@RestController
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/gettest1")
    public Member memberTest(){
        return new Member(12, "jaeww",23,"제주", new Date());
    }
    @GetMapping("/gettest2")
    public ArrayList<Member> memberTest2(){
        ArrayList<Member> mList = new ArrayList<Member>();
        mList.add(new Member(11,"히철",20, "제주",new Date()));
        mList.add(new Member(11,"재철",23, "서울",new Date()));
        return mList;
    }

    @PostMapping("/insert")
    public Member insert(@RequestBody Map<String,String> map){
        return memberRepository.save(new Member(map.get("name"),intParser(map.get("age")) ,map.get("address")));
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id){
        memberRepository.deleteById(id);
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

