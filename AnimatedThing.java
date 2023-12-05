import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing {
    protected double x;
    protected double y;
    protected ImageView image;
    private int attitude;
    private int index;
    protected int durationFrame=100000000;
    public AnimatedThing(String fileName, double x, double y) {
        this.x = x;
        this.y = y;
        this.image = new ImageView(new Image(fileName));
    }
    public ImageView getImage() {
        return image;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
}