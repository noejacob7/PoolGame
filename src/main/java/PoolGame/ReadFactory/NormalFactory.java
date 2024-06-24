package PoolGame.ReadFactory;

import PoolGame.ConfigReader;
import PoolGame.Game;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class NormalFactory implements LevelFactory{
    @Override
    public ConfigReader make() throws ConfigReader.ConfigKeyMissingException, IOException, ParseException {
        return new ConfigReader("/config_normal.json", true);
    }
}
