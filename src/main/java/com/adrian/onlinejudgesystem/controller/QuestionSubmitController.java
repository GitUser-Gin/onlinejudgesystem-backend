package com.adrian.onlinejudgesystem.controller;

import com.adrian.onlinejudgesystem.common.BaseResponse;
import com.adrian.onlinejudgesystem.common.ErrorCode;
import com.adrian.onlinejudgesystem.common.ResultUtils;
import com.adrian.onlinejudgesystem.exception.BusinessException;
import com.adrian.onlinejudgesystem.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.adrian.onlinejudgesystem.model.entity.User;
import com.adrian.onlinejudgesystem.service.QuestionSubmitService;
import com.adrian.onlinejudgesystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *  题目提交接口
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 提交记录的id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                         HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        Long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

}
