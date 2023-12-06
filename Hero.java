import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
public class Hero extends AnimatedThing{
    private double yVitess=1;
    public  boolean monte;
    public  boolean descend;
    public boolean debJeu;
    public Hero (String fileName, double x, double yr) {
        super(fileName, x, yr);
    }
    public double getyVitess() {
        return yVitess;
    }
    public void update(long time){
        if (!debJeu) {
            x = x + 5;
            long index = (time / durationFrame) % 6;
            if (monte) {
                image.setViewport(new Rectangle2D(0, 160, 85, 100));
            }
            if (descend) {
                image.setViewport(new Rectangle2D(85, 160, 85, 100));
            }
            if (!descend && !monte) {
                image.setViewport(new Rectangle2D(index * 85, 0, 85, 100));
            }
            y += yVitess;
            yVitess -= 0.5; //gravité
            if (y < 10) {     // il rebondit un peu en aterrissant
                y = 10;
                yVitess *= -0.2;
            }
        }
        else {image.setViewport(new Rectangle2D(-100, 160, 85, 100));} // on cache le perso
    }
    public void jump (){
        yVitess+=11;  // il saute
    }
    public void render(long time){
        time=time/3000000;
        this.getImage().setImage(new Image("file:img/heros1.png"));
    }
}