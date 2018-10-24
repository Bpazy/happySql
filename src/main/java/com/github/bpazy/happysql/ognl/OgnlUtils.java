package com.github.bpazy.happysql.ognl;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ognl.Ognl;
import ognl.OgnlContext;

@UtilityClass
public class OgnlUtils {
    private static final OgnlContext context = new OgnlContext(null, null, new DefaultMemberAccess(true));

    @SneakyThrows
    public void setValue(String expression, Object root, Object value) {
        Ognl.setValue(expression, context, root, value);
    }
}
