package hello.core.singleton;

public class StatefulService {
    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제
    }
    // 상태 유지 안하게 만들면 이렇다.
//    public int order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        return price;
//    }

    public int getPrice() {
        return price;
    }
}
