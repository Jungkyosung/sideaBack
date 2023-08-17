package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.DonationDto;
import sidea.version.nudge.dto.DonationInfoDto;
import sidea.version.nudge.service.AdminDonationService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/donation")
public class AdminDonationController {

    @Autowired
    private AdminDonationService adminDonationService;

    //캠페인 목록 조회
    @GetMapping("/list")
    public ResponseEntity<Object> getDonationList() throws Exception{

        List<DonationInfoDto> donationList = adminDonationService.getDonationList();

        if( donationList.size() > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(donationList);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("조회 불가");
        }
    }

    //캠페인 세부내역 조회
    @GetMapping("/detail")
    public ResponseEntity<Object> getDonationDetail(@RequestParam(value="donationidx") String donationIdx) throws Exception{

        DonationInfoDto donationDetail = adminDonationService.getDonationDetail(donationIdx);

        if( donationDetail != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(donationDetail);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("조회 불가");
        }
    }

    //캠페인 등록
    @PostMapping("")
    public ResponseEntity<Object> insertDonation(@RequestBody DonationInfoDto donationInfoDto) throws Exception{

        int insertedCnt = adminDonationService.insertDonation(donationInfoDto);

        if( insertedCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("등록");
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("등록 불가");
        }
    }


    //캠페인 수정

    //캠페인 삭제
}
