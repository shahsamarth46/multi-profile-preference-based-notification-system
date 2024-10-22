package com.example.userdetection.user_detection_service.controller;

import com.example.userdetection.user_detection_service.constant.UrlConstants;
import com.example.userdetection.user_detection_service.service.UserDetectionService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlConstants.API)
@RequiredArgsConstructor
public class UserDetectionController {

    @Autowired
    private UserDetectionService userDetectionService;

    @PostMapping(UrlConstants.DETECT_USERID)
    public ResponseEntity<String> detectUser(@PathVariable String userId) {
        String message = userDetectionService.sendUserDetectionEvent(userId);
        return ResponseEntity.ok(message);
    }
}
