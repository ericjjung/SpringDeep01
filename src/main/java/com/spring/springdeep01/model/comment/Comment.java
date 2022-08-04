package com.spring.springdeep01.model.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.springdeep01.model.Timestamped;
import com.spring.springdeep01.model.memo.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment  extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String content;

//    @Column(nullable = false)
//    private Long memoId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "memo_id" )
    private Memo memo;

    public Comment(String username, String content,Long memoId){
        this.username = username;
        this.content = content;
//        this.memoId = memoId;
    }
    public void commentUpdate(){
        this.content = content;
    }


}
