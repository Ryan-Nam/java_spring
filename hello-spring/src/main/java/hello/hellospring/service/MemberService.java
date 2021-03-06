package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    //to make member' service, we need their repository first
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Member Registeration
    // Requirmenet, no duplication name
    public Long join(Member member) {
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("Already registered");
//        });

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId(); //firstly, only return ID
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("Already registered");
        });
    }

    // All Member finding
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional <Member> findOne (Long memberId){
        return memberRepository.findById(memberId);
    }
}
