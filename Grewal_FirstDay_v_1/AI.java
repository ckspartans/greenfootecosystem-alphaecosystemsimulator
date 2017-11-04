import greenfoot.*;
import java.util.*;
/**
 * Write a description of class AI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//This class handles all thinking functions the organisms (except the algaes) have
public class AI  
{
    // instance variables - replace the example below with your own

    //The object target is used to hold the organism the main organism will target as prey
    public AbstOrganism target;
    //This object holds the animals closest predator (so that it could decide whether or not it is appropriate to flee it 
    public AbstOrganism nearestPredator;
    //This object stores the main organism that will be doing the thinking (we will refer the animal that we pass into each function as the 'owner')
    public AbstOrganism owner = null;
    //This int stores the prey's energy, which we will use to determine how much energy the animal may recieve
    public int energyFactor;
    //This int stores the amount of desire the owner has towards eating a prey, which we will compare when determining the best prey
    public int desireFactor = 0;

    MyWorld world;
    /**
     * Constructor for objects of class AI
     */
    //The paramters of AI is the animal itself
    public AI(AbstOrganism _owner)
    {
        owner = _owner;
    }
    //in this function I tie everything in together to create a tracking function
    public void track(){
        //if there are no nearby preys 
        if(owner.targets == null){
            //get out of this function since there is nothing to even track
            return;   
        }
        //if there are preys nearby
        if(owner.targets != null){
            //Use the function findMostDesired to find the most desiring prey for the owner
            findMostDesired();
        }
        //If we successfully found a target to eat
        if(target != null){
            //set the owner's energyFactor to the targets energy before the target was eaten
            owner.energyFactor = target.energy;
            //Turn towards the target and close in on it
            owner.turnTowards(target.getX(),target.getY());
        }
        //Set target to null since the target is now eaten
        target = null;   
    }
    //In this function I determine the most desiring target
    private void findMostDesired(){
        //This double stores the most desiring target
        double maxDesire = 0;
        //this int stores the index of the most desiring target
        int maxIndex = 0;
        //Get the targets nearby the owner
        List <AbstOrganism> targets = new ArrayList<AbstOrganism>(owner.targets);
        //This new ArrayList will store the viable targets that the owner can actually go for
        ArrayList <AbstOrganism> newTargets = new ArrayList<AbstOrganism>();
        //For each possible prey around the owner  
        for (int i = 0; i< targets.size(); i++){
            //Get that possible prey  
            AbstOrganism o = targets.get(i);
            //if the owner is an omnivore
            if(owner.trophicLevel == 3){
                //check if the owner's trophic level is above the target's trophic level
                if (owner.trophicLevel >= o.trophicLevel && o.trophicLevel != -1){
                    //add this potential target to the newTargets array list
                    newTargets.add(o);
                }
            }
            //if the owner is a carnivore
            if(owner.trophicLevel == 4){
                //check if the owner's trophic level is above the target's trophic level, and that it is not a plant
                if (owner.trophicLevel >= o.trophicLevel && o.trophicLevel != 0 && o.trophicLevel != -1){
                    //add this potential target to the newTargets array list 
                    newTargets.add(o);
                }
            }
            //if the owner is a herbivore
            if(owner.trophicLevel == 1){    
                //check if the target is a plant
                if (o.trophicLevel == 0 && o.trophicLevel != -1){
                    //add this potential target to the newTargets array list 
                    newTargets.add(o);
                }
            }
            //if the owner is a scavenger
            if(owner.trophicLevel == 2){    
                //check if the target is a plant
                if (o.trophicLevel == -1){
                    //add this potential target to the newTargets array list 
                    newTargets.add(o);
                }
            }
        }
        //remove any targets that couldve been null
        removeNull(newTargets);
        //if there are any new targets
        if(newTargets != null && !newTargets.isEmpty()){
            //for each new target
            for(int i = 0; i < newTargets.size(); i++){
                //get that target
                AbstOrganism o = newTargets.get(i);
                double tempDesire = 0;
                removeNull(newTargets);
                //if that target isnt null
                if(newTargets.get(i) != null && o != null){
                    removeNull(newTargets);
                    //calculate the desire of that specific target
                    tempDesire = calculateDesire(o);
                }
                //if that new target is more desiring than the most desiring target
                if (tempDesire > maxDesire){
                    //set the new maxIndex to this new target
                    maxIndex = i;
                    //set the maxDesire to the desire of the target that has higher desire
                    maxDesire = tempDesire;
                }
            }
            //now get the most desiring prey
            target = newTargets.get(maxIndex);
        }
        //if there are no newTargets left
        else{
            //leave this function
            return;
        }    
    }

    public void flee(){
        //Get the targets nearby the owner
        List <AbstOrganism> predators  = new ArrayList <AbstOrganism> (owner.targets);
        ArrayList <AbstOrganism> newPredators = new ArrayList<AbstOrganism>();
        double minDist = 1000;
        int maxIndex = 0;
        //for each target
        for (int i = 0; i< predators.size(); i++){
            //get that target
            AbstOrganism o = predators.get(i);
            //if the trophicLevel of the owner is less than the trophic level of the target
            if (owner.trophicLevel < o.trophicLevel){
                //add this target to the predators arraylist
                newPredators.add(o);
            }

        }
        //if there are preadators around the owner
        if(newPredators != null && !newPredators.isEmpty()){
            //for each predator, get the distance and compare them with each other to find the closest target
            for(int i = 0; i < newPredators.size(); i++){
                AbstOrganism o = newPredators.get(i);
                double tempDist = (int)Math.sqrt((int)Math.pow((owner.getX() - o.getX()), 2) + (int)Math.pow((owner.getY()  - o.getY()),2));
                if(tempDist < minDist){

                    maxIndex = i;
                    minDist = tempDist;
                }
            }
            nearestPredator = newPredators.get(maxIndex);
        }
        else {
            return;
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

    //this program handles calculating the desire of each target
    private double calculateDesire(AbstOrganism o){
        int distance = 1;
        int energy = 0;
        //if the target is not null
        if (o != null){
            //get the distance from that target and the enery of that target
            distance = (int)Math.sqrt((int)Math.pow((owner.getX() - o.getX()), 2) + (int)Math.pow((owner.getY()  - o.getY()),2)) + 1;
            energy = o.siz;
        }
        else {
            distance = 1;
            energy = 0;
        }

        //return the energy over the distance, which will be the desireFactor when considering the best target
        return energy/distance;

    }

    public void interact(){
        //First check to see if the two objects are touching each other
        //If the owner is touching the target
        //Also make sure it isnt touching Algae (because who fights plants) 
        if(target.trophicLevel != 0){
            //if the target is a plant eater
            //dont lose any health
            if(target.trophicLevel == 1){
                return;
            }
            else {

                //if it isnt a plant eater
                //decrease your health by the att but add def (so really decrease health by (att - def)
                //NOTE: MAKE SURE THAT VALUE ISNT NEGATIVE BECAUSE THEN WE WILL BE INCREASING HEALTH
                owner.health -= (target.att - owner.def);
            }
        }

    }

    public void kill(){
        if(target != null){
            //check which type of organism it is
               world.carrion.add(new DeadOrganism());
                world.addObject(world.carrion.get(world.carrion.size()-1),Greenfoot.getRandomNumber(world.getWidth()),Greenfoot.getRandomNumber(world.getHeight()));
            if(target.trophicLevel == -1){
                //remove them from all lists
                //world.removeObject(target.energyIndicator);
                world.carrion.remove(target);
                target.energyIndicator.temp.clear();
                world.removeObject(target.energyIndicator);
            }
            if(target.trophicLevel == 0){
                //remove them from all lists
                world.plants.remove(target);
                target.energyIndicator.temp.clear();
                world.removeObject(target.energyIndicator);
            }
            if(target.trophicLevel == 1){
                //remove them from all lists
                world.herbivores.remove(target);
                target.energyIndicator.temp.clear();
                world.removeObject(target.energyIndicator);
            }
            if(target.trophicLevel == 2){
                //remove them from all lists
                world.scavengers.remove(target);
                target.energyIndicator.temp.clear();
                world.removeObject(target.energyIndicator);
            }
            if(target.trophicLevel == 3){
                //remove them from all lists
                world.carrion.add(new DeadOrganism());
                world.addObject(world.carrion.get(world.carrion.size()-1),Greenfoot.getRandomNumber(world.getWidth()),Greenfoot.getRandomNumber(world.getHeight()));
                world.omnivores.remove(target);
                target.energyIndicator.temp.clear();
                world.removeObject(target.energyIndicator);
            }
            if(target.trophicLevel == 4){
                //remove them from all lists
                world.carnivores.remove(target);
                target.energyIndicator.temp.clear();
                world.removeObject(target.energyIndicator);
            }
            //remove them from the arraylist
            //remove from world

        }
    }
}