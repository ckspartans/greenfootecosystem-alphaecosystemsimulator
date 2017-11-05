import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Reproduce
{
    
    //The object reproducee is used to hold the organism that is reproducing
    public AbstOrganism reproducee;
    MyWorld world;
    
    // this double stores the organism's energy, which is the amount of energy the organism has
    public double energyToReproduce;
    
    // this double stores the organism's mutation rate, which is the chance the organism will mutate any given trait
    public double mutationFactor;
    
    // this int stores the organism's repro_energy, which is the energy needed to reproduce
    public int reproduceFactor;
    
    // this arraylist stores the different kinds of organisms found in this ecosystem
    public ArrayList <AbstOrganism> org = new ArrayList<AbstOrganism> (5);
    
    //The paramters of Reproduction is organism itself
    public Reproduce(AbstOrganism _reproducee)
    {
        reproducee = _reproducee;
        
    }
    
    //this function checks if the organism has enough energy to reproduce, and then creates two new organisms and kills off the old one while the new ones inherit the previous organism's traits and have a chance to mutate their traits
    public void reproduce(){
        // stores variables required for reproduction and mutation (energy, repro_energy, and mutation_rate)
        energyToReproduce = reproducee.energy;
        mutationFactor = reproducee.mutation_rate;
        reproduceFactor = reproducee.repro_energy;
        
        //check to see of there is enough energy to split
        //if yes then call the constructor for new ones and kill the last one
        if (energyToReproduce >= reproduceFactor){
            
            // checks trophic level
                // spawn new organisms of initial organism's type
                // new organisms inherit original organisms' traits and has a chance to mutate them
                    // this is done by going through all the organism's traits using a for loop and checking to mutate them individually using the parent organism's traits, if it does not mutate, just inherits parent organism's traits
                // kills original organism
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
            
            else if (reproducee.trophicLevel == 1) {
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
            
            else if (reproducee.trophicLevel == 2) {
                world.scavengers.add(new Scavenger());
                world.addObject(world.scavengers.get(world.scavengers.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                for (int i = 0; i < org.size(); i++) {
                    mutate(i);
                }
                
                world.scavengers.add(new Scavenger());
                world.addObject(world.scavengers.get(world.scavengers.size()-1), reproducee.getX() + Greenfoot.getRandomNumber(20), reproducee.getY() + Greenfoot.getRandomNumber(20));
                for (int i = 0; i < org.size(); i++) {
                    mutate(i);
                }
                
                reproducee.die();
            }
            
            else if (reproducee.trophicLevel == 3) {
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
            
            else if (reproducee.trophicLevel == 4) {
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
    
    // when traits of an organism are inputed, there is a chance that it mutates and returns the newly mutated trait
    public void mutate(int trait) {
        // generates random number between 0 and 100
        int mutate = Greenfoot.getRandomNumber(100);
        
        // if the random number is less than or equal to the mutation rate
        if (mutate <= mutationFactor) {
            // the inputed trait is mutated by adding the initial trait by the mutation factor
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
