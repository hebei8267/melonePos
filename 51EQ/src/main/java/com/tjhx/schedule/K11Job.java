package com.tjhx.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjhx.common.utils.DateUtils;

public class K11Job implements IJob {
	private static Logger logger = LoggerFactory.getLogger(K11Job.class);

	public static void main(String[] args) throws ParseException {
		String nowDate = DateUtils.getCurFormatDate("yyyyMMdd");
		String runDate = DateUtils.getNextDateFormatDate(nowDate, -1, "yyyyMMdd");
		run(runDate);
	}

	public static void run(String runDate) {
		String command = "d:\\java -jar helper-miya-DataTurns-2.0-SNAPSHOT.jar " + runDate;
		String line = null;
		StringBuilder sb = new StringBuilder();
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(command);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
				System.out.println(line);
			}

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		// java -jar helper-miya-DataTurns-2.0-SNAPSHOT.jar 20180712
		logger.info("K11商场销售数据同步FTP Begin");

		String nowDate = DateUtils.getCurFormatDate("yyyyMMdd");
		String runDate = DateUtils.getNextDateFormatDate(nowDate, -1, "yyyyMMdd");

		run(runDate);
		logger.info("K11商场销售数据同步FTP End");
	}
}
