package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberReposiory;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    private final MemberReposiory memberReposiory;

//    @Autowired
    public MemberService(MemberReposiory memberReposiory) {
        this.memberReposiory = memberReposiory;
    }

    public Long join(Member member){
        validateDuplicateMember(member);

        memberReposiory.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberReposiory.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }

    public List<Member> findMembers(){
        return memberReposiory.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberReposiory.findById(memberId);
    }
}
