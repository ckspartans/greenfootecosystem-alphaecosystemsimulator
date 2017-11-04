import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pumpkin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pumpkin extends Actor
{
    MyWorld world;
    /**
     * Act - do whatever the Pumpkin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(world == null){
           world = (MyWorld)getWorld();
        }
         eatFood();
    } 
    public void eatFood(){
                //if the dolphin and the food intersect (meaning that the dolphin is eating the food), remove the food
                if(getOneIntersectingObject(TestOrganism.class) != null){
                getWorld().removeObject(this);
                world.foodEaten++;
                }
    }
}
