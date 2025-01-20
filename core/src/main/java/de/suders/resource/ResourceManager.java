package de.suders.resource;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import de.suders.assets.Assets;
import lombok.Getter;
import lombok.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResourceManager {

    @Getter
    private final Settings settings;

    public ResourceManager() throws IOException {
        File folder = new File(Assets.GLOBAL_FILE_PATH);
        if (!folder.exists()) {
            System.out.println(folder.mkdir());
        }
        settings = (Settings) loadSerializableResource(Settings.class, Assets.GLOBAL_FILE_PATH + "settings.dat");
    }

    public final SerializableResource loadSerializableResource(Class<?> clazz, @NonNull String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             Input input = new Input(fis)) {
            Kryo kryo = new Kryo();
            kryo.register(clazz);
            Object obj = (SerializableResource) kryo.readObject(input, clazz);
            System.out.println(obj.toString());
            return (SerializableResource) obj;
        } catch (Exception exc) {
            try {
                SerializableResource serializableResource = (SerializableResource) clazz.getDeclaredConstructor().newInstance();
                saveSerializableResource(serializableResource);
                return serializableResource;
            } catch (Exception exc2) {
                exc.printStackTrace();
                throw new RuntimeException(exc2);
            }
        }
    }

    public final void saveSerializableResource(SerializableResource serializableResource) {
        File file = new File(serializableResource.getFileName());
        if (!file.exists()) {
            try {
                if (!file.createNewFile())
                    System.out.println("Settings creation failed at " + serializableResource.getFileName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileOutputStream fos = new FileOutputStream(serializableResource.getFileName());
             Output output = new Output(fos)) {
            Kryo kryo = new Kryo();
            kryo.register(serializableResource.getClass());
            System.out.println(serializableResource.toString());
            kryo.writeObject(output, serializableResource);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
