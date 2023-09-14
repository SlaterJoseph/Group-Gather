package com.groupgather.controllers;

import com.groupgather.services.GroupChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chats")
public class GroupChatController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupChatController.class);
    private final GroupChatService groupChatService;

    public GroupChatController(GroupChatService groupChatService){
        this.groupChatService = groupChatService;
    }
}
