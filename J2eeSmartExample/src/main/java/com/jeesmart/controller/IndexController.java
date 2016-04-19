package com.jeesmart.controller;

import org.j2eesmart.framework.annotation.Action;
import org.j2eesmart.framework.annotation.Controller;
import org.j2eesmart.framework.annotation.Inject;
import org.j2eesmart.framework.annotation.MethodType;
import org.j2eesmart.framework.pojo.RequestParam;
import org.j2eesmart.framework.view.JsonView;
import org.j2eesmart.framework.view.JspView;
import org.j2eesmart.framework.view.View;

import com.jeesmart.service.IndexService;

@Controller
public class IndexController {

	@Inject
	private IndexService indexService;

	@Action(urlPath = "/hello", methodType = MethodType.GET)
	public View indexGet(RequestParam request) {
		System.out.println(request);
		indexService.index();
		return new JspView("/index.jsp").addModel("hello", "hello");
	}

	@Action(urlPath = "/hello", methodType = MethodType.POST)
	public View indexPost(RequestParam request) {
		System.out.println(request);
		indexService.index();
		return new JspView("/index.jsp").addModel("hello", "hello");
	}

	@Action(urlPath = "/helloJson", methodType = MethodType.GET)
	public View indexJsonGet(RequestParam request) {
		System.out.println(request);
		indexService.index();
		return new JsonView(request);
	}

	@Action(urlPath = "/helloJson", methodType = MethodType.POST)
	public View indexJsonPost(RequestParam request) {
		System.out.println(request);
		indexService.index();
		return new JsonView(request);
	}
}
