package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.AskDto;
import sidea.version.nudge.service.AdminQnaService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/qna")
public class AdminQnaController {

    @Autowired
    private AdminQnaService adminQnaService;

    //QNA 목록 조회
    @GetMapping
    public ResponseEntity<Object> getQnaList() throws Exception{

        List<AskDto> qnaList = adminQnaService.getQnaList();

        if (qnaList.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(qnaList);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("조회 불가 또는 조회 내용 없음");
        }
    }

    //QNA 세부 조회
    @GetMapping("/detail")
    public ResponseEntity<Object> getQnaDetail(@RequestParam(value = "askIdx") long askIdx) throws Exception{

        AskDto qna = adminQnaService.getQnaDetail(askIdx);

        if (qna != null) {
            return ResponseEntity.status(HttpStatus.OK).body(qna);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("조회 불가 또는 조회 내용 없음");
        }
    }

    //QNA 등록
    @PostMapping
    public ResponseEntity<Object> insertQna(@RequestBody AskDto askDto) throws Exception{

        int insertedCnt = adminQnaService.insertQna(askDto);

        if (insertedCnt > 0 ) {
            return ResponseEntity.status(HttpStatus.OK).body("등록");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("등록 불가");
        }
    }

    //QNA 수정
    @PutMapping
    public ResponseEntity<Object> updateQna(@RequestBody AskDto askDto) throws Exception{

        int updatedCnt = adminQnaService.updateQna(askDto);

        if (updatedCnt > 0 ) {
            return ResponseEntity.status(HttpStatus.OK).body("수정");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("수정 불가");
        }
    }

    //QNA 삭제
    @DeleteMapping
    public ResponseEntity<Object> deleteQna(@RequestBody AskDto askDto) throws Exception{

        int deletedCnt = adminQnaService.deleteQna(askDto);

        if (deletedCnt > 0 ) {
            return ResponseEntity.status(HttpStatus.OK).body("삭제");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("삭제 불가");
        }
    }

}
