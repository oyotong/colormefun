/**
 * Created by andytang on 11/7/14.
 */
public class Test {
    public static void main(String args[]){
        String ser_type = "PUesDs".replaceAll("(es|s)$","").toUpperCase();
        System.out.print(ser_type);
    }
}
