import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class NIOClient implements Runnable{
	
	public static String encryptAES256(String msg, String key) throws Exception {
		
		SecureRandom random = new SecureRandom();
		
		byte bytes[] = new byte[20];
		
		random.nextBytes(bytes);
		
		byte[] saltBytes = bytes;
		
		// Password-Based Key Derivation function 2
		
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		
		PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);
		
		SecretKey secretKey = factory.generateSecret(spec);
		
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
		
		// CBC : Cipher Block Chaining Mode
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		
		AlgorithmParameters params = cipher.getParameters();
		
		
		byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
		
		byte[] encryptedTextBytes = cipher.doFinal(msg.getBytes("UTF-8"));
		
		byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];
		
		System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
		
		System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
		
		System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length,
				encryptedTextBytes.length);
		
		return Base64.getEncoder().encodeToString(buffer);
		
	}
	public static String decryptAES256(String msg, String key) throws Exception {
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(msg));
		
		
		
		byte[] saltBytes = new byte[20];
		
		buffer.get(saltBytes, 0, saltBytes.length);
		
		byte[] ivBytes = new byte[cipher.getBlockSize()];
		
		buffer.get(ivBytes, 0, ivBytes.length);
		
		byte[] encryoptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes.length];
		
		buffer.get(encryoptedTextBytes);
		
		
		
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);
		
		
		
		SecretKey secretKey = factory.generateSecret(spec);
		
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
		
		
		
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));
		
		
		
		byte[] decryptedTextBytes = cipher.doFinal(encryoptedTextBytes);
		
		return new String(decryptedTextBytes);
		
	}
	@Override
	public void run() {
		try {
			InetSocketAddress socketAddr = new InetSocketAddress("localhost", 1111);
			SocketChannel socketClient = SocketChannel.open(socketAddr);
			System.out.println("AAA");
			ByteBuffer buffer = null;
			for (int i = 0; i < 1; i++) {
				String str = Integer.toString(i);
				String message = encryptAES256(str, "abc");
				buffer = ByteBuffer.wrap(message.getBytes());
				socketClient.write(buffer);
				buffer.clear();
				Thread.sleep(1000);
			}
			buffer = ByteBuffer.wrap("END_SIGN".getBytes());
			socketClient.write(buffer);
			buffer.clear();
			Thread.sleep(2000);
			socketClient.close();
		}
		catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
		}
	}
}
