package jm.task.core.jdbc;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        System.out.println("------------- VL --------------------");
        String stest = "abcd";
        String stest2 = "123";
        System.out.println("s1 = " + (stest.getBytes())[0] + "   --- s2 = " + ((stest2 == null)? 0 : (stest2.getBytes().length<=0)? 0 : (stest2.getBytes())[0]));
    }
}
