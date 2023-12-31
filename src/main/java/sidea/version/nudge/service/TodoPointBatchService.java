package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.TestDto;
import sidea.version.nudge.dto.TodoDto;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.mapper.TodoMapper;
import sidea.version.nudge.mapper.UserMapper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TodoPointBatchService {


    //알림은 최근 100개만 보여주면 될 듯? 그 중에서 안 읽은 것만 개수를 세면 될 듯 함
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private PointService pointService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TodoMapper todoMapper;

    @Scheduled(cron= "0/5 * * * * *")
    public void notificationTest(){

        //메시지알림은 이렇게 보내면 됨. 포인트가 갱신되면 어떻게 처리할지가 문제임. 배치는 배치대로 돌텐데
        //
        TestDto tdto = new TestDto();
        tdto.setMsg("메시지가 잘 가는지 테스트");
        messagingTemplate.convertAndSend("/topic/alarm", tdto);
    }


    @Scheduled(cron= "0 55 23 * * *")
    public void startBatch() throws Exception{

        //오늘자를 기준으로 탈퇴하지 않은 회원 모두 조회

        //select user_idx, user_point_balance from user where user_leave = 'N';
        //시작일과 종료일, 요일별로 투두 리스트 조회
        //고민? 투두를 완료하자마자 포인트 적립을 해주고 차감은 12시 정각에 한번에 정산하는게 맞는지
        //아니면 투두를 완료해도 저녁 12시에 완료하지 않은 투두와 함께 정산하는 게 맞는지?
        //
        //탈퇴하지 않은 회원 조회
        List<UserDto> userList = userMapper.findNotLeaveUser();

        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        //1,월요일 ~ 7,일요일

        //이걸 회원별로 하나씩 처리하는 게 맞는 걸까?
        //1.회원별로 하나씩 처리시
        for ( UserDto user : userList){

            int minusPoint = 0;
            int plusPoint = 0;

            //todoList todoDone테이블과 조인해서 가져올 것.
            List<TodoDto> todoList = todoMapper.getTodoList(user.getUserIdx(), now.toString(), dayOfWeekNumber);
            for( TodoDto todo : todoList){

                int todoDone = todo.getTodoDone();

                if( todoDone == 0 ){
                    minusPoint += todo.getTodoPoint();
                } else {
                    plusPoint += todo.getTodoPoint();
                }
            }

            double updatedPointBalance = user.getUserPointBalance() - minusPoint + plusPoint;
            user.setUserPointBalance(updatedPointBalance);
            pointService.updatePointBalance(user);

        }

    }

}
