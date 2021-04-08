package ca.sheridancollege.zhanyiya.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.zhanyiya.beans.Reply;
import ca.sheridancollege.zhanyiya.beans.Thread;
import ca.sheridancollege.zhanyiya.beans.User;
import ca.sheridancollege.zhanyiya.repositories.ReplyRepository;
import ca.sheridancollege.zhanyiya.repositories.ThreadRepository;
import ca.sheridancollege.zhanyiya.repositories.UserRepository;
import lombok.AllArgsConstructor;

/*
 * 
 * IMPORTANT: PORT CHANGED TO: 8443
 * 
 */

@Controller
@AllArgsConstructor
public class ForumController {
	private ThreadRepository threadRepository;
	private ReplyRepository replyRepository;
	private UserRepository userRepository;
	
	@GetMapping("/forum")
	public String forum(Authentication authentication, Model model) {
		model.addAttribute("threads", threadRepository.findAll());
		return "/forum/index";
	}
	
	@GetMapping("/forum/createThread")
	public String createThread(Authentication authentication, Model model) {
		model.addAttribute("newThread", new Thread());
		return "/forum/create";
	}
	
	@PostMapping("/forum/createThread")
	public String addThread(Authentication authentication, Model model, @ModelAttribute Thread newThread) {
		User user = userRepository.findByName(authentication.getName());
		newThread.setCreator(user);
		threadRepository.save(newThread);
		return "redirect:/forum";
	}
	
	@PostMapping("/forum/thread")
	public String thread(Model model, @RequestParam Long threadId) {
		Optional<Thread> thread = threadRepository.findById(threadId);
		if (thread.isPresent()) {
			model.addAttribute("thread", thread.get());
			model.addAttribute("newReply", new Reply());
			model.addAttribute("replies", replyRepository.findByThread_IdOrderByTimeAsc(threadId));
		}
		return "/forum/thread";
	}
	
	@PostMapping("/forum/reply")
	public String reply(Authentication authentication, Model model, @RequestParam Long threadId, @ModelAttribute Reply newReply) {
		model.addAttribute("threadId", threadId);
		Optional<Thread> thread = threadRepository.findById(threadId);
		if (thread.isPresent()) {
			newReply.setThread(thread.get());
			User user = userRepository.findByName(authentication.getName());
			newReply.setCreator(user);
			replyRepository.save(newReply);
			
			model.addAttribute("thread", thread.get());
			model.addAttribute("replies", replyRepository.findByThread_IdOrderByTimeAsc(threadId));
			model.addAttribute("newReply", new Reply());
		}
		return "/forum/thread";
	}
}
