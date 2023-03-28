package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter// getter setter 알아서 만들어줌
@Setter // 생성자 관련해서도 롬복이 지원해줌
@ToString
public class HelloLombok {

    private String name;
    private int age;


    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfge");

        String name1 = helloLombok.getName();
        System.out.println("name1 = " + name1);

        System.out.println(helloLombok);
    }

}
