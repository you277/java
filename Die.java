public class Die {
    int sides;

    public void setSides(int s) {
        sides = s;
    }

    public int roll() {
        return (int)Math.round(Math.random()*sides);
    }
}
