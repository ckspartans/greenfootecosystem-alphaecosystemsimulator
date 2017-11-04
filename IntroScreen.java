import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroScreen extends World
{
    MyWorld world;
    public static AlgaeSlider aSlider = new AlgaeSlider();
    public static HerbivoreSlider hSlider = new HerbivoreSlider();
    public static OmnivoreSlider oSlider = new OmnivoreSlider();
    public static CarnivoreSlider cSlider = new CarnivoreSlider();
    public static ScavengerSlider sSlider = new ScavengerSlider();
    int state = 0;
    /**
     * Constructor for objects of class IntroScreen.
     * 
     */
    public IntroScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 1000, 1);

    }

    public IntroScreen(MyWorld w)
    {    
        super(1000, 1000, 1);
        world = w;
    }

    public void act(){
        if(state == 0) {
            //start button (make sure it makes state == 1 )
            removeObject(aSlider);
            removeObject(hSlider);
            removeObject(oSlider);
            removeObject(cSlider);
            removeObject(sSlider);
            if(Greenfoot.isKeyDown("s")){
                state = 1;
                setBackground("sliders.png");
            }       
        }
        
        if(state == 1) {
            addObject(aSlider, 525 , 75);
            addObject(hSlider, 525 , 175);
            addObject(oSlider , 525 , 275);
            addObject(cSlider, 525 , 375);
            addObject(sSlider, 525 , 475);
            
            
            if (Greenfoot.isKeyDown("SPACE")){
                Greenfoot.setWorld(world);
            }
            
           
        }
        
    }
}