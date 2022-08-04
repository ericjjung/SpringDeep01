package com.spring.springdeep01.controller.response;

import com.spring.springdeep01.model.memo.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
//@NoArgsConstructor   생성자 ================
public class ResponseDto<T>{

    private Boolean success;
    @Nullable
    private List<T> data;
    private String error;


    public void Simple() {
        this.success = true;
        this.data = null;
        this.error = null;
    }

    public void Error(){
        this.error = "아직 에러 로직 안 만들었솨여";
    }


    ////임시로 돌아도록 만든 생성자
    public ResponseDto(List<T> testList,String error) {
        this.data = testList;
        if(testList.isEmpty()){
            this.success = false;
        }else{
            this.success = true;
        }
        this.error = error;
    }

//    /////  원래 목표 생성자.
//    public ResponseDto(Boolean success, List<T> testList,String error) {
//
//            this.data = testList;
//
//        this.success = success;
//        this.error = error;
//    }
}