package ahmadZufarJsmartMH;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Vector;

public class JsonTable<T> extends Vector<T> {
    public final String filepath ;
    private static final Gson gson = new Gson();

    public JsonTable(Class<T> clazz, String filepath) throws IOException{
        this.filepath = filepath;
        try
        {
            Class<T[]> array = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
            T[] loaded = readJson(array, filepath);
            if(loaded != null)
            {
                Collections.addAll(this, loaded);
            }
        }
        catch (FileNotFoundException e)
        {
            File f = new File(filepath);
            File f1 =  f.getParentFile();
            if(f1 != null)
            {
                f1.mkdirs();
            }
            f.createNewFile();
        }
    }

    public static <T>T readJson(Class<T> clazz, String filepath) throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader(filepath));
        T objectT = gson.fromJson(jsonReader, clazz);
        return objectT;
//        final JsonReader reader = new JsonReader(new FileReader(filepath));
//        return  reader;
    }

    public void writeJson() throws IOException {
        writeJson(this, this.filepath);
//        final FileWriter writer = new FileWriter(this.filepath);
//        gson.toJson(this.gson, writer);
    }

    public static void writeJson(Object object, String filepath) throws IOException {
        FileWriter fwrite = new FileWriter(filepath);
        String s = gson.toJson(object);
        fwrite.write(s);
        fwrite.close();
    }
}
