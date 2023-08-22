package sidea.version.nudge.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.TodoDto;
import sidea.version.nudge.service.TodoService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    //투두리스트조회 회원, 날짜별
    @GetMapping("/todo")
    public ResponseEntity<Object> getTodoList(@RequestParam(value = "userIdx") long userIdx,
                                              @RequestParam(value = "todoDate") String todoDate,
                                              @RequestParam(value = "date") int date) throws Exception {

        List<TodoDto> todoList = todoService.getTodoList(userIdx, todoDate, date);

        if (todoList.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(todoList);
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("조회된 리스트가 없음");
        }
    }

    //투두 등록
    @PostMapping("/todo")
    public ResponseEntity<Object> insertTodo(@RequestBody TodoDto todoDto) throws Exception {

        int insertedTodo = todoService.insertTodo(todoDto);

        if (insertedTodo > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("등록");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("등록 불가");
        }
    }

    //투두 수정
    @PutMapping("/todo")
    public ResponseEntity<Object> updateTodo(@RequestBody TodoDto todoDto) throws Exception {

        int updatedTodo = todoService.updateTodo(todoDto);

        if (updatedTodo > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("수정");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 불가");
        }
    }

    //투두 삭제
    @DeleteMapping("/todo")
    public ResponseEntity<Object> deleteTodo(@RequestBody TodoDto todoDto) throws Exception {

        int deletedTodo = todoService.deleteTodo(todoDto);

        if (deletedTodo > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("삭제");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 불가");
        }
    }

    //투두 완료
    @PutMapping("/todo/done")
    public ResponseEntity<Object> finishTodo(@RequestBody TodoDto todoDto) throws Exception {

        int finishedTodo = todoService.finishTodo(todoDto);

        if (finishedTodo > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("완료");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("처리 불가");
        }
    }

    //투두 알림 설정변경
    @PutMapping("/todo/alarm")
    public ResponseEntity<Object> alarmTodo(@RequestBody TodoDto todoDto) throws Exception {

        int alarmTodo = todoService.alarmTodo(todoDto);

        if (alarmTodo > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("변경완료");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("처리불가");
        }
    }


}
