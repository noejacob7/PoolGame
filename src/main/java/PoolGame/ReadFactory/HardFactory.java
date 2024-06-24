package PoolGame.ReadFactory;

import PoolGame.ConfigReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HardFactory implements LevelFactory{
    @Override
    public ConfigReader make() throws ConfigReader.ConfigKeyMissingException, IOException, ParseException {
        return new ConfigReader("/config_hard.json", true);
    }
}
