import javafx.geometry.Rectangle2D;
public class Foe extends AnimatedThing {
    public double Vmechant=2;
    public boolean debJeu;
    public Foe(String fileName, double x, double y) {
        super(fileName, x, y);
    }
    public void update(long time){
        if (!debJeu) {
            x = x - Vmechant;
            long index = (time / durationFrame) % 2;
            image.setViewport(new Rectangle2D(index * 100, 0, 100, 100));
        }
        else{image.setViewport(new Rectangle2D(-1000, 0, 100, 100));}
    }
}