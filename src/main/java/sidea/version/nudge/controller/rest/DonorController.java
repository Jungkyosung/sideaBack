package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.DonorDto;
import sidea.version.nudge.service.DonorService;

import java.util.List;

@RestController
@RequestMapping("/api/donor")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @GetMapping("")
    public ResponseEntity<Object> getDonorList() throws Exception{

        List<DonorDto> donorList = donorService.getDonorList();

        if( donorList.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(donorList);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body("조회 내용 없음");
        }
    }
}
