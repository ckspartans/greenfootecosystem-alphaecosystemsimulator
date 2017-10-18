import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Reproduce
{
    
    public AbstOrganism reproducee;
    MyWorld world;
    
    public double energyToReproduce;
    public double mutationFactor;
    public int reproduceFactor;
    
    public ArrayList <AbstOrganism> org = new ArrayList<AbstOrganism> (5);
    
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
                for (int i = 0; i < org.size(); i++) {
                    mutate(i);
                }
                
                world.plants.add(new Algae());
                world.addObject(world.plants.get(world.plants.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                for (int i = 0; i < org.size(); i++) {
                    mutate(i);
                }
                
                reproducee.die();
            }
            
            if (reproducee.trophicLevel == 1) {
                world.herbivores.add(new PlantEater());
                world.addObject(world.herbivores.get(world.herbivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                for (int i = 0; i < org.size(); i++) {
                    mutate(i);
                }
                
                world.herbivores.add(new PlantEater());
                world.addObject(world.herbivores.get(world.herbivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                for (int i = 0; i < org.size(); i++) {
                    mutate(i);
                }
                
                reproducee.die();
            }
            
            if (reproducee.trophicLevel == 2) {
                world.omnivores.add(new AnythingEater());
                world.addObject(world.omnivores.get(world.omnivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                for (int i = 0; i < org.size(); i++) {
                    mutate(i);
                }
                
                world.omnivores.add(new AnythingEater());
                world.addObject(world.omnivores.get(world.omnivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                for (int i = 0; i < org.size(); i++) {
                    mutate(i);
                }
                
                reproducee.die();
            }
            
            if (reproducee.trophicLevel == 3) {
                world.carnivores.add(new MeatEater());
                world.addObject(world.carnivores.get(world.carnivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                for (int i = 0; i < org.size(); i++) {
                    mutate(i);
                }
                
                world.carnivores.add(new MeatEater());
                world.addObject(world.carnivores.get(world.carnivores.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                for (int i = 0; i < org.size(); i++) {
                    mutate(i);
                }
                
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
