import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("::::");

        Boolean aBoolean = new Boolean("/true");
        System.out.println(aBoolean);

        int i = 5;
        i = i++ + ++i;
        System.out.println(i);

        String[] names = {"a", "b", "a"};
        String name = "a";
        Predicate predicate = name::equals;
        System.out.println(Stream.of(names).filter(predicate).count());
        name = "b";
        System.out.println(Stream.of(names).filter(predicate).count());


//        B b = new B();
//        b.show();

        List list = new ArrayList();
        list.add("one");
        list.add("one");
        list.add("one");

        list.stream().forEach(o -> {
            System.out.println(o);
            list.add(o + "new");
        });
    }
}

class B {
    B b = new B();

    public int show() {
        return (true ? null : 0);
    }
}
