package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.DonationInfoDto;
import sidea.version.nudge.dto.PointDto;
import sidea.version.nudge.dto.PointTypeDto;
import sidea.version.nudge.service.PointService;

import java.util.List;

@RestController
@RequestMapping("/api/point")
public class PointController {

    @Autowired
    private PointService pointService;


    //포인트 내역 조회
    @GetMapping
    public ResponseEntity<Object> getPointList(@RequestParam(value = "userIdx") long userIdx) throws Exception{

        List<PointDto> pointList = pointService.getPointList(userIdx);

        if( pointList.size() >= 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(pointList);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("조회 불가");
        }
    }

    //포인트 충전
    @PostMapping
    public ResponseEntity<Object> chargePoint(@RequestBody PointDto pointDto) throws Exception{

        pointDto.setPointTypeIdx(3);

        int insertedCnt = pointService.chargePoint(pointDto);

        if( insertedCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("포인트 충전");
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("충전 불가");
        }
    }

    //포인트 기부



    //포인트 삭감

}
