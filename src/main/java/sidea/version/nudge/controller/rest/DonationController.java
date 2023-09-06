package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.DonationDto;
import sidea.version.nudge.dto.DonationInfoDto;
import sidea.version.nudge.dto.UserDonationDto;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.service.DonationService;

import java.util.List;

@RestController
@RequestMapping("/api/donation")
public class DonationController {

    @Autowired
    private DonationService donationService;

    //기부리스트 조회
    @GetMapping("")
    public ResponseEntity<Object> getDonationList() throws Exception{

        List<DonationDto> donationList = donationService.getDonationList();

        if( donationList.size() > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(donationList);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("조회 불가");
        }
    }

    //기부세부내역 조회
    @GetMapping("/detail")
    public ResponseEntity<Object> getDonationDetail(@RequestParam(value = "donationIdx") long donationIdx) throws Exception{

        DonationInfoDto donationDetail = donationService.getDonationDetail(donationIdx);

        if( donationDetail != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(donationDetail);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("조회 불가");
        }
    }

    //참여한 기부 리스트
    @GetMapping("/mydonation")
    public ResponseEntity<Object> getDonationListByUser(@RequestParam(value = "userIdx") int userIdx) throws Exception{

        List<DonationInfoDto> donationList = donationService.getDonationListByUser(userIdx);

        if( donationList.size() >= 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(donationList);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("조회 불가");
        }
    }

    //기부 참여하기
    @PostMapping
    public ResponseEntity<Object> donate(@RequestBody UserDonationDto userDonationDto) throws Exception{

        int donatedCnt = donationService.donate(userDonationDto);

        if( donatedCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("기부");
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("기부 실패");
        }
    }


}
