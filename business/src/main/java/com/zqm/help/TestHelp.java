package com.zqm.help;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe:
 * @author:zqm
 */
public final class TestHelp {

    private TestHelp() {
    }

    protected static Map<String, String> getResource(Reader reader) {
        Map<String, String> map = new ConcurrentHashMap<>();
        try {
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("=");
                map.put(tokens[0], tokens[1]);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map;
    }


    protected static Map<String, String> getResource() {
        return getResource(newClassPathReader("/test.dict"));
    }

    protected static Map<String, String> getResource1() {
        return getResource(newClassPathReader("/test1"));
    }

    protected static Map<String, String> getResource2() {
        return getResource(newClassPathReader("/resources/test1"));
    }

    protected static Reader newClassPathReader(String classpath) {
        InputStream is = TestHelp.class.getResourceAsStream(classpath);
        try {
            return new InputStreamReader(is, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getResource());
        System.out.println(getResource1());
        System.out.println(getResource2());

    }
}
