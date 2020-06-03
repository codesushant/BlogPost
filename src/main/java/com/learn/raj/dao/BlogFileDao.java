package com.learn.raj.dao;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.learn.raj.entities.Blog;

import java.io.*;

public class BlogFileDao{
    public static Blog get(String fileName) {
        Gson gson = new Gson();

        File file = new File(fileName);
        if (!file.exists())
            System.out.println("File doesn't exist");

        Blog blog=null;

        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream(file), "UTF-8");

            JsonReader myReader = new JsonReader(isReader);
            blog = gson.fromJson(myReader, Blog.class);

            System.out.println("Blog: " + blog.toString());

        } catch (Exception e) {
            System.out.println("error in load data from file " + e.toString());
        }
        return blog;
    }

    public static boolean save(Blog blog, String dir) {
        Gson gson = new Gson();
        String fileName = dir + "/" + blog.getBlogId();
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Exception: " + e.toString());
            }
        }

        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file.getAbsoluteFile(), false);

            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            String myData = gson.toJson(blog);
            bufferWriter.write(myData);
            bufferWriter.close();

            System.out.println("Data saved at file location: " + fileName + " Data: " + myData + "\n");
        } catch (IOException e) {
            System.out.println("Error while saving data to file " + e.toString());
            return false;
        }
        return true;
    }
}
