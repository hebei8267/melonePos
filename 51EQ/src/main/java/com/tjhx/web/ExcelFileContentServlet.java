/**
 * 
 */
package com.tjhx.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author he_bei
 * 
 */
public class ExcelFileContentServlet extends HttpServlet {

	private static final long serialVersionUID = -3958151636382367942L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");
		String filename = request.getParameter("filename");

		if ("csv".equals(type)) {
			response.setContentType("application/csv");
		} else if ("xls".equals(type)) {
			response.setContentType("application/vnd.ms-excel");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		OutputStream output = response.getOutputStream();
		output.write(request.getParameter("data").getBytes());
		output.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
