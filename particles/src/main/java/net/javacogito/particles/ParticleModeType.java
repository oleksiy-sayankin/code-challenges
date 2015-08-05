package net.javacogito.particles;

/**
 * @author oleksiy sayankin
 */

public enum ParticleModeType {

    SIMPLE(Constants.ParticleType.SIMPLE),
    MASS(Constants.ParticleType.MASS);
    private String name;
    ParticleModeType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static ParticleModeType parse(String name){
        for(ParticleModeType type : ParticleModeType.values()){
            if(type.getName().equals(name)) return type;
        }
        return null;
    }
}
