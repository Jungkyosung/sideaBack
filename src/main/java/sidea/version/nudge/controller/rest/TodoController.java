package sidea.version.nudge.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.TodoDoneDto;
import sidea.version.nudge.dto.TodoDto;
import sidea.version.nudge.service.TodoService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    //투두리스트조회 회원, 날짜별
    @GetMapping("")
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
    @PostMapping("")
    public ResponseEntity<Object> insertTodo(@Validated  @RequestBody TodoDto todoDto) throws Exception {

        int insertedTodo = todoService.insertTodo(todoDto);

        if (insertedTodo > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("등록");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("등록 불가");
        }
    }

    //투두 수정
    @PutMapping("")
    public ResponseEntity<Object> updateTodo(@Validated @RequestBody TodoDto todoDto) throws Exception {

        int updatedTodo = todoService.updateTodo(todoDto);

        if (updatedTodo > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("수정");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 불가");
        }
    }

    //투두 삭제
    @DeleteMapping("")
    public ResponseEntity<Object> deleteTodo(@RequestBody TodoDto todoDto) throws Exception {

        int deletedTodo = todoService.deleteTodo(todoDto);

        if (deletedTodo > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("삭제");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 불가");
        }
    }

    //투두 완료(첫 등록)
    @PostMapping("/done")
    public ResponseEntity<Object> insertTodoDone(@RequestBody TodoDoneDto todoDoneDto) throws Exception {

        int insertedTodoDone = todoService.insertTodoDone(todoDoneDto);

        if (insertedTodoDone > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("완료");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("처리 불가");
        }
    }

    //투두 완료(등록시 수정)
    @PutMapping("/done")
    public ResponseEntity<Object> updateTodoDone(@RequestBody TodoDoneDto todoDoneDto) throws Exception {

        int updatedTodoDone = todoService.updateTodoDone(todoDoneDto);

        if (updatedTodoDone > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("완료");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("처리 불가");
        }
    }

    //투두 알림 설정변경
    @PutMapping("/alarm")
    public ResponseEntity<Object> alarmTodo(@RequestBody TodoDto todoDto) throws Exception {

        int alarmTodo = todoService.alarmTodo(todoDto);

        if (alarmTodo > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("변경완료");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("처리불가");
        }
    }

    //투두 알림관련 이용자의 현재 시간 이후 일정(매주 반복 포함) 조회
    @GetMapping("/alarm/todolist")
    public ResponseEntity<Object> getAlarmTodolist(@RequestParam(value = "userIdx") long userIdx,
                                              @RequestParam(value = "todoDate") String todoDate) throws Exception {

        List<TodoDto> alarmTodolist = todoService.getAlarmTodolist(userIdx, todoDate);

        if (alarmTodolist.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(alarmTodolist);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("처리불가");
        }
    }

}
