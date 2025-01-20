package de.suders.resource;

import de.suders.assets.Assets;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.lang.reflect.Field;

@Getter
public abstract class SerializableResource implements Serializable {

    private transient String fileName;

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
                    s = "{" + field.getName() + "=" + field.get(this) + ",";
                } else if (i == fields.length - 1) {
                    s += field.getName() + "=" + field.get(this) + "}";
                } else {
                    s += field.getName() + "=" + field.get(this) + ",";
                }
                if(!wasAccessible) field.setAccessible(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
