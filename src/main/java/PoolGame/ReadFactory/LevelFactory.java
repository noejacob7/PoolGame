package PoolGame.ReadFactory;

import PoolGame.ConfigReader;
import PoolGame.Game;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface LevelFactory {
    public ConfigReader make() throws ConfigReader.ConfigKeyMissingException, IOException, ParseException;
}
