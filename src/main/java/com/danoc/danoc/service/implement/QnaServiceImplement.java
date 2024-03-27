package com.danoc.danoc.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import com.danoc.danoc.dto.ResponseDto;
import com.danoc.danoc.dto.request.qna.QnaDeleteRequestDto;
import com.danoc.danoc.dto.request.qna.QnaEditRequestDto;
import com.danoc.danoc.dto.request.qna.QnaWriteRequestDto;
import com.danoc.danoc.dto.response.qna.QnaDeleteResponseDto;
import com.danoc.danoc.dto.response.qna.QnaEditResponseDto;
import com.danoc.danoc.dto.response.qna.QnaWriteResponseDto;
import com.danoc.danoc.entity.ImageEntity;
import com.danoc.danoc.entity.QnaEntity;
import com.danoc.danoc.repository.ImageRepository;
import com.danoc.danoc.repository.QnaRepository;
import com.danoc.danoc.repository.UserRepository;
import com.danoc.danoc.service.QnaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaServiceImplement implements QnaService {
    
    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;


    @Override
    public ResponseEntity<? super QnaWriteResponseDto> qnaWrite(QnaWriteRequestDto dto, Long userId) {

        try {
            
            boolean existsByUserId = userRepository.existsByUserId(userId);
            if (!existsByUserId) return QnaWriteResponseDto.userNotFound();

            QnaEntity qnaEntity = new QnaEntity(dto,userId);
            qnaRepository.save(qnaEntity);

            Long qaId = qnaEntity.getQaId();
            List<String> qnaImageList = dto.getQaImageList();

            if (qnaImageList == null || qnaImageList.isEmpty()) {
                return QnaWriteResponseDto.success();
            } else {
                List<ImageEntity> imageEntities = new ArrayList<>();
                for (String image : qnaImageList) {
                    ImageEntity imageEntity = new ImageEntity(qaId, image, true);
                    imageEntities.add(imageEntity);
                }
                imageRepository.saveAll(imageEntities);

                return QnaWriteResponseDto.success();
            }

        } catch (Exception e) {
            log.debug("QNA 작성 에러", e);
            return ResponseDto.databaseError();
        }
    }


    @Override
    public ResponseEntity<? super QnaDeleteResponseDto> qnaDelete(QnaDeleteRequestDto dto) {
        try {
            Long qaId = dto.getQaId();

            Optional<QnaEntity> qnaOptional = qnaRepository.findById(qaId);
            if(!qnaOptional.isPresent()) {
                return QnaDeleteResponseDto.deleteFail();
            }

            qnaOptional.ifPresent(qnaRepository::delete);

            return QnaDeleteResponseDto.success();
            
            
        } catch (Exception e) {
            log.debug("삭제 실패", e);
            return ResponseDto.databaseError();
        }
    }


    @Override
    public ResponseEntity<? super QnaEditResponseDto> qnaEdit(QnaEditRequestDto dto) {
       try {
            Long qaId = dto.getQaId();

            Optional<QnaEntity> qnaOptional = qnaRepository.findById(qaId);
            if(!qnaOptional.isPresent()) {
                return QnaEditResponseDto.editFail();
            }
            QnaEntity qna = qnaOptional.get();

            qna.setTitle(dto.getTitle());
            qna.setCtnt(dto.getCtnt());
            qna.setPwd(dto.getPwd());

            if (dto.getQaImageList() != null && !dto.getQaImageList().isEmpty()) {
                List<String> qnaImageList = dto. getQaImageList();
                List<ImageEntity> imageEntities = new ArrayList<>();

                for (String image: qnaImageList) {
                    ImageEntity imageEntity = new ImageEntity(qaId, image, true);
                    imageEntities.add(imageEntity);
                }
                imageRepository.saveAll(imageEntities);
            }

            qnaRepository.save(qna);

            return QnaEditResponseDto.success();

       } catch (Exception e) {
            log.debug("수정 실패");
                return ResponseDto.databaseError();
       }
    }


    @Override
    public List<QnaEntity> qnaList() {
        try {
            return qnaRepository.findByQaIdAndUserIdAndTitleAndDate(null, null, null, null);
        } catch (Exception e) {
            log.debug("게시판 목록을 불러올 수 없습니다", e);
            return Collections.emptyList();
        }
    }


}
