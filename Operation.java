import java.util.HashMap;
import java.util.Map;

/**
 * 功能：操作符
 *
 * @author xul
 * @create 2018-04-19 18:39
 */
public class Operation {
    private final static Map<String, Sign> operations = new HashMap() {{
        put("=", new Sign("==", "EQ"));  //等于
        put("!=", new Sign("!=", "NE"));  //不等于
        put(">", new Sign(">", "GT"));  //大于
        put(">=", new Sign(">=", "GTE"));  //大于等于
        put("<", new Sign("<", "LT"));  //小于
        put("<=", new Sign("<=", "LTE"));  //小于等于
        put("like", new Sign("", "LIKE"));  //LIKE
        put("in", new Sign("in", "IN"));  //in
        put("not in", new Sign("not in", ""));  //not in
        put("&", new Sign("&&", ""));  //并且
        put("|", new Sign("||", ""));  //或
        put("and", new Sign("&&", ""));  //并且
        put("or", new Sign("||", ""));  //或
        put("!", new Sign("!", ""));  //非
    }};

    public static String getTsType(String key) {
        Sign sign = operations.get(key);
        if (sign == null) return null;
        return sign.getTsType();
    }

    public static String getSearchType(String key) {
        Sign sign = operations.get(key);
        if (sign == null) return null;
        return sign.getSearchType();
    }

    public static boolean isOperation(String key) {
        return operations.containsKey(key);
    }

    public static boolean isLogicOperation(String c) {
        return c.equals("|") || c.equals("&") || c.equals("!") || c.equals("and") || c.equals("or");
    }

     static class Sign {
        /**
         * TypeScript操作符类型
         **/
        private String tsType;

         public String getTsType() {
             return tsType;
         }

         public void setTsType(String tsType) {
             this.tsType = tsType;
         }

         public String getSearchType() {
             return searchType;
         }

         public void setSearchType(String searchType) {
             this.searchType = searchType;
         }

         /**
         * 查询操作符类型
         **/
        private String searchType;

        public Sign(String tsType, String searchType) {
            this.tsType = tsType;
            this.searchType = searchType;
        }
    }
}
