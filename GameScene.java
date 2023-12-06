import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class GameScene extends Scene {
    // definition des variables
    private final Camera camera;
    private final StaticThing fin = new StaticThing("file:img/fin.png", 200, 200);
    private final StaticThing left = new StaticThing("file:img/fond.png",800,600);
    private final StaticThing right = new StaticThing("file:img/fond.png",800,600);
    private final Hero perso = new Hero("file:img/heros1.png", 300, 0);
    private final Foe mechant = new Foe("file:img/foe.png", 1000, 10);
    //protected static StaticThing life =
    private boolean jumpok=true;  // on allow le jump
    public boolean debJeu=true;
    AnimationTimer timer= new AnimationTimer() {
        public void handle(long time) {
            perso.update(time);
            camera.update(time);
            mechant.update(time);
            render();
        }
    };
    //_________________________________________________________________CONSTRUCTEUR__________________________________________________________________________________
    public GameScene(Group group, double v, double v1) {
        super(group, v, v1);
        camera = new Camera(300, 0, perso);
        StaticThing debut1 = new StaticThing("file:img/debut1.png", 600, 400);
        group.getChildren().add(debut1.getImage());
        group.getChildren().add(left.getImage());
        group.getChildren().add(right.getImage());
        group.getChildren().add(perso.getImage());
        group.getChildren().add(mechant.getImage());
        group.getChildren().add(fin.getImage());
        timer.start();
        render();
    }
//_____________________________________________________________________METHODE__________________________________________________________________________________
    // fonction sauter
    public void sauter(){
        if (perso.getyVitess()>0.2) {       //animation hero monte et descend
            perso.monte=true;
            perso.descend=false;}
        if (perso.getyVitess()<-0.4) {
            perso.descend=true;
            perso.monte=false;}
        if (perso.y<11) {
            perso.descend=false;
            perso.monte=false;}
        this.setOnKeyPressed(event->{   // on saute quand on presse espace
            if (event.getCode() == KeyCode.SPACE) {
                if (perso.getY()<=10.5 && jumpok){
                    perso.jump();
                }
            }
        });

    }
    // fonction render
    public void render(){
        mechant.debJeu=debJeu;
        perso.debJeu=debJeu;
        double xCam=camera.getX();
        double offsetLeft=xCam%left.getX();
        sauter();
        //game over
        if (debJeu){

            left.getImage().setX(-1000);
            right.getImage().setX(-1000);
            fin.getImage().setX(-1000);
            this.setOnKeyPressed(event->{
                if (event.getCode() == KeyCode.ENTER) {
                    debJeu=false;
                }
            });
        }
        if(!debJeu) {

            if (mechant.getX() <= perso.getX() + 50 && perso.getY() <= mechant.getY() + 10) {        //si on perd? on cache tout et on met l'ecran de fin  et on empeche de sauter sinon le jeu reprend
                fin.getImage().setX(125);
                perso.getImage().setX(-1000);
                perso.getImage().setY(20);
                mechant.getImage().setX(-2000);
                mechant.getImage().setY(100);
                jumpok = false;
                left.getImage().setX(-offsetLeft);
                right.getImage().setX(800-offsetLeft);
            }
            else {
                fin.getImage().setX(-1000);    // sinon on joue
                perso.getImage().setY(400 - 150 - perso.getY());
                perso.getImage().setX(perso.getX() - camera.getX());
                mechant.getImage().setY(400 - 150 - mechant.getY());
                mechant.getImage().setX(mechant.getX() - camera.getX());
                left.getImage().setX(-offsetLeft);
                right.getImage().setX(800-offsetLeft);
                if (mechant.getX() < perso.getX() - 50) {    //réapparaition du monstre à droite
                    mechant.x = perso.getX() + 1000;
                    mechant.Vmechant=mechant.Vmechant+0.25; // on fait accelerer le monstre

                }
            }
        }
    }
}