public class Cost {
    private int side;
    private String colour;
    private int counter;

    // Constructor
    public Cost(int side, String colour, int counter) {
        this.side = side;
        this.colour = colour;
        this.counter = counter;
    }

    // Getters 
    public int getSide() {
        return side;
    }

    public String getColour() {
        return colour;
    }

    public int getCounter() {
        return counter;
    }

    // Setters
    public void setSide(int side) {
        this.side = side;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
