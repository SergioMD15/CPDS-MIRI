public class Turn {

    private String turn;

    public Turn() {
        this.turn = "";
    }
    public synchronized void set(String s) {
        if (s.equals("alice")) {
            this.turn = "bob";
        }
        else {
            this.turn = "alice";
        }
    }
    public synchronized boolean is_my_turn(String name) {
        return this.turn.equals(name);
    }
}
