package com.itcast.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itcast.util.VerifyCode;


/**
 * 验证码
 * @author DELL
 *
 */
@RestController
public class VerifyCodeController {

	
	@GetMapping("/vercode") 
	public void vercode (HttpServletRequest req,HttpServletResponse resp) throws IOException {
		VerifyCode vc = new VerifyCode ();
	BufferedImage image = vc.getImage ();
	String text = vc.getText ();
	HttpSession session = req.getSession ();
	session .setAttribute ("index_code", text);
	VerifyCode.output (image,resp.getOutputStream ());
	}
}
