package training;

class Mnv{
	static int cost;
	
	public synchronized void increment() {
		cost++;
	}
}




public class Multithreading01 {

	public static void main(String[] args) throws Exception{

	

//		Thread t1 = new Thread(()->{
//			for(int i=0; i<5; i++) {
//				System.out.println("class A");
//				try {
//					Thread.sleep(1000);
//				}catch(Exception e) {
//					System.out.println(e.getMessage());
//				}
//			}
//		});
//		
//		
//		
//		
//		
//		
//		Thread t2 = new Thread(()->{
//				for(int i=0; i<5; i++) {
//				System.out.println("class B");
//				try {
//					Thread.sleep(1000);
//				}catch(Exception e) {
//					System.out.println(e.getMessage());
//				}
//			}
//			
//		});

		
//		t1.start();
//		try {
//			Thread.sleep(1000);
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		t2.start();
		
		Mnv obj1 = new Mnv();
//		Mnv obj2 = new Mnv();
		
//		obj1.increment();
//		obj2.increment();
		
		Thread t1 = new Thread(()->{
			for(int i=1; i<=5000; i++)
				obj1.increment();
		});
		
		Thread t2 = new Thread(()->{
			for(int i=1; i<=5000; i++)
				obj1.increment();
		});
		
		t1.join();
		t2.join();
		
		t1.start();
		t2.start();
		
		System.out.println(Mnv.cost);
//		System.out.println(t2.getPriority());
//		
//		System.out.println("Main thread");

	}

	
	
}