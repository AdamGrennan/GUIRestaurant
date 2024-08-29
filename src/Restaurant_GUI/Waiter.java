package Restaurant_GUI;

import java.util.Queue;

public class Waiter extends Thread {
    private Queue<Order> orderQueue;
    private Restaurant r;
    private Object lock;

    public Waiter(Queue<Order> orderQueue, Restaurant r, Object lock) {
        this.orderQueue = orderQueue;
        this.r = r;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            Order order;
            synchronized (lock) {
                if (orderQueue.isEmpty()) {
                    break;
                }
                order = orderQueue.poll();
            }

            if (order != null) {
                r.appendToDisplayOrders("\nWaiter has received order");
                try {
                    Thread.sleep(3000);
                    Chef chef = new Chef(order, r, this);
                    chef.start();
                } catch (InterruptedException e) {                
                    e.printStackTrace();
                } 
                

                synchronized (this) {
                    try {
                        wait(); // Wait for the chef to complete preparing the order
                        r.appendToDisplayOrders("\nWaiter is bringing " + order + " to customer");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
