package shop.company.util;

/**
 * Created by tong on 2014/7/21.
 */
public class Site {
    public static enum  LetMeKnow{
        Yes("Y"),No("N");
        private String type = "Y";
        LetMeKnow(String y) {
            this.type = y;
        }
        public String toString() {
            return type;
        }
    }
    public static enum  Active{
        Yes("Y"),No("N");
        private String type = "Y";
        Active(String y) {
            this.type = y;
        }
        public String toString() {
            return type;
        }
    }
}
