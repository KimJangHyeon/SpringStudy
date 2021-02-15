import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
	private static final int BUFFER_SIZE = 512;
	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel serverSocket = ServerSocketChannel.open();
		InetSocketAddress serverAddr = new InetSocketAddress("localhost", 1111);
		serverSocket.bind(serverAddr);
		serverSocket.configureBlocking(false);
		serverSocket.register(selector, serverSocket.validOps(), null);
		while (true) {
			selector.select();
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
			while (selectionKeyIterator.hasNext()) {
				SelectionKey selectionKey = selectionKeyIterator.next();
				if (selectionKey.isAcceptable()) {
					SocketChannel socketChannel = serverSocket.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ);
				} else if (selectionKey.isReadable()) {
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
					socketChannel.read(buffer);
					String result = new String(buffer.array()).trim();
					if (result.equals("END_SIGN")) {
						socketChannel.close();
					}
				}
				selectionKeyIterator.remove();
			}
		}
	}
}