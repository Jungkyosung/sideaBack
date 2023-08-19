package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.*;
import sidea.version.nudge.service.PointService;

import java.util.List;

@RestController
@RequestMapping("/api/point")
public class PointController {

    @Autowired
    private PointService pointService;


    //회원 포인트 내역 리스트 조회
    @GetMapping
    public ResponseEntity<Object> getPointList(@RequestParam(value = "userIdx") long userIdx) throws Exception{

        List<PointDto> pointList = pointService.getPointList(userIdx);

        if( pointList.size() >= 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(pointList);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("조회 불가");
        }
    }

    //포인트 충전(충전, 포인트 계좌 ++)
    @PostMapping("/charging")
    @Transactional
    public ResponseEntity<Object> chargePoint(@RequestBody PointDto pointDto) throws Exception{

        //포인트 충전이면 회원 포인트를 조정하고 충전내역을 등록해야 하지 않나??
        //회원별로 포인트 내역을 기록해야 할 듯?
        pointDto.setPointTypeIdx(PointType.기부.value());//이렇게 하면 기부의 값인 3이 나오나?

        //int, double 단위 좀 맞춰야 할 듯....
        //회원 포인트 잔액 조회 -> 잔액 + 충전액 -> 회원정보 업데이트 -> 포인트 충전 내역 입력
        int pointBalance = pointService.getPointBalance(pointDto.getUserIdx());
        int pointScore = (int)pointDto.getPointScore();
        UserDto userDto = new UserDto();
        userDto.setUserIdx(pointDto.getUserIdx());
        userDto.setUserPointBalance(pointBalance + pointScore);

        int updatedCnt = pointService.updatePointBalance(userDto);
        int insertedCnt = pointService.chargePoint(pointDto);

        if( (updatedCnt == insertedCnt) && insertedCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("포인트 충전");
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("충전 불가");
        }
    }

    //포인트 기부
    @PostMapping("/donation")
    public ResponseEntity<Object> donatePoint(@RequestBody PointDto pointDto) throws Exception{

        //포인트 충전이면 회원 포인트를 조정하고 충전내역을 등록해야 하지 않나??
        //회원별로 포인트 내역을 기록해야 할 듯?
        pointDto.setPointTypeIdx(PointType.기부.value());//이렇게 하면 기부의 값인 3이 나오나?

        //int, double 단위 좀 맞춰야 할 듯....
        //회원 포인트 잔액 조회 -> 잔액 + 충전액 -> 회원정보 업데이트 -> 포인트 충전 내역 입력
        int pointBalance = pointService.getPointBalance(pointDto.getUserIdx());
        int pointScore = (int)pointDto.getPointScore();
        UserDto userDto = new UserDto();
        userDto.setUserIdx(pointDto.getUserIdx());
        userDto.setUserPointBalance(pointBalance + pointScore);

        int updatedCnt = pointService.updatePointBalance(userDto);
        int insertedCnt = pointService.chargePoint(pointDto);

        if( (updatedCnt == insertedCnt) && insertedCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("포인트 충전");
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("충전 불가");
        }
    }


    //포인트 적립
    @PostMapping("/earning")
    public ResponseEntity<Object> earnPoint(@RequestBody PointDto pointDto) throws Exception{

        //포인트 충전이면 회원 포인트를 조정하고 충전내역을 등록해야 하지 않나??
        //회원별로 포인트 내역을 기록해야 할 듯?
        pointDto.setPointTypeIdx(PointType.기부.value());//이렇게 하면 기부의 값인 3이 나오나?

        //int, double 단위 좀 맞춰야 할 듯....
        //회원 포인트 잔액 조회 -> 잔액 + 충전액 -> 회원정보 업데이트 -> 포인트 충전 내역 입력
        int pointBalance = pointService.getPointBalance(pointDto.getUserIdx());
        int pointScore = (int)pointDto.getPointScore();
        UserDto userDto = new UserDto();
        userDto.setUserIdx(pointDto.getUserIdx());
        userDto.setUserPointBalance(pointBalance + pointScore);

        int updatedCnt = pointService.updatePointBalance(userDto);
        int insertedCnt = pointService.chargePoint(pointDto);

        if( (updatedCnt == insertedCnt) && insertedCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("포인트 충전");
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("충전 불가");
        }
    }

}
