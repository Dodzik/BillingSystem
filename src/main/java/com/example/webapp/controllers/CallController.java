package com.example.webapp.controllers;

import com.example.webapp.entities.Call;
import com.example.webapp.entities.User;
import com.example.webapp.repository.CallRepository;
import com.example.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CallController {

    private final CallRepository callRepository;
    private final UserRepository userRepository;

    @Autowired
    public CallController(CallRepository callRepository, UserRepository userRepository) {
        this.callRepository = callRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/calls")
    public String getCalls(Model model) {
        List<Call> calls = callRepository.findAll();
        model.addAttribute("calls", calls);

        return "calls";
    }

    @RequestMapping(value = "/user/{mainNumber}")
    public String getOneCalls(Model model, @PathVariable String mainNumber) {
        List<Call> calls = callRepository.findCallsByMainNumber(mainNumber);
        model.addAttribute("calls", calls);
        User user = userRepository.findUserByMainNumber(mainNumber);
        model.addAttribute("user", user);
        List<Call> outgoingCalls = callRepository.findCallsByTargetNumber(mainNumber);
        model.addAttribute("outgoingCalls", outgoingCalls);

        return "userDetails";
    }

    @RequestMapping("/calls/SortedByMainNumberAsc")
    public String getCallsMainNumberAsc(Model model) {
        model.addAttribute("calls", callRepository.findByOrderByMainNumberAsc());

        return "calls";
    }

    @RequestMapping("/calls/SortedByMainNumberDesc")
    public String getCallsMainNumberDesc(Model model) {
        model.addAttribute("calls", callRepository.findByOrderByMainNumberDesc());

        return "calls";
    }

    @RequestMapping("/calls/SortedByTargetNumberAsc")
    public String getCallsTarget_numberAsc(Model model) {
        model.addAttribute("calls", callRepository.findByOrderByTargetNumberAsc());

        return "calls";
    }

    @RequestMapping("/calls/SortedByTargetNumberDesc")
    public String getCallsTarget_numberDesc(Model model) {
        model.addAttribute("calls", callRepository.findByOrderByTargetNumberDesc());

        return "calls";
    }

    @RequestMapping("/calls/SortedByTalkTimeAsc")
    public String getCallsTalkTimeAsc(Model model) {
        model.addAttribute("calls", callRepository.findByOrderByTalkTimeAsc());

        return "calls";
    }

    @RequestMapping("/calls/SortedByTalkTimeDesc")
    public String getCallsTalkTimeDesc(Model model) {
        model.addAttribute("calls", callRepository.findByOrderByTalkTimeDesc());

        return "calls";
    }

    @RequestMapping("/calls/SortedByDataAsc")
    public String getCallsDataAsc(Model model) {
        model.addAttribute("calls", callRepository.findByOrderByDateAsc());

        return "calls";
    }

    @RequestMapping("/calls/SortedByDataDesc")
    public String getCallsDataDesc(Model model) {
        model.addAttribute("calls", callRepository.findByOrderByDateDesc());

        return "calls";
    }
}
