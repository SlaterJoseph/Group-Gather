package com.groupgather.controllers;

import com.groupgather.services.GroupChatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chats")
public class GroupChatController {

    GroupChatService groupChatService;

    public GroupChatController(GroupChatService groupChatService){
        this.groupChatService = groupChatService;
    }
}
