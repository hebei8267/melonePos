package com.tjhx.common.log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.p6spy.engine.logging.appender.FileLogger;

/**
 * p6spy日志自定义输出
 * 
 * @author
 * @version 1.00
 */
public class DBResultLogger extends FileLogger {
	private String fileName;

	public DBResultLogger() {
	}

	@Override
	public void setLogfile(String fileName) {
		this.fileName = fileName;
	}

	public void logSQL(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
		if ("resultset".equals(category)) {
			return;
		}
		String logEntry = "[" + now + "]" + category + "-->\n" + sql + "\n";

		OutputStreamWriter filewriter = null;
		try {
			filewriter = new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8");

			filewriter.write(logEntry);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (filewriter != null) {
					filewriter.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
