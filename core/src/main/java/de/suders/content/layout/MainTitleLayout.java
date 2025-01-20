package de.suders.content.layout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.suders.SudersMain;
import de.suders.content.logic.Layout;
import de.suders.resource.Settings;
import lombok.NonNull;

public class MainTitleLayout extends Layout {

    public MainTitleLayout(@NonNull Skin skin) {
        super("mainLayout", "main_table", skin);
    }

    @Override
    public void onLoadLayout() {
        Stage stage = getCurrentStage();
        Button soundActor = stage.getRoot().findActor(SettingsLayout.SettingsTypes.QUICK_SOUND_BUTTON.toString());
        Button musicActor = stage.getRoot().findActor(SettingsLayout.SettingsTypes.QUICK_MUSIC_BUTTON.toString());

        if(soundActor != null && musicActor != null) {
            Settings settings = SudersMain.resourceManager.getSettings();
            soundActor.setChecked(settings.isSoundEnabled());
            musicActor.setChecked(settings.isMusicEnabled());
        } else {
            Gdx.app.log("MainTitleLayout", "Settings couldn't be loaded for Sound and Music");
        }
    }

    @Override
    public void onUnloadLayout() {
        Stage stage = getCurrentStage();
        Button soundActor = stage.getRoot().findActor(SettingsLayout.SettingsTypes.QUICK_SOUND_BUTTON.toString());
        Button musicActor = stage.getRoot().findActor(SettingsLayout.SettingsTypes.QUICK_MUSIC_BUTTON.toString());
        Gdx.app.log("MainTitleLayout", "Settings saving...");
        Settings settings = SudersMain.resourceManager.getSettings();
        settings.setSoundEnabled(soundActor.isChecked());
        settings.setMusicEnabled(musicActor.isChecked());
    }
}
