package three.stone.java.annotation;

import java.lang.reflect.Field;

@DBTable("member")
public class Member {
    @SQLInteger(name = "id", constraints = @Constraints(primary = true))
    private int id;
    @SQLInteger(name = "age")
    private int age;
    @SQLString(name = "name", length = 100)
    private String name;
    @SQLString(name = "description" , length = 150 , constraints = @Constraints(nullable = true))
    private String description;

    private static String createTableSQL(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        DBTable dbTable = clazz.getAnnotation(DBTable.class);
        builder.append("CREATE TABLE " + dbTable.value() + "(\n");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            SQLInteger sqlInteger = field.getAnnotation(SQLInteger.class);
            if (sqlInteger != null) {
                builder.append(sqlInteger.name());
                builder.append(" INT ");
                Constraints constraints = sqlInteger.constraints();
                if (!constraints.nullable()) {
                    builder.append(" NOT NULL ");
                }
                if (constraints.primary()) {
                    builder.append(" PRIMARY KEY ");
                }
                if (constraints.unique()) {
                    builder.append(" UNIQUE KEY ");
                }
                builder.append(",\n");
            }
            SQLString sqlString = field.getAnnotation(SQLString.class);
            if (sqlString != null) {
                builder.append(sqlString.name());
                builder.append(" VARCHAR(");
                builder.append(sqlString.length());
                builder.append(")");
                Constraints constraints = sqlString.constraints();
                if (!constraints.nullable()) {
                    builder.append(" NOT NULL ");
                }
                if (constraints.primary()) {
                    builder.append(" PRIMARY KEY ");
                }
                if (constraints.unique()) {
                    builder.append(" UNIQUE KEY ");
                }
                builder.append(",\n");
            }
        }
        builder.append(");");

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(createTableSQL(Member.class));
    }
}
