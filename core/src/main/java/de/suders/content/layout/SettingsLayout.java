package de.suders.content.layout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import de.suders.SudersMain;
import de.suders.content.logic.Layout;
import de.suders.resource.Settings;
import lombok.Getter;
import lombok.NonNull;

public class SettingsLayout extends Layout {

    public SettingsLayout(@NonNull Skin skin) {
        super("settingsLayout", "settings_table", skin);
    }

    @Override
    public void onLoadLayout() {
        Stage stage = getCurrentStage();
        Settings settings = SudersMain.resourceManager.getSettings();
        for(SettingsTypes type : SettingsTypes.values()) {
            Actor actor = findActor(type);
            if(actor != null) {
                switch(type) {
                    case MUSIC_SLIDER:
                    case SOUND_SLIDER:
                        setLabelToValue(SettingsTypes.MUSIC_SLIDER.equals(type) ? SettingsTypes.MUSIC_LABEL : SettingsTypes.SOUND_LABEL, settings.getMusicVolume());
                        ((Slider) actor).setValue(SettingsTypes.MUSIC_SLIDER.equals(type) ? settings.getMusicVolume() : settings.getSoundVolume());
                        break;
                    case FULLSCREEN_BUTTON:
                        ((Button) actor).setChecked(settings.isFullScreen());
                        break;
                    case VSYNC_BUTTON:
                        ((Button) actor).setChecked(settings.isVSync());
                        break;
                    default:
                        break;
                }
            } else {
                Gdx.app.log("SettingsLayout", "Could not button " + type + " in stage " + stage);
            }
        }
    }

    @Override
    public void onUnloadLayout() {
        Stage stage = getCurrentStage();
        Settings settings = SudersMain.resourceManager.getSettings();
        for(SettingsTypes type : SettingsTypes.values()) {
            Actor actor = findActor(type);
            if(actor != null) {
                switch(type) {
                    case MUSIC_SLIDER:
                        settings.setMusicVolume((int) ((Slider) actor).getValue());
                        break;
                    case SOUND_SLIDER:
                        settings.setSoundVolume((int) ((Slider) actor).getValue());
                        break;
                    case FULLSCREEN_BUTTON:
                        settings.setFullScreen(((Button) actor).isChecked());
                    case VSYNC_BUTTON:
                        settings.setVSync(((Button) actor).isChecked());
                        break;
                    default:
                        break;
                }
            } else {
                Gdx.app.log("SettingsLayout", "Could not button " + type + " in stage " + stage);
            }
        }
    }

    public void updateLabelOfValue(@NonNull SettingsTypes type) {
        Actor actor = findActor(type);
        Settings settings = SudersMain.resourceManager.getSettings();
        if(actor != null) {
            switch(type) {
                case MUSIC_SLIDER:
                    setLabelToValue(SettingsTypes.MUSIC_LABEL, (int) ((Slider) actor).getValue());
                    break;
                case SOUND_SLIDER:
                    setLabelToValue(SettingsTypes.SOUND_LABEL, (int) ((Slider) actor).getValue());
                    break;
            }
        }
    }

    private void setLabelToValue(SettingsTypes type, int value) {
        Label label = (Label) findActor(type);
        if(label == null) return;
        String formatted = String.format("%3d", value);

        label.setText(type.getExtraValue().replaceAll("%v%", formatted));
    }

    private Actor findActor(SettingsTypes type) {
        Stage stage = getCurrentStage();
        return stage.getRoot().findActor(type.toString());
    }

    public enum SettingsTypes {

        MUSIC_SLIDER(null),
        SOUND_SLIDER(null),
        FULLSCREEN_BUTTON(null),
        VSYNC_BUTTON(null),
        SOUND_LABEL("Musik Lautstärke: %v%"),
        MUSIC_LABEL("Sound Lautstärke: %v%"),
        QUICK_MUSIC_BUTTON(null),
        QUICK_SOUND_BUTTON(null),;

        @Getter
        String extraValue;

        SettingsTypes(String extraValue) {
            this.extraValue = extraValue;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
