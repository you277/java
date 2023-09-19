public class Die {
    private int sides;

    public Die(int s) {
        sides = s;
    }

    public void setSides(int s) {
        sides = s;
    }

    public int roll() {
        return (int)Math.round(Math.random()*sides);
    }
}
