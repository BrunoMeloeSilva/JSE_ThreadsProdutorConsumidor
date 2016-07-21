
public class Caixa {
	
	private int valor;

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	public static void main(String[] args) {
		final Caixa c = new Caixa();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					c.setValor(i);
					System.out.println(Thread.currentThread().getName()+"Entrou: "+i+" Saiu: "+c.getValor());
				}
			}
		}, "Tarefa1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 50; i < 100; i++) {
					c.setValor(i);
					System.out.println(Thread.currentThread().getName()+"Entrou: "+i+" Saiu: "+c.getValor());
				}
			}
		},"Tarefa2");
		
		t1.start();
		t2.start();
	}
}
