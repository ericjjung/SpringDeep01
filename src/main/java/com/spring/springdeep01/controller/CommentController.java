package com.spring.springdeep01.controller;


import com.spring.springdeep01.controller.response.ResponseDto;
import com.spring.springdeep01.dto.CommentDto;
import com.spring.springdeep01.dto.formCheck.UserMemoCheck;
import com.spring.springdeep01.model.comment.Comment;
import com.spring.springdeep01.model.memo.Memo;
import com.spring.springdeep01.repository.CommentRepository;
import com.spring.springdeep01.repository.MemoRepository;
import com.spring.springdeep01.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class CommentController {

    private final MemoRepository memoRepository;
    private final CommentRepository commentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private String error;


    ////유저이름의 게시물 전체 조회
    @GetMapping("/api/comment/{username}")
    public ResponseDto allComment(@PathVariable String username){
       List<UserMemoCheck> response = memoRepository.findByUsername(username);
       return new ResponseDto<>(response,error);
    }




    ///  댓글 생성
    @PostMapping("/api/auth/comment")
    public ResponseDto createComment(@RequestBody CommentDto commentDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        String username = jwtTokenProvider.getUserPk(token);
        Comment comment = new Comment(commentDto.getContent(),username,commentDto.getMemoId());
        Memo memo = memoRepository.findById(commentDto.getMemoId()).orElseThrow(
                ()->new IllegalArgumentException("해당 게시물이 없습니다.")
        );
        comment.setMemo(memo);
        commentRepository.save(comment);
        List<Comment> setform = new ArrayList<>();
        setform.add(comment);
         return new ResponseDto<>(setform,error);
    }

    ///  댓글 수정
    @PutMapping("/api/auth/comment/{id}")
    public ResponseDto updateComment(@PathVariable Long id,@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                     @RequestBody CommentDto commentDto){
        String username = jwtTokenProvider.getUserPk(token);
        Comment comment = new Comment(commentDto.getContent(),username,commentDto.getMemoId());
        Memo memo = memoRepository.findById(commentDto.getMemoId()).orElseThrow(
                ()->new IllegalArgumentException("해당 게시물이 없습니다.")
        );
        comment.commentUpdate();
        List<Comment> response = new ArrayList<>();
        response.add(comment);
        return new ResponseDto<>(response,error);
    }



    // 댓글 삭제 API
    @DeleteMapping("/api/auth/comment/{id}")
    public ResponseDto deleteMemo(@PathVariable Long id){
        List<Comment> response = new ArrayList<>();
        response.add(commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("없는 게시물 입니다.")));
        commentRepository.deleteById(id);
        return new ResponseDto(response,error);
    }
}
