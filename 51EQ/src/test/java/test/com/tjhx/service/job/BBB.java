package test.com.tjhx.service.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

public class BBB {
	public static void main(String[] args) {

		FTPSClient ftpClient = new FTPSClient();
		try {
			ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
			// ftpClient.setAuthValue("SSL");
			ftpClient.connect("121.15.128.18");
			ftpClient.login("WHTS2FL2036", "m7Mre187");
			ftpClient.setDefaultPort(21);

			System.out.print(ftpClient.getReplyString());
			int reply = ftpClient.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}
			ftpClient.enterLocalPassiveMode();

			// 设置上传目录
			ftpClient.changeWorkingDirectory("/");
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			boolean res = ftpClient.storeFile("test", new FileInputStream(new File("/Users/tao_tao/Documents/test")));
			System.out.println("$$$$$$$$$$$$$$$$" + res);
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
