package com.github.bpazy.happysql;

import com.github.bpazy.common.lang.ArrayUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class HappySqlTest {

    @Test
    public void test() {
        List<TestBean> t = new HappySql().returnType(TestBean.class).execute("select * from h_test");
        System.out.println(t);

        TestBean t2 = new HappySql().limit(1).returnType(TestBean.class).execute("select * from h_test");
        System.out.println(t2);
    }

    @Test
    public void test2() {
        Date now = new Date();
        TestBean b = TestBean.builder()
                .id("732819837291")
                .var1("var1")
                .var2("var2")
                .createTime(now)
                .updateTime(now)
                .build();

        Field[] fields = b.getClass().getDeclaredFields();
        System.out.println(ArrayUtils.toString(fields));
    }
}


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class TestBean {
    private String id;
    private String var1;
    private String var2;
    private Date createTime;
    private Date updateTime;
}
