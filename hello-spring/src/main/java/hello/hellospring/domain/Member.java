package hello.hellospring.domain;

import javax.persistence.*;

@Entity // jpa가 관리하는 entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 입력하는게 아니라 DB가 알아서 생성하는 것
    private Long id;
    
    @Column(name="name") // DB의 username 열이랑 연결
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
