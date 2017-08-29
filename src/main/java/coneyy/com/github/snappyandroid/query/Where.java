package coneyy.com.github.snappyandroid.query;


public class Where {

    From from;
    String initClause;

    public Where(From from) {
        this.from = from;
    }

    public Where(From from, String initClause) {
        this.from = from;
        this.initClause = initClause;
    }

    private From addToFrom(String clause) {
        if (initClause != null)
            return from.appendClause(initClause + clause);
        else return from.appendClause(clause);
    }

    public From eq(String columnName, Object arg) {

        return addToFrom(columnName + " = " + argToString(arg));
    }

    public From ge(String columnName, Object arg) {
        return addToFrom(columnName + " >= " + argToString(arg));
    }

    public From gt(String columnName, Object arg) {
        return addToFrom(columnName + " > " + argToString(arg));
    }

    public From le(String columnName, Object arg) {
        return addToFrom(columnName + " =< " + argToString(arg));
    }

    public From lt(String columnName, Object arg) {
        return addToFrom(columnName + " < " + argToString(arg));
    }

    public From isNull(String columnName) {
        return addToFrom(columnName + " IS NULL");
    }

    public From notNull(String columnName) {
        return addToFrom(columnName + " NOT NULL");

    }

    private String argToString(Object arg) {
        if (arg instanceof String)
            return "'" + arg.toString() + "'";
        if (arg instanceof Boolean) {
            if (((Boolean) arg))
                return "1";
            else return "0";
        } else return arg.toString();
    }


}
