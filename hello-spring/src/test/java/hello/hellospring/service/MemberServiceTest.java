package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    // To join, service is needed
    MemberService memberService;
    MemoryMemberRepository memberRepository; //for "Clean DB"

    @BeforeEach
    public void beforeEach(){
        memberService = new MemberService(memberRepository);
        memberRepository = new MemoryMemberRepository();
    }

    //Clear DB after each test,
    @AfterEach
    public void afterEach(){
        memberRepository.clearSotre();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello"); //even we change to "Spring", working well

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember =memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    // Test "Join" also important (50% of 100%), the other 50% test is below
    // But Dealing with the Exception is more important. need to actually see our exception (duplication)
    @Test
    void exceptionDuplicatedMember() {

        //Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("Already registered");

        // This method also good..
        /*try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("Already registered");
        }*/

        // Rather than this, we use different way, using assertThrows... above.



        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}