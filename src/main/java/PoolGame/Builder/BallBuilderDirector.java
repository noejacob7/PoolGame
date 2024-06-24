package PoolGame.Builder;

import java.util.HashMap;
import java.util.Map;

import PoolGame.Config.BallConfig;
import PoolGame.Items.Ball;
import PoolGame.Items.Ball.BallType;
import PoolGame.Strategy.GameReset;
import PoolGame.Strategy.PocketOnce;
import PoolGame.Strategy.PocketTwice;
import PoolGame.Strategy.PocketThrice;

/** The class that builds all the different balls */
public class BallBuilderDirector {
    private Map<String, BallBuilder> builders;

    /** Initialise a ball builder director with no knowledge of how to build balls */
    public BallBuilderDirector() {
        this.builders = new HashMap<>();
    }

    /**
     * Associate a key with a ball builder.
     * @param key A string to associate with a builder
     * @param builder The ball builder to associate with the provided key
     */
    public void register(String key, BallBuilder builder) {
        this.builders.put(key, builder);
    }

    /** Register the defaults for the ball builder director. */
    public void registerDefault() {
        this.register("white", new WhiteBallBuilder(BallType.CUEBALL, new GameReset(), 0));

        this.register("red", new RedBallBuilder(BallType.NORMALBALL, new PocketOnce(), 1));
        this.register("orange", new OrangeBallBuilder(BallType.NORMALBALL, new PocketOnce(), 8));
        this.register("yellow", new YellowBallBuilder(BallType.NORMALBALL, new PocketOnce(), 2));

        this.register("blue", new BlueBallBuilder(BallType.NORMALBALL, new PocketTwice(), 5));
        this.register("green", new GreenBallBuilder(BallType.NORMALBALL, new PocketTwice(), 3));
        this.register("purple", new PurpleBallBuilder(BallType.NORMALBALL, new PocketTwice(), 6));

        this.register("black", new BlackBallBuilder(BallType.NORMALBALL, new PocketThrice(), 7));
        this.register("brown", new BrownBallBuilder(BallType.NORMALBALL, new PocketThrice(), 4));

    }

    /**
     * Construct the ball based on the config
     * @param config The ball configuration
     * @return The ball instance configured with the config provided
     */
    public Ball construct(BallConfig config) {
        String key = config.getColour();
        BallBuilder builder = this.builders.get(key);
        if (builder == null) {
            return null;
        }
        builder.setXPos(config.getPositionConfig().getX());
        builder.setYPos(config.getPositionConfig().getY());
        builder.setXVel(config.getVelocityConfig().getX());
        builder.setYVel(config.getVelocityConfig().getY());
        builder.setMass(config.getMass());
        return builder.finaliseBuild();
    }
}
