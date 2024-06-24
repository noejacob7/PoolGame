package PoolGame.ReadFactory;

import java.io.IOException;
import java.util.*;

import PoolGame.ConfigReader;
import PoolGame.Game;
import javafx.scene.Group;
import org.json.simple.parser.ParseException;

/** The config factory registry */
public class LevelFactoryRegistry {

    private Group root = new Group();

    private Map<String, LevelFactory> registry = new HashMap<>();

    /**
     * Register the default factory for the JSON configuration
     */
    public void registerDefault() {
        this.register("easy", new EasyFactory());
        this.register("normal", new NormalFactory());
        this.register("hard", new HardFactory());
    }

    /**
     * Insert factory into the registry, retrievable by using the same key.
     * If the key exists, it will replace the existing factory defined.
     * @param key A `String` as the key, case sensitive
     * @param factory The factory associated with the key
     */
    public void register(String key, LevelFactory factory) {
        // key = key.toLowerCase();
        if (!this.registry.containsKey(key)) {
            this.registry.put(key, factory);
        } else {
            this.registry.replace(key, factory);
        }
    }

    /**
     * Remove the factory associated with the key from the registry.
     *
     * @param key A `String` that is associated to a factory.
     * @return The factory that is associated with the key, returns `null` if
     * the key was not found.
     */
    public LevelFactory unregister(String key) {
        // key = key.toLowerCase();
        return this.registry.remove(key);
    }

    /**
     * Get the factory associated with the key.
     * @param key A key to get a factory
     * @return The factory associated with the key, null if no factory is
     * associated with the key
     */
    public LevelFactory getFactory(String key) {
        // key = key.toLowerCase();
        return this.registry.get(key);
    }

    /**
     * Create an instance using a factory associated with the key
     * @param key The key associated with a factory
     * @return null if the key does not exist, the return value of the factory
     * otherwise
     */
    public void create(String key, Game game) throws ConfigReader.ConfigKeyMissingException, IOException, ParseException {
        LevelFactory factory = this.getFactory(key);
        ConfigReader config = factory.make();
        root.getChildren().clear();
        game.setup(config);
        game.addDrawables(root);
        game.getScene().setRoot(root);
    }
}
