import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Reproduce
{
    
    public AbstOrganism reproducee;
    MyWorld world;
    
    public double energyToReproduce;
    public double mutationFactor;
    public int reproduceFactor;
    
    
    public Reproduce(AbstOrganism _reproducee)
    {
        reproducee = _reproducee;
        
    }
    
    
    public void reproduce(){
        energyToReproduce = reproducee.energy;
        mutationFactor = reproducee.mutation_rate;
        reproduceFactor = reproducee.repro_energy;
        
        if (energyToReproduce >= reproduceFactor){
            if (reproducee.trophicLevel == 0) {
                world.plants.add(new Algae());
                world.addObject(world.plants.get(world.plants.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                world.plants.get(world.plants.size()-1).repro_energy = mutate(repro_energy);
                world.plants.get(world.plants.size()-1).siz = mutate(siz);
                world.plants.get(world.plants.size()-1).health = mutate((int) health);
                world.plants.get(world.plants.size()-1).energy = mutate((int) energy);
                world.plants.get(world.plants.size()-1).speed = mutate(speed);
                world.plants.get(world.plants.size()-1).speed = mutate(def);
                world.plants.get(world.plants.size()-1).att = mutate(att);
                world.plants.get(world.plants.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.plants.get(world.plants.size()-1).sight = mutate(sight);
                
                world.plants.add(new Algae());
                world.addObject(world.plants.get(world.plants.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                world.plants.get(world.plants.size()-1).repro_energy = mutate(repro_energy);
                world.plants.get(world.plants.size()-1).siz = mutate(siz);
                world.plants.get(world.plants.size()-1).health = mutate((int) health);
                world.plants.get(world.plants.size()-1).energy = mutate((int) energy);
                world.plants.get(world.plants.size()-1).speed = mutate(speed);
                world.plants.get(world.plants.size()-1).speed = mutate(def);
                world.plants.get(world.plants.size()-1).att = mutate(att);
                world.plants.get(world.plants.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.plants.get(world.plants.size()-1).sight = mutate(sight);
                
                reproducee.die();
            }
            
            if (reproducee.trophicLevel == 1) {
                world.herbivores.add(new PlantEater());
                world.addObject(world.herbivores.get(world.herbivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                world.herbivores.get(world.herbivores.size()-1).repro_energy = mutate(repro_energy);
                world.herbivores.get(world.herbivores.size()-1).siz = mutate(siz);
                world.herbivores.get(world.herbivores.size()-1).health = mutate((int) health);
                world.herbivores.get(world.herbivores.size()-1).energy = mutate((int) energy);
                world.herbivores.get(world.herbivores.size()-1).speed = mutate(speed);
                world.herbivores.get(world.herbivores.size()-1).speed = mutate(def);
                world.herbivores.get(world.herbivores.size()-1).att = mutate(att);
                world.herbivores.get(world.herbivores.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.herbivores.get(world.herbivores.size()-1).sight = mutate(sight);
                
                
                world.herbivores.add(new PlantEater());
                world.addObject(world.herbivores.get(world.herbivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                world.herbivores.get(world.herbivores.size()-1).repro_energy = mutate(repro_energy);
                world.herbivores.get(world.herbivores.size()-1).siz = mutate(siz);
                world.herbivores.get(world.herbivores.size()-1).health = mutate((int) health);
                world.herbivores.get(world.herbivores.size()-1).energy = mutate((int) energy);
                world.herbivores.get(world.herbivores.size()-1).speed = mutate(speed);
                world.herbivores.get(world.herbivores.size()-1).speed = mutate(def);
                world.herbivores.get(world.herbivores.size()-1).att = mutate(att);
                world.herbivores.get(world.herbivores.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.herbivores.get(world.herbivores.size()-1).sight = mutate(sight);
                
                reproducee.die();
            }
            
            if (reproducee.trophicLevel == 2) {
                world.omnivores.add(new AnythingEater());
                world.addObject(world.omnivores.get(world.omnivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                world.omnivores.get(world.omnivores.size()-1).repro_energy = mutate(repro_energy);
                world.omnivores.get(world.omnivores.size()-1).siz = mutate(siz);
                world.omnivores.get(world.omnivores.size()-1).health = mutate((int) health);
                world.omnivores.get(world.omnivores.size()-1).energy = mutate((int) energy);
                world.omnivores.get(world.omnivores.size()-1).speed = mutate(speed);
                world.omnivores.get(world.omnivores.size()-1).speed = mutate(def);
                world.omnivores.get(world.omnivores.size()-1).att = mutate(att);
                world.omnivores.get(world.omnivores.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.omnivores.get(world.omnivores.size()-1).sight = mutate(sight);
                
                world.omnivores.add(new AnythingEater());
                world.addObject(world.omnivores.get(world.omnivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                world.omnivores.get(world.omnivores.size()-1).repro_energy = mutate(repro_energy);
                world.omnivores.get(world.omnivores.size()-1).siz = mutate(siz);
                world.omnivores.get(world.omnivores.size()-1).health = mutate((int) health);
                world.omnivores.get(world.omnivores.size()-1).energy = mutate((int) energy);
                world.omnivores.get(world.omnivores.size()-1).speed = mutate(speed);
                world.omnivores.get(world.omnivores.size()-1).speed = mutate(def);
                world.omnivores.get(world.omnivores.size()-1).att = mutate(att);
                world.omnivores.get(world.omnivores.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.omnivores.get(world.omnivores.size()-1).sight = mutate(sight);
                
                reproducee.die();
            }
            
            if (reproducee.trophicLevel == 3) {
                world.carnivores.add(new MeatEater());
                world.addObject(world.carnivores.get(world.carnivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                world.carnivores.get(world.carnivores.size()-1).repro_energy = mutate(repro_energy);
                world.carnivores.get(world.carnivores.size()-1).siz = mutate(siz);
                world.carnivores.get(world.carnivores.size()-1).health = mutate((int) health);
                world.carnivores.get(world.carnivores.size()-1).energy = mutate((int) energy);
                world.carnivores.get(world.carnivores.size()-1).speed = mutate(speed);
                world.carnivores.get(world.carnivores.size()-1).speed = mutate(def);
                world.carnivores.get(world.carnivores.size()-1).att = mutate(att);
                world.carnivores.get(world.carnivores.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.carnivores.get(world.carnivores.size()-1).sight = mutate(sight);
                
                world.carnivores.add(new MeatEater());
                world.addObject(world.carnivores.get(world.carnivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                world.carnivores.get(world.carnivores.size()-1).repro_energy = mutate(repro_energy);
                world.carnivores.get(world.carnivores.size()-1).siz = mutate(siz);
                world.carnivores.get(world.carnivores.size()-1).health = mutate((int) health);
                world.carnivores.get(world.carnivores.size()-1).energy = mutate((int) energy);
                world.carnivores.get(world.carnivores.size()-1).speed = mutate(speed);
                world.carnivores.get(world.carnivores.size()-1).speed = mutate(def);
                world.carnivores.get(world.carnivores.size()-1).att = mutate(att);
                world.carnivores.get(world.carnivores.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.carnivores.get(world.carnivores.size()-1).sight = mutate(sight);
                
                reproducee.die();
            }
            
        }
    }
    
    public void mutate(int trait) {
        int mutate = Greenfoot.getRandomNumber(100);
        
        if (mutate <= mutationFactor) {
            trait = trait+trait*(int) mutationFactor/100;
        }
    }
    
    public ArrayList<AbstOrganism> removeNull(ArrayList<AbstOrganism> s){
        for(int i = 0; i < s.size(); i++){
            if(s.get(i) == null){
                s.remove(s.get(i));
            }
        }
        return s;
    }
}
