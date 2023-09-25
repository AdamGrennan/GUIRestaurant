package GUI;

import java.util.ArrayList;

public class Chef extends Thread{
	//Instance of ArrayList
	ArrayList<Order>list = new ArrayList<Order>();
	//Instance of Restaurant
	Restaurant r;
	//Instance of Waiter
	Waiter w;
	
	//Chef Constructor
		public Chef(ArrayList<Order>list,Restaurant r,Waiter w){	
         this.list = list;
         this.r = r;
         this.w = w;
	
		}
		
		public void run() {
		    synchronized (w) {
			try {
				//Prepare all orders in list
				 for (int i = 0; i < list.size(); i++) {
				r.displayOrders.append("\nChef is preparing" + " " +list.get(i));
				//Wait time for preparing orders
				Chef.sleep(5000);
				r.displayOrders.append("\n" + list.get(i) + " " +"is ready");					    
				 }
			}		 
			 catch(InterruptedException e) {
				e.printStackTrace();		
		    }
			//Notify waiter once all items are prepared
			 w.notify();
		}
		}
		
}