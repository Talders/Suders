package de.suders.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Settings extends SerializableResource {

    private boolean isFullScreen;
    private boolean vSync;
    private boolean musicEnabled;
    private boolean soundEnabled;
    private int soundVolume; //0-100
    private int musicVolume; //0-100

    public Settings() {
        super("settings.dat");
        this.vSync = true;
        this.isFullScreen = false;
        this.musicEnabled = true;
        this.soundEnabled = true;
        this.soundVolume = 50;
        this.musicVolume = 50;
    }
}
