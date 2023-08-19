package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.AskDto;
import sidea.version.nudge.service.AdminQnaService;

@RestController
@RequestMapping("/api/admin/qna")
public class AdminQnaController {

    @Autowired
    private AdminQnaService adminQnaService;

    //QNA 목록 조회
    @GetMapping
    public ResponseEntity<Object> getQnaList() throws Exception{

        adminQnaService.getQnaList();

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    //QNA 세부 조회
    @GetMapping("/detail")
    public ResponseEntity<Object> getQnaDetail(@RequestParam(value = "askIdx") long askIdx) throws Exception{

        adminQnaService.getQnaDetail(askIdx);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    //QNA 등록
    @GetMapping
    public ResponseEntity<Object> insertQna(@RequestBody AskDto askDto) throws Exception{

        adminQnaService.insertQna(askDto);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    //QNA 수정
    @GetMapping
    public ResponseEntity<Object> updateQna(@RequestBody AskDto askDto) throws Exception{

        adminQnaService.insertQna(askDto);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    //QNA 삭제
    @GetMapping
    public ResponseEntity<Object> deleteQna(@RequestBody AskDto askDto) throws Exception{

        adminQnaService.insertQna(askDto);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
