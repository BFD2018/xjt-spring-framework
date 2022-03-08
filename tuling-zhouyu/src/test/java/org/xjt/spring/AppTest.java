package org.xjt.spring;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
//        String ss = "org.xjt.demo.service";
//        ss = ss.replace(".", "/");
//        System.out.println(ss);


        HashMap<String, Object> map = new HashMap<>();
        map.put("name","xinog");
        map.put("age",18);
        for (String s : map.keySet()) {
            System.out.println(s);
            System.out.println(map.get(s));
        }
    }
}
