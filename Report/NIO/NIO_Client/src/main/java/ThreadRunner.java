public class ThreadRunner {
	public static void main(String[] args) throws InterruptedException {
		int size = 100;
		for (int i = 0; i < size; i++) {
			NIOClient nio = new NIOClient();
			Thread th = new Thread(nio, i + "");
			th.start();
		}
		System.out.println("_____________END______________");
	}
}
