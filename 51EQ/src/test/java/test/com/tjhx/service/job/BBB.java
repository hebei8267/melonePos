package test.com.tjhx.service.job;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

public class BBB {
	public static void main(String[] args) {

		FTPSClient ftpClient = new FTPSClient();
		try {
			ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
			ftpClient.connect("121.15.128.18");
			ftpClient.login("WHTS2FL2036", "m7Mre187");
			System.out.print(ftpClient.getReplyString());
			int reply = ftpClient.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}
			ftpClient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
					// do nothing
				}
			}
		}
	}

}
