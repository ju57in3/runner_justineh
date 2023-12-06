public class LifeCounter {
    private int lifeCount;
    public LifeCounter(int initialLifeCount){
        this.lifeCount = initialLifeCount;
    }
    public int getLifeCount(){
        return lifeCount;
    }
    public void decreaseLife(){
        lifeCount--;
    }
}
