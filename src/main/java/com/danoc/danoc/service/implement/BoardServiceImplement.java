package com.danoc.danoc.service.implement;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.danoc.danoc.dto.ResponseDto;
import com.danoc.danoc.dto.request.board.BoardDeleteRequestDto;
import com.danoc.danoc.dto.request.board.BoardEditRequestDto;
import com.danoc.danoc.dto.request.board.BoardWriteRequestDto;
import com.danoc.danoc.dto.response.board.BoardDeleteResponseDto;
import com.danoc.danoc.dto.response.board.BoardEditResponseDto;
import com.danoc.danoc.dto.response.board.BoardReadResponseDto;
import com.danoc.danoc.dto.response.board.BoardWriteResponseDto;
import com.danoc.danoc.entity.BoardEntity;
import com.danoc.danoc.entity.ImageEntity;
import com.danoc.danoc.repository.BoardRepository;
import com.danoc.danoc.repository.ImageRepository;
import com.danoc.danoc.repository.UserRepository;
import com.danoc.danoc.repository.resultSet.BoardReadResultSet;
import com.danoc.danoc.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {
    
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<BoardEntity> boardList() {
        try {
            return boardRepository.findAll();
        } catch (Exception e) {
            log.debug("게시판 목록을 불러올 수 없습니다", e);
            // 예외가 발생할 경우 null을 반환하거나 빈 목록을 반환할 수 있습니다.
            return Collections.emptyList(); // 빈 목록 반환
            // 또는 원하는 방식에 따라 예외 처리 로직을 추가하여 반환값을 결정할 수 있습니다.
        }
    }
    

        @Override
        public ResponseEntity<? super BoardWriteResponseDto> boardWrite(BoardWriteRequestDto dto, Long userId){
            try {

               boolean existsByUserId = userRepository.existsByUserId(userId);
               if (!existsByUserId) return BoardWriteResponseDto.userNotFound();
        
                // 게시판 엔티티 생성 및 저장
                BoardEntity boardEntity = new BoardEntity(dto,userId);
                boardRepository.save(boardEntity);

                Long boardId = boardEntity.getBoardId();
                List<String> boardImageList = dto.getBoardImageList();
                
                // 이미지가 없는 경우 또는 이미지 리스트가 비어있는 경우 처리
                if (boardImageList == null || boardImageList.isEmpty()) {
                    log.debug("이미지가 없는 게시물 작성");

                    log.debug("작성 성공");
                    return BoardWriteResponseDto.success();
                } else {
                    // 이미지가 있는 경우 이미지 엔티티 생성 및 저장
                    List<ImageEntity> imageEntities = new ArrayList<>();
                    for (String image : boardImageList) {
                        ImageEntity imageEntity = new ImageEntity(boardId, image);
                        imageEntities.add(imageEntity);
                    }
                    imageRepository.saveAll(imageEntities);

                    log.debug("이미지가 포함된 게시물 작성 성공");
                    return BoardWriteResponseDto.success();
        }

            } catch (Exception e) {
                log.debug("게시판 작성 에러", e);
                return ResponseDto.databaseError();
            }
    }

        @Override
        public ResponseEntity<? super BoardDeleteResponseDto> boardDelete(BoardDeleteRequestDto dto) {
            try {
                // 1. 요청된 게시물 ID를 사용하여 해당 게시물을 데이터베이스에서 찾습니다.
                Optional<BoardEntity> optionalBoard = boardRepository.findById(dto.getBoardId());
        
                if (!optionalBoard.isPresent()) {
                    return BoardDeleteResponseDto.deleteFail();
                }
                BoardEntity board = optionalBoard.get();
                boardRepository.delete(board);

                return BoardDeleteResponseDto.success();


            } catch (Exception e) {
                log.debug("삭제 실패",e);
                return ResponseDto.databaseError();
            }
        }

        @Override
        public ResponseEntity<? super BoardEditResponseDto> boardEdit(BoardEditRequestDto dto) {
            try {
                // 요청된 게시물 ID를 사용하여 해당 게시물을 데이터베이스에서 조회합니다.
                Optional<BoardEntity> optionalBoard = boardRepository.findById(dto.getBoardId());
                
                // 게시물이 존재하지 않을 경우 수정 실패 응답을 반환합니다.
                if (!optionalBoard.isPresent()) {
                    return BoardEditResponseDto.editFail();
                }
                BoardEntity board = optionalBoard.get();

                        // 수정할 필드들을 업데이트합니다.
                board.setTitle(dto.getTitle());
                board.setCtnt(dto.getCtnt());
                board.setCate(dto.getCate());
                // 파일이 첨부되었을 경우에만 이미지 엔티티 생성 및 저장을 수행합니다.
                if (dto.getBoardImageList() != null && !dto.getBoardImageList().isEmpty()) {
                    Long boardId = board.getBoardId();
                    List<String> boardImageList = dto.getBoardImageList();
                    List<ImageEntity> imageEntities = new ArrayList<>();
                    
                    // 이미지 엔티티 생성 및 저장
                    for (String image: boardImageList) {
                        ImageEntity imageEntity = new ImageEntity(boardId, image);
                        imageEntities.add(imageEntity);
                    }
                    imageRepository.saveAll(imageEntities);
                }
                Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                dto.setBoardChg(currentDate);

                // 수정된 게시물을 저장합니다.
                boardRepository.save(board);

                // 수정 성공 응답을 반환합니다.
                return BoardEditResponseDto.success();


            } catch (Exception e) {
                log.debug("수정 실패");
                return ResponseDto.databaseError();
            }
        }

        @Override
        public ResponseEntity<? super BoardReadResponseDto> boardRead(Long boardId) {

            BoardReadResultSet resultSet = null;
            List<ImageEntity> imageEntities = new ArrayList<>();

            try {
                
                resultSet = boardRepository.boardRead(boardId);
                if (resultSet == null) return BoardReadResponseDto.boardNotFound();

                imageEntities = imageRepository.findByBoardId(boardId);

                BoardEntity boardEntity = boardRepository.findByBoardId(boardId);

                return BoardReadResponseDto.success(resultSet, imageEntities);
            } catch (Exception e) {
                return ResponseDto.databaseError();
            }
            
        }
}
