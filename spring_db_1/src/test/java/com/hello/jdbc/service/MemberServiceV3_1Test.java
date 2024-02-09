package com.hello.jdbc.service;

import com.hello.jdbc.domain.Member;
import com.hello.jdbc.repository.MemberRepositoryV3;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

import static com.hello.jdbc.connection.ConnectionConst.*;

/**
 * 트랜잭션 - 트랜잭션 매니저
 * */
class MemberServiceV3_1Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberServiceV3_1 memberServiceV3_1;
    private MemberRepositoryV3 memberRepositoryV3;

    @BeforeEach
    void before() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        memberRepositoryV3 = new MemberRepositoryV3(dataSource);
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        memberServiceV3_1 = new MemberServiceV3_1(transactionManager, memberRepositoryV3);
    }

    @AfterEach
    void afterEach() throws SQLException {
        memberRepositoryV3.delete(MEMBER_A);
        memberRepositoryV3.delete(MEMBER_B);
        memberRepositoryV3.delete(MEMBER_EX);
    }

    @Test
    @DisplayName("정상 이체")
    public void accountTransfer() throws Exception {
        // given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        memberRepositoryV3.save(memberA);
        memberRepositoryV3.save(memberB);

        // when
        memberServiceV3_1.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);

        // then
        Member findMemberA = memberRepositoryV3.findById(memberA.getMemberId());
        Member findMemberB = memberRepositoryV3.findById(memberB.getMemberId());

        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(8000);
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체 중 예외 발생")
    public void accountTransferEx() throws Exception {
        // given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_EX, 10000);
        memberRepositoryV3.save(memberA);
        memberRepositoryV3.save(memberB);

        // when
        Assertions.assertThatThrownBy(() -> memberServiceV3_1.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000))
                .isInstanceOf(IllegalStateException.class);

        // then
        Member findMemberA = memberRepositoryV3.findById(memberA.getMemberId());
        Member findMemberB = memberRepositoryV3.findById(memberB.getMemberId());

        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(10000);
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(10000);
    }
}