package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.AskDto;
import sidea.version.nudge.service.QnaService;

@RestController
@RequestMapping("/api/qna")
public class QnaController {

    @Autowired
    private QnaService qnaService;

    //회원 QNA 목록 조회
    @GetMapping
    public ResponseEntity<Object> getQnaList(@RequestParam(value = "userIdx") long userIdx) throws Exception{

        qnaService.getQnaList(userIdx);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }


    //QNA 상세보기(조회)
    @GetMapping("/detail")
    public ResponseEntity<Object> getQnaDetail(@RequestBody AskDto askDto) throws Exception{

        qnaService.getQnaDetail(askDto);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    //QNA 작성
    @PostMapping
    public ResponseEntity<Object> insertQna(@RequestBody AskDto askDto) throws Exception{

        qnaService.insertQna(askDto);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    //QNA 수정
    @PutMapping
    public ResponseEntity<Object> updateQna(@RequestBody AskDto askDto) throws Exception{

        qnaService.updateQna(askDto);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    //QNA 삭제
    @DeleteMapping
    public ResponseEntity<Object> deletetQna(@RequestBody AskDto askDto) throws Exception{

        qnaService.deleteQna(askDto);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }


}