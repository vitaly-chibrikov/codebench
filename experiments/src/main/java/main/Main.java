package main;

import java.util.Date;
import java.util.Scanner;

/**
 * @author v.chibrikov
 */
public class Main {
    public static void main(String[] args) {
        long enable_beacon_timestamp = new Date(1424876956000L).getTime() / 1000;
        System.out.println(enable_beacon_timestamp);
        System.out.println((int) (enable_beacon_timestamp));
    }
}
