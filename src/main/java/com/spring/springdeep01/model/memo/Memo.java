package com.spring.springdeep01.model.memo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.springdeep01.dto.MemoRequestDto;
import com.spring.springdeep01.model.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Memo extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


//    @Column(name="memo_id",nullable = false,unique = true)
//    private Long memoId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String content;

    @JsonIgnore
    @Column(nullable = false)
    private String memoPassword;


    public Memo(String username,String title, String contents,String memoPassword) {
        this.username = username;
        this.title = title;
        this.content = contents;
        this.memoPassword = memoPassword;
//        this.memoId = getId();
    }

    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.title = requestDto.getTitle();
        this.memoPassword = requestDto.getMemoPassword();
    }

    public void update(MemoRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.memoPassword = requestDto.getMemoPassword();
        this.title = requestDto.getTitle();
    }

}