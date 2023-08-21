package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.AskDto;
import sidea.version.nudge.service.QnaService;

import java.util.List;

@RestController
@RequestMapping("/api/qna")
public class QnaController {

    @Autowired
    private QnaService qnaService;

    //회원 QNA 목록 조회
    @GetMapping
    public ResponseEntity<Object> getQnaList(@RequestParam(value = "userIdx") long userIdx) throws Exception{

        List<AskDto> qnaList = qnaService.getQnaList(userIdx);

        if (qnaList.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(qnaList);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("조회 불가 또는 조회 내용 없음");
        }

    }


    //QNA 상세보기(조회)
    @GetMapping("/detail")
    public ResponseEntity<Object> getQnaDetail(
            @RequestParam(value = "askIdx") long askIdx,
            @RequestParam(value = "userIdx") long userIdx
            ) throws Exception{

        AskDto askDto = qnaService.getQnaDetail(askIdx, userIdx);

        if (askDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(askDto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("조회 불가 또는 조회 내용 없음");
        }
    }

    //QNA 등록
    @PostMapping
    public ResponseEntity<Object> insertQna(@RequestBody AskDto askDto) throws Exception{

        int insertedQna = qnaService.insertQna(askDto);

        if (insertedQna > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("등록");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("등록 불가");
        }
    }

    //QNA 수정
    @PutMapping
    public ResponseEntity<Object> updateQna(@RequestBody AskDto askDto) throws Exception{

        int updatedCnt = qnaService.updateQna(askDto);

        if (updatedCnt > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("수정");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("수정 불가");
        }
    }

    //QNA 삭제
    @DeleteMapping
    public ResponseEntity<Object> deletetQna(@RequestBody AskDto askDto) throws Exception{

        int deletedCnt = qnaService.deleteQna(askDto);

        if (deletedCnt > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("삭제");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("삭제 불가");
        }
    }


}
