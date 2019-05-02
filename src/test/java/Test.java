import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String []args){
        Set<Integer> set = new HashSet<>();
        set.add(11);
        set.add(22);
        set.add(33);
        for (Integer integer:
             set) {
            System.out.println(integer);
        }
    }
}
