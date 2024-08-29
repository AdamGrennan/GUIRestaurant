package Restaurant_GUI;

public class Chef extends Thread {
    private Order order;
    private Restaurant r;
    private Waiter waiter;

    public Chef(Order order, Restaurant r, Waiter waiter) {
        this.order = order;
        this.r = r;
        this.waiter = waiter;
    }

    @Override
    public void run() {
        synchronized (waiter) {
            try {
                r.appendToDisplayOrders("\nChef is preparing " + order);
                Thread.sleep(5000); // Simulate preparation time
                r.appendToDisplayOrders("\n" + order + " is ready");
                waiter.notify(); // Notify the waiter that this order is ready
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
