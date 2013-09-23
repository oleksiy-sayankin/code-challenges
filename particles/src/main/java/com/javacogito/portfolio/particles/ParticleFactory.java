package com.javacogito.portfolio.particles;

import com.javacogito.portfolio.particles.physics.model.IContainerModel;
import com.javacogito.portfolio.particles.physics.model.IParticleModel;
import com.javacogito.portfolio.particles.physics.model.MassParticleModel;
import com.javacogito.portfolio.particles.physics.model.SimpleParticleModel;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author oleksiy sayankin
 */

public final class ParticleFactory {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new InputStreamReader(ParticleFactory.class.getClassLoader().getResourceAsStream(Constants.Util.PROPERTIES_FILE_NAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ParticleFactory() throws IOException {
    }
    public static IParticleModel createParticleModel(ParticleModeType type, IContainerModel containerModel){
        switch (type){
            case SIMPLE: return new SimpleParticleModel(containerModel);
            case MASS: return new MassParticleModel(containerModel);
        }
        return null;
    }

    public static IParticleModel createParticleModel(IContainerModel containerModel){
        ParticleModeType type = ParticleModeType.parse(properties.getProperty(Constants.Properties.PARTICLE_TYPE));
        return createParticleModel(type, containerModel);
    }
}
