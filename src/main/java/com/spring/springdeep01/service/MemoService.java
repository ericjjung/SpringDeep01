package com.spring.springdeep01.service;

import com.spring.springdeep01.dto.MemoRequestDto;
import com.spring.springdeep01.model.memo.Memo;
import com.spring.springdeep01.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Boolean checkPassword(Long id, MemoRequestDto requestDto){
        Boolean result;
        Memo memo = memoRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("잘못된 비밀번호거나 삭제된 게시물입니다.")
        );
        if(requestDto.getMemoPassword().equals(memo.getMemoPassword())){
            result = true;
        }else{
            result = false;
        }

        return result;
    }

    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        memo.update(requestDto);
        return memo.getId();
    }
}