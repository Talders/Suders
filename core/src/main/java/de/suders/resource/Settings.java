package de.suders.resource;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import de.suders.assets.Assets;
import lombok.Getter;
import lombok.Setter;

import java.io.*;

@Getter
@Setter
public class Settings extends SerializableResource {

    private boolean isFullScreen;
    private int soundVolume; //0-100
    private int musicVolume; //0-100

    public Settings() {
        super("settings.txt");
        this.isFullScreen = false;
        this.soundVolume = 50;
        this.musicVolume = 50;
    }
}
