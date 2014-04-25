package com.tjhx.web.member;

import java.security.KeyPair;

import javacryption.aes.AesCtr;
import javacryption.jcryption.JCryption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhx.globals.Constants;
import com.tjhx.web.BaseController;

@Controller
public class EncryptController extends BaseController {
	@ResponseBody
	@RequestMapping(value = "/encrypt")
	public String encrypt_Action(HttpServletRequest request, HttpSession session) {
		/** Generates a KeyPair for RSA **/
		if (request.getParameter("generateKeyPair") != null && request.getParameter("generateKeyPair").equals("true")) {

			JCryption jc = new JCryption();
			KeyPair keys = jc.getKeyPair();
			session.setAttribute(Constants.SESSION_ENCRYPT_KEY_PAIR, keys);
			String e = jc.getPublicExponent();
			String n = jc.getKeyModulus();
			String md = String.valueOf(jc.getMaxDigits());

			/** Sends response **/
			return "{\"e\":\"" + e + "\",\"n\":\"" + n + "\",\"maxdigits\":\"" + md + "\"}";
		}
		/** jCryption handshake **/
		else if (request.getParameter("handshake") != null && request.getParameter("handshake").equals("true")) {

			/** Decrypts password using private key **/
			JCryption jc = new JCryption((KeyPair) session.getAttribute(Constants.SESSION_ENCRYPT_KEY_PAIR));

			// key参数详见jquery.jcryption-1.2.js====197行
			String key = jc.decrypt(request.getParameter("key"));

			session.removeAttribute(Constants.SESSION_ENCRYPT_KEY_PAIR);
			session.setAttribute(Constants.SESSION_ENCRYPT_KEY, key);

			/** Encrypts password using AES **/
			String ct = AesCtr.encrypt(key, key, 256);

			/** Sends response **/
			return "{\"challenge\":\"" + ct + "\"}";

		}

		return null;
	}
}
