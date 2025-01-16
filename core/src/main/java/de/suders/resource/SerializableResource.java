package de.suders.resource;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import de.suders.assets.Assets;
import lombok.Getter;
import lombok.NonNull;

import java.io.*;
import java.lang.reflect.Field;

public abstract class SerializableResource implements Serializable {

    @Getter
    private String fileName;

    public SerializableResource(@NonNull String fileName) {
        this.fileName = Assets.GLOBAL_FILE_PATH + fileName;
    }

    @Override
    public String toString() {
        String s = "";
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                boolean wasAccessible = field.isAccessible();
                if(!wasAccessible) field.setAccessible(true);
                if (i == 0) {
                    s = "{" + field.getName() + "=" + field.get(field.getDeclaringClass().newInstance()) + ",";
                } else if (i == fields.length - 1) {
                    s += field.getName() + "=" + field.get(field.getDeclaringClass().newInstance()) + "}";
                } else {
                    s += field.getName() + "=" + field.get(field.getDeclaringClass().newInstance()) + ",";
                }
                if(!wasAccessible) field.setAccessible(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
