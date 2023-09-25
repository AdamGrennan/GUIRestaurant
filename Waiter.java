package GUI;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Waiter extends Thread{
	//Instance of ArrayList
	ArrayList<Order>list = new ArrayList<Order>();
	//Instance of Restaurant
	Restaurant r;
	
	//Waiter constructor
	public Waiter(ArrayList<Order>list,Restaurant r){	
	this.list = list;
	this.r = r;
	}
	
public void run() {
	 synchronized(this) {
	r.displayOrders.append("\nWaiter has received order");
	//Passing arraylist to chef and starting their thread
   Waiter w = new Waiter(list,r);
   Chef c = new Chef(list,r,w);
   c.start();


   try { 
	   synchronized(w) {
		   //Wait until chef has prepared order and notify waiter	
		   w.wait(); 
		   //Bring all prepared items to customer
		   for (int i = 0; i < list.size(); i++) {
			r.displayOrders.append("\nWaiter is bringing" + " " +list.get(i)+ "" +" to customer");
			
			 }
		   //Remove all orders once they are completed & allow for new order
		  list.removeAll(list);
		  r.confirmOrder.setEnabled(true);
		
	   }
   }
 catch (InterruptedException e) {
	e.printStackTrace();
}
	 }
}
}