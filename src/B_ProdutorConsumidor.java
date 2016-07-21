public class B_ProdutorConsumidor {
	
	private int conteudo;
	private boolean isCheio;
	
	public int consome() {
		synchronized (this) {
			while(isCheio==false) {
				System.out.println("Está vazio, deve aguardar o preenchimento...");
				try { wait(); } catch (InterruptedException e) {}
				System.out.println("Agora tá cheio, e poderá ser consumido !");
			}
			isCheio = false;
			notifyAll();
			System.out.println("O valor foi consumido !");
			return conteudo;
		}
	}
	
	public void produz(int valor) {
		synchronized (this) {
			while(isCheio){
				try { wait(); } catch (InterruptedException e) {}
			}
			conteudo = valor;
			System.out.println("Produzido o valor "+valor);
			isCheio=true;
			notifyAll();
		}
	}
	
	@Override
	public String toString() {
		return "Conteudo: "+conteudo;
	}
	
	public static void main(String[] args) {
		final B_ProdutorConsumidor o = new B_ProdutorConsumidor();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try { Thread.sleep(10000); } catch (InterruptedException e) {}
				o.produz(10);
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				o.consome();
			}
		});
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(o.toString());
			}
		});
		
		//t3.start();
		t2.start(); 
		//t3.start();
		t1.start();
		//t3.start();
		
	}
}
//“Concurrent Programming in Java”, de Doug Lea.