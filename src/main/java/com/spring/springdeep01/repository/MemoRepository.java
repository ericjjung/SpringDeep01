package com.spring.springdeep01.repository;

import com.spring.springdeep01.dto.formCheck.MemoCheck;
import com.spring.springdeep01.dto.formCheck.UserMemoCheck;
import com.spring.springdeep01.dto.formCheck.WholeInqury;
import com.spring.springdeep01.model.memo.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<WholeInqury> findAllByOrderByIdDesc();
    List<UserMemoCheck> findByUsername(String username);
}