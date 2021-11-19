package com.ahmadZufarJsmartMH;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;

public class JsonTable<T> extends Vector<T> {
    public final String filepath;
    private static final Gson gson = new Gson();

    public JsonTable(Class<T> clazz, String filepath) throws IOException {
        this.filepath = filepath;
        try {
            Class<T[]> arrayType = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
            T[] readresult = readJson(arrayType, filepath);
            if(readresult != null) {
                Collections.addAll(this, readresult);
            }
        } catch (FileNotFoundException e) {
            File f = new File(filepath);
            File f1 = f.getParentFile();
            if(f1 != null) {
                f.mkdir();
            }
            f.createNewFile();
        }
    }

    public static <T> T readJson(Class<T> clazz, String filepath) throws FileNotFoundException {
        Reader reader = new FileReader(filepath);
        return gson.fromJson(reader, clazz);
    }

    public void writeJson() throws IOException {
        writeJson(this, this.filepath);
    }

    public static void writeJson(Object object, String filepath) throws IOException {
        FileWriter writer = new FileWriter(filepath);
        writer.write(gson.toJson(object));
        writer.close();
    }
}
