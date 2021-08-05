package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {
    //to make member' service, we need their repository first
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // Member Registeration
    // Requirmenet, no duplication name
    public Long join(Member member) {
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("Already registered");
//        });

        extracted(member);


        memberRepository.save(member);
        return member.getId(); //firstly, only return ID

        
    }

    private void extracted(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("Already registered");
        });
    }
}
