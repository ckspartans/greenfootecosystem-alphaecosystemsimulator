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
    // calls constructors for slider classes
    public static AlgaeSlider aSlider = new AlgaeSlider();
    public static HerbivoreSlider hSlider = new HerbivoreSlider();
    public static OmnivoreSlider oSlider = new OmnivoreSlider();
    public static CarnivoreSlider cSlider = new CarnivoreSlider();
    public static ScavengerSlider sSlider = new ScavengerSlider();
    
    // sets gameState to 0 (start screen)
    int state = 0;
    /**
     * Constructor for objects of class IntroScreen.
     * 
     */
    public IntroScreen()
    {    
        // starts game with screen size 1000px*1000px
        super(1000, 1000, 1);

    }

    public IntroScreen(MyWorld w)
    {    
        // starts game with screen size 1000px*1000px
        super(1000, 1000, 1);
        world = w;
    }

    public void act(){
        // if game state is 0 (start)
        if(state == 0) {
            // removes sliders (doesnt call yet)
            removeObject(aSlider);
            removeObject(hSlider);
            removeObject(oSlider);
            removeObject(cSlider);
            removeObject(sSlider);
            // if s is pressed
            if(Greenfoot.isKeyDown("s")){
            //start button (make sure it makes state == 1 )
                state = 1;
                setBackground("sliders.png");
            }       
        }
        
        // if game state is 1 (customization of simulation)
        if(state == 1) {
            // adds slider objects and constructs them
            addObject(aSlider, 525 , 75);
            addObject(hSlider, 525 , 175);
            addObject(oSlider , 525 , 275);
            addObject(cSlider, 525 , 375);
            addObject(sSlider, 525 , 475);
            
            // if space key is pressed
            if (Greenfoot.isKeyDown("SPACE")){
                // starts ecosystem simulation
                Greenfoot.setWorld(world);
            }
            
           
        }
        
    }
}
