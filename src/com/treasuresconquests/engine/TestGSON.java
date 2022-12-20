package com.treasuresconquests.engine;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class TestGSON {
    public static void main(String[] args) {
        printTestDataGSON();
    }
        public static InputStream getFileFromResourceAsStream (String fileName){
            ClassLoader classLoader = TestGSON.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + fileName);
            } else {
                return inputStream;
            }
        }

        public static void printTestDataGSON () {
            Gson gson = new Gson();
            String s = "GSON/testJson.json";
            InputStream inputTestJSON = getFileFromResourceAsStream(s);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputTestJSON, "UTF-8"))) {
                Map<String, Map<String, Object>> map = gson.fromJson(reader, Map.class);
                System.out.println(map.keySet().size());
                System.out.println(map.keySet().toArray()[0]);
                System.out.println(map.keySet().toArray());
            } catch (IOException ioe) {
                System.out.println("unable to read file " + s);
            }
        }
    }

