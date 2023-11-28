package com.example.teama.service;

import com.example.teama.dto.Reply.ReplyActionDto;
import com.example.teama.dto.Reply.ReplySaveDto;
import com.example.teama.entity.Post;
import com.example.teama.entity.Reply;
import com.example.teama.persistence.PostRepository;
import com.example.teama.persistence.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    // 게시글 ID에 해당하는 모든 댓글 조회
    @Transactional(readOnly = true)
    public List<ReplyActionDto> findRepliesByPostId(Long id) {
        // 게시글 ID로 게시글 찾기
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 게시글에 속한 모든 댓글 조회 후 ReplyActionDto로 변환 후 반환
        List<Reply> replies = replyRepository.findByPost(post);
        if (replies.isEmpty()) {
            throw new IllegalArgumentException("해당 게시글에는 댓글이 없습니다.");
        }
        return replies.stream()
                .map(ReplyActionDto::new)
                .collect(Collectors.toList());
    }

    // 댓글 저장
    @Transactional
    public Long saveReply(ReplySaveDto requestDto) {
        // 게시글 ID로 게시글 찾기
        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // ReplySaveDto에서 필요한 정보 추출 후 댓글 생성 후 저장
        Reply reply = Reply.builder()
                .replyContent(requestDto.getReplyContent())
                .user(requestDto.getUser())
                .post(post)
                .build();

        return replyRepository.save(reply).getId();
    }


    // 댓글 작성
    @Transactional
    public void updateReply(Long replyId, ReplyActionDto requestDto) {
        Reply existingReply = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // 작성한 내용을 적용
        existingReply.setReplyContent(requestDto.getReplyContent());
        // 다른 필드가 있다면 추가로 작성

        // 작성 수행
        replyRepository.save(existingReply);
    }


    // 댓글 삭제
    @Transactional
    public void deleteReply(Long replyId) {
        // 특정 댓글을 삭제
        replyRepository.deleteById(replyId);
    }
}