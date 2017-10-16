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
    public AbstOrganism owner = null;
    public int energyFactor;
    public int desireFactor = 0;
    // public static List<AbstOrganism> targets;
    /**
     * Constructor for objects of class AI
     */
    public AI(AbstOrganism _owner)
    {
        owner = _owner;
    }

    public void track(){
        
        if(owner.targets == null){
            return;   
        }
        if(owner.targets != null){
            findMostDesired();
        }
        if(target != null){
                //owner.setLocation(owner.getX() + (target.getX()-owner.getX()) , owner.getY() + (target.getY()-owner.getY()));
                owner.energyFactor = target.energy;
                owner.turnTowards(target.getX(),target.getY());
        }
             target = null;   
        }
        //owner.move(owner.speed-4);  
        //owner.turnTowards(target.getX(), target.getY());
       // if (target == null){
           // owner.energyFactor = 0;
        //}
    private void findMostDesired(){
            double maxDesire = 0;
            int maxIndex = 0;
            List <AbstOrganism> targets = new ArrayList<AbstOrganism>(owner.targets);
            ArrayList <AbstOrganism> newTargets = new ArrayList<AbstOrganism>();
          for (int i = 0; i< targets.size(); i++){
              AbstOrganism o = targets.get(i);
                  if(owner.trophicLevel == 2){
                      if (owner.trophicLevel >= o.trophicLevel){
                                // System.out.println("LOOOL");
                                 //targets.remove(o);
                                    newTargets.add(o);
                      }
                  }
                  if(owner.trophicLevel == 3){
                      if (owner.trophicLevel >= o.trophicLevel && o.trophicLevel != 0){
                                // System.out.println("LOOOL");
                                 //targets.remove(o);
                                    newTargets.add(o);
                      }
                  }
                  if(owner.trophicLevel == 1){
                      if (o.trophicLevel == 0){
                                // System.out.println("LOOOL");
                                 //targets.remove(o);
                                    newTargets.add(o);
                      }
                  }
          }
          removeNull(newTargets);
          if(newTargets != null && !newTargets.isEmpty()){
              for(int i = 0; i < newTargets.size(); i++){
                  AbstOrganism o = newTargets.get(i);
                  double tempDesire = 0;
                  removeNull(newTargets);
                if(newTargets.get(i) != null && o != null){
                    removeNull(newTargets);
                    tempDesire = calculateDesire(o);
                }
                if (tempDesire > maxDesire){
                  //System.out.println("ookokokok");
                   maxIndex = i;
                  maxDesire = tempDesire;
                }
              }
              target = newTargets.get(maxIndex);
          }
          else{
              return;
          }
                //System.out.println(maxIndex);
                 
               
    }
    public void flee(){
        List <AbstOrganism> predators  = new ArrayList <AbstOrganism> (owner.targets);
        ArrayList <AbstOrganism> newPredators = new ArrayList<AbstOrganism>();
        double minDist = 1000;
        int maxIndex = 0;
          for (int i = 0; i< predators.size(); i++){
              AbstOrganism o = predators.get(i);
              if (owner.trophicLevel <= o.trophicLevel){
                            // System.out.println("LOOOL");
                             //targets.remove(o);
                                newPredators.add(o);
              }
             // AbstOrganism closestPredator = newPredators.getNearest();
          }
          if(newPredators != null && !newPredators.isEmpty()){
              for(int i = 0; i < newPredators.size(); i++){
                  AbstOrganism o = newPredators.get(i);
                  double tempDist = (int)Math.sqrt((int)Math.pow((owner.getX() - o.getX()), 2) + (int)Math.pow((owner.getY()  - o.getY()),2));
                 if(tempDist < minDist){
                  //System.out.println("ookokokok");
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
    private double calculateDesire(AbstOrganism o){
        //int distance = dis
        int distance = 1;
        int energy = 0;
        if (o != null){
            distance = (int)Math.sqrt((int)Math.pow((owner.getX() - o.getX()), 2) + (int)Math.pow((owner.getY()  - o.getY()),2)) + 1;
            energy = o.siz;
        }
        else {
            distance = 1;
            energy = 0;
        }
       
         

      return energy/distance;

    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    /*public void track(){

        if (target == null) {
            // Look for targets

            List <AbstOrganism> targets = owner.targets;
            if (!targets.isEmpty()){
                // If possible targets seen, choose one at random.
                //System.out.println("TARGET FOUND");
                int choice = Greenfoot.getRandomNumber(targets.size());
                target = targets.get(choice);
            }
        }
        //target acquired. Seek!
        else {
            //System.out.println("TARGET IS NOT NULL");
            System.out.println("S : " + owner.trophicLevel);
            System.out.println("T : " + target.trophicLevel);
            if (owner.trophicLevel > target.trophicLevel){
                // Close 90% of distance to target.  See Achilles/Tortoise.. :wink:
                //turnTowards(target.getX(), target.getY());
                //move((int)Math.sqrt((int)Math.pow(target.getX(),2) + (int)Math.pow(target.getY(),2)));
                // System.out.println("WE MADE ITTTTT");
                owner.setLocation(owner.getX() + (target.getX()-owner.getX()) , owner.getY() + (target.getY()-owner.getY()));
                energyFactor = target.siz;
                //if(getX() == target.getX() && getY() == target.getY()){
                target = null;

                //}
            }
            else {
                target = null;
            }
        }
    }*/   
}

