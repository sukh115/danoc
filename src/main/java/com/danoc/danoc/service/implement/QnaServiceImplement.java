package com.danoc.danoc.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import com.danoc.danoc.dto.ResponseDto;
import com.danoc.danoc.dto.request.qna.QnaDeleteRequestDto;
import com.danoc.danoc.dto.request.qna.QnaWriteRequestDto;
import com.danoc.danoc.dto.response.qna.QnaDeleteResponseDto;
import com.danoc.danoc.dto.response.qna.QnaWriteResponseDto;
import com.danoc.danoc.entity.ImageEntity;
import com.danoc.danoc.entity.QnaEntity;
import com.danoc.danoc.repository.ImageRepository;
import com.danoc.danoc.repository.QnaRepository;
import com.danoc.danoc.repository.UserRepository;
import com.danoc.danoc.service.BoardService;
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


}
