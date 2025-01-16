package de.suders.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.suders.assets.Assets;
import de.suders.content.logic.Container;
import de.suders.content.logic.Layout;
import lombok.Getter;
import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class ScreenManager {

    @Getter
    private final Skin uiSkin;
    private final HashMap<Class<?>, Layout> layoutCollection;
    private final HashMap<Class<?>, Container> containerCollection;
    private final ArrayList<EventListener> listenerCollection;

    public ScreenManager() {
        uiSkin = new Skin(Gdx.files.internal("ui/mainAtlas.json"));
        layoutCollection = new HashMap<Class<?>, Layout>();
        containerCollection = new HashMap<Class<?>, Container>();
        listenerCollection = new ArrayList<EventListener>();

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/mainAtlas.atlas"));
        uiSkin.addRegions(atlas);
    }

    public synchronized void loadLayouts() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<?> layoutClass : Assets.getClasses("de.suders.content.layout")) {
            if (layoutClass.getSuperclass().equals(Layout.class)) {
                Layout layout = (Layout) layoutClass.getDeclaredConstructor().newInstance();
                addLayout(layout);
            }
        }
    }

    public void loadListenersToStage(Stage stage) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<?> listenerClass : Assets.getClasses("de.suders.content.listener")) {
            if (InputListener.class.isAssignableFrom(listenerClass)) {
                InputListener listener = (InputListener) listenerClass.getDeclaredConstructor().newInstance();
                stage.addListener(listener);
                listenerCollection.add(listener);
            }
        }
    }

    private void addLayout(@NonNull Layout layout) {
        layoutCollection.put(layout.getClass(), layout);
    }

    public void loadContainer(@NonNull Container container) {
        if (!containerCollection.containsKey(container.getClass())) {
            containerCollection.put(container.getClass(), container);
        }
    }

    public Container getContainerByClass(@NonNull Class<?> clazz) {
        if (!containerCollection.containsKey(clazz)) {
            try {
                Container container = (Container) clazz.getDeclaredConstructor().newInstance();
                loadContainer(container);
                return container;
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        }
        return containerCollection.get(clazz);
    }

    public boolean disposeContainer(@NonNull Container container) {
        if (containerCollection.containsKey(container.getClass())) {
            containerCollection.remove(container.getClass());
            container.dispose();
            return true;
        }
        return false;
    }

    /**
     * @param targetLayout Wenn zum Beispiel die Klasse Layout.java gesucht wird, um keine neue zu generieren
     *                     dann wird einfach hiervon die abgerufen
     * @return
     */
    public <T extends Layout> T getLayoutByClass(@NonNull Class<T> targetLayout) {
        for (Layout layout : layoutCollection.values()) {
            if (layout.getClass() == targetLayout) {
                return targetLayout.cast(layout);
            }
        }
        return null;
    }
}
