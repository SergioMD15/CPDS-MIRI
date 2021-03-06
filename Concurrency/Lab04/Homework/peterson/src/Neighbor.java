public class Neighbor extends Thread {

    private Flags flags;
    private Turn turn;

    public Neighbor(Flags flags, Turn turn) {
        this.flags = flags;
        this.turn = turn;
    }
    public void run() {
        while (true) {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("try again, my name is: " + name);
                this.flags.set_true(name);
                this.turn.set(name);
                //To model greedy write the sleep as follows
                Thread.sleep((int)(200 * Math.random()));
                while ((!this.flags.query_flag(name)) && this.turn.is_my_turn(name)) {
                    System.out.println(name + " waits");
                    Thread.sleep(200);
                }
                System.out.println(name + " enters");
                Thread.sleep(400);
                System.out.println(name + " exits");
                Thread.sleep((int)(200 * Math.random()));
                this.flags.set_false(name);
            }
            catch (InterruptedException e) {};
        }
    }
}
