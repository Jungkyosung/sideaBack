package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.TodoDto;
import sidea.version.nudge.mapper.LoginMapper;
import sidea.version.nudge.mapper.TodoMapper;
import sidea.version.nudge.mapper.UserMapper;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoMapper todoMapper;

    //투두 조회
    public List<TodoDto> getTodoList(long userIdx, String todoDate) throws Exception{
        return todoMapper.getTodoList(userIdx, todoDate);
    }

    //투두 등록
    public int insertTodo(TodoDto todoDto) throws Exception{
        return todoMapper.insertTodo(todoDto);
    }

    //투두 수정
    public int updateTodo(TodoDto todoDto) throws Exception{
        return todoMapper.updateTodo(todoDto);
    }

    //투두 삭제
    public int deleteTodo(TodoDto todoDto) throws Exception{
        return todoMapper.deleteTodo(todoDto);
    }

    //투두 완료
    public int finishTodo(TodoDto todoDto) throws Exception{
        return todoMapper.finishTodo(todoDto);
    }

    //투두 알람 설정변경
    public int alarmTodo(TodoDto todoDto) throws Exception{

        return todoMapper.alarmTodo(todoDto);
    }

}
