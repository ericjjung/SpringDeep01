package com.spring.springdeep01.controller;


import com.spring.springdeep01.controller.response.ResponseDto;
import com.spring.springdeep01.dto.MemoRequestDto;
import com.spring.springdeep01.model.memo.Memo;
import com.spring.springdeep01.repository.MemoRepository;
import com.spring.springdeep01.dto.formCheck.WholeInqury;
import com.spring.springdeep01.repository.UserRepository;
import com.spring.springdeep01.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class MemoController {
    private final MemoRepository memoRepository;
    private final MemoService memoService;
    private final UserRepository userRepository;

    private List<?> responseList;
    private String error;



    /// 전체 게시물 조회  API
    @GetMapping("/api/memos")
    public ResponseDto readMemo(){
         List<WholeInqury> memo = memoRepository.findAllByOrderByIdDesc();
         ResponseDto responseDto = new ResponseDto(memo, error);
        return responseDto;
    }

    // 회원 게시물 조회
    @GetMapping("/api/memos/{id}")
    public ResponseDto getAuthorMemo(@PathVariable Iterable id){
        String error = "";
        List<Memo> testList = new ArrayList<>();
        try{
            testList = memoRepository.findAllById(id);
        }catch (Exception e){
            error = e.getMessage();
        }
        return new ResponseDto(testList, error );
    }

    // 회원 비밀번호 체크 API
    @PostMapping("/api/memos/check/{id}")
    public ResponseDto checkPassword(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        Boolean result;
        result = memoService.checkPassword(id,requestDto);
        Optional<Memo> testList = memoRepository.findById(id);
        List<Memo> memoList = new ArrayList<>();
        memoList.add(testList.get());
        String error = "";
        ResponseDto response;
        if(result){
            response = new ResponseDto(memoList,error);
        }else{
            throw new RuntimeException("비밀 번호가 잘못되거나 삭제된 글입니다.");
        }
        return response;
    }



    // 게시물 작성  API
    @PostMapping("/api/auth/memo")
    public ResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
        if(requestDto.getUsername().equals
                (userRepository.findByUsername(requestDto.getUsername()))
        ){

        }else {
            throw new RuntimeException("유저 이름이 맞지 않거나 비정상적인 접근입니다.");
        }
        Memo memo = new Memo(requestDto);
        memo = memoRepository.save(memo);
        List<Memo> setList = new ArrayList<>();
        setList.add(memo);
        return new ResponseDto(setList,error);
    }




    // 게시글 수정 API
    @PutMapping("/api/auth/memo/{id}")
    public ResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        memoService.update(id, requestDto);
        List<Memo> memoList = new ArrayList<>();
        memoList.add(memoRepository.findById(id).get());
        return new ResponseDto(memoList,error);
    }


    // 게시물 삭제 API
    @DeleteMapping("/api/auth/memo/{id}")
    public ResponseDto deleteMemo(@PathVariable Long id){
        memoRepository.deleteById(id);
        return new ResponseDto(null,error);
    }
}
