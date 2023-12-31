package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.TodoDoneDto;
import sidea.version.nudge.dto.TodoDto;

import java.util.List;

@Mapper
public interface TodoMapper {


    //투두리스트 조회
    public List<TodoDto> getTodoList(long userIdx, String todoDate, int date) throws Exception;

    //투두 등록
    public int insertTodo(TodoDto todoDto) throws Exception;

    //투두 수정
    public int updateTodo(TodoDto todoDto) throws Exception;

    //투두 삭제
    public int deleteTodo(TodoDto todoDto) throws Exception;

    //투두 완료(첫 등록)
    public int insertTodoDone(TodoDoneDto todoDoneDto) throws Exception;

    //투두 완료(등록 후 변경)
    public int updateTodoDone(TodoDoneDto todoDoneDto) throws Exception;

    //투두 알람 설정 변경
    public int alarmTodo(TodoDto todoDto) throws Exception;

    //투두 알람리스트 조회
    public List<TodoDto> getAlarmTodolist(long userIdx, String todoDate) throws Exception;

}
